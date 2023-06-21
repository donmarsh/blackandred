package com.example.fcmilan.activities

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fcmilan.R
import com.example.fcmilan.models.fixtures.FixturesApiResponse
import com.example.fcmilan.models.players.TeamResponse
import com.example.fcmilan.services.FootballApi
import com.example.fcmilan.services.RetrofitClient
import com.example.fcmilan.ui.theme.Black
import com.example.fcmilan.ui.theme.FCMilanTheme
import com.example.fcmilan.ui.theme.RedGradientStart
import com.example.fcmilan.ui.theme.RedPlain
import com.example.fcmilan.viewmodels.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    val API_TEAM_ID = "489"
    val LEAGUE_ID = "135" //serie A
    val SEASON = Calendar.getInstance().get(Calendar.YEAR).toString()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.setIsHomeScreen(true)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val isCurrentScreenHomeScreen by viewModel.isHomeCurrent.observeAsState()
            val navController = rememberNavController()

            FCMilanTheme {
                // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = with (Modifier){

                            fillMaxSize()
                                .paint(
                                    // Replace with your image id
                                    painterResource(id = if(isCurrentScreenHomeScreen == true) R.drawable.red_background else R.drawable.white_background),
                                    contentScale = ContentScale.FillBounds)
                        }, color = Color.Transparent) {
                        Scaffold(
                            topBar = {
                                TopAppBarRed()
                            },
                            content = { innerPadding ->
                                NavHost(navController = navController, startDestination = "HOME") {
                                    composable("HOME") {
                                        viewModel.setIsHomeScreen(true)
                                        Log.e("isHomeScreen",viewModel.isHomeCurrent.value.toString())
                                        ButtonList(contentPadding = innerPadding, navigation = navController)
                                    }
                                    composable("TROPHIES") {
                                        viewModel.setIsHomeScreen(false)
                                        TrophyPage(contentPadding = innerPadding)
                                    }
                                    composable("GALLERY") {
                                        viewModel.setIsHomeScreen(false)
                                        GalleryPage(contentPadding = innerPadding)
                                    }
                                    composable("History") {
                                        viewModel.setIsHomeScreen(false)
                                        HistoryPage(contentPadding = innerPadding)
                                    }
                                    composable("MATCHES") {
                                        viewModel.setIsHomeScreen(false)
                                        MatchesPage(contentPadding = innerPadding)
                                    }
                                    composable("PLAYERS") {
                                        viewModel.setIsHomeScreen(false)
                                        Log.e("isHomeScreen",viewModel.isHomeCurrent.value.toString())
                                        PlayersPage(contentPadding = innerPadding)
                                    }
                                }
                            },
                            containerColor = Color.Transparent,
                            modifier = Modifier.fillMaxSize()


                        )
                    }


            }
        }
        //getFixturesList()
        getPlayers()
    }
    private fun getFixturesList(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(FootballApi::class.java)
        val getFixturesResponse = apiInterface.getFixtures(LEAGUE_ID, SEASON, API_TEAM_ID)
        getFixturesResponse.enqueue(object : Callback<FixturesApiResponse> {
            override fun onResponse(
                call: Call<FixturesApiResponse>,
                response: Response<FixturesApiResponse>
            ) {
                if(response.code()==200)
                {
                    Log.d("sucessful response",""+response.body()!!.fixturesResponse.size)
                    val fixturesApiResponse = response.body()!!
                    val fixturesResponse = fixturesApiResponse.fixturesResponse
                    fixturesResponse.forEach{e->
                        Log.d("fixture",e.fixture!!.venue!!.name!!)
                    }
                }
            }

            override fun onFailure(call: Call<FixturesApiResponse>, t: Throwable) {
                Log.d("failed response",t.message.toString())

                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
            }

        })

    }
    private fun getPlayers(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(FootballApi::class.java)
        val getPlayersResponse = apiInterface.getPlayers(SEASON, API_TEAM_ID,"1")
        getPlayersResponse.enqueue(object : Callback<TeamResponse>{
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if(response.code()==200)
                {
                    Log.d("player response",response.message())
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarRed() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
        modifier = Modifier.paint(painterResource(id = R.drawable.appbarbackground), contentScale = ContentScale.FillBounds),
        title = { Text("Black and Red",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.ExtraBold, color = Color.White
            ) }
    )
}
@Composable
fun ButtonList(contentPadding: PaddingValues, navigation:NavController){

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .padding(contentPadding))
    {
        MainButton(buttonText = "HISTORY", navigation)
        MainButton(buttonText = "TROPHIES", navigation)
        MainButton(buttonText = "PLAYERS", navigation)
        MainButton(buttonText = "MATCHES", navigation)
        MainButton(buttonText = "GALLERY", navigation)
    }

}

@Composable
fun MainButton(buttonText:String, navigation: NavController){
    Button(modifier = Modifier
        .width(300.dp)
        .height(100.dp)
        .padding(20.dp),
        colors = buttonColors(RedPlain),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        onClick = {
        switchComposable(buttonText, navigation)
        }) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            RedGradientStart,
                            Black
                        )
                    )
                )
                .border(BorderStroke(2.dp, Color.White))
                .fillMaxSize(), contentAlignment = Alignment.Center,
        ) {
            Text(text = buttonText)
        }

    }
}
fun switchComposable( name:String, navigation: NavController) {
        navigation.navigate(name)
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FCMilanTheme {
        Greeting("Main Activity")
    }
}
@Composable
@ReadOnlyComposable
fun textResource(@StringRes id: Int): CharSequence =
    LocalContext.current.resources.getText(id)
// Creating a function to display the styled text
@Composable
fun StyledText(text: CharSequence, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = {
            it.text = text
        }
    )
}
@Composable
fun Header(text:String) {
    Text(text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
}
@Composable
fun TrophyPage(contentPadding:PaddingValues) {
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(contentPadding)){
        Header(text = "TROPHIES")
        TrophyImage()
        TrophyInformation()
    }
}

@Composable
fun TrophyImage() {
    Image(painterResource(id = R.drawable.trophies),
        "Trophy Image",
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentScale = ContentScale.FillWidth)
}
@Composable
fun TrophyInformation() {
    StyledText(textResource(id = R.string.trophy_information))

}
@Composable
fun GalleryPage(contentPadding:PaddingValues)
{
    val context = LocalContext.current
    Column(verticalArrangement = Arrangement.Top, modifier = Modifier
        .padding(contentPadding)
        .verticalScroll(rememberScrollState())) {
        Header(text = "AC Milan")
        for(i in 1..10)
        {
            val image = "club$i"
            val resId = context.resources.getIdentifier(image,"drawable",context.packageName)
            GalleryImage(id = resId)
        }
    }
}

@Composable
fun GalleryImage(id:Int) {
    Image(
        painterResource(id = id),
        "Club Flag",
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentScale = ContentScale.FillWidth)

}
@Composable
fun PlayersPage(contentPadding: PaddingValues)
{
    Text(
        text = "Players Page!",
        modifier = Modifier.padding(contentPadding)
    )
}
@Composable
fun MatchesPage(contentPadding: PaddingValues)
{
    Text(
        text = "Matches Page!",
        modifier = Modifier.padding(contentPadding)
    )
}
@Composable
fun FixturesCard()
{
    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(color = RedPlain))
    {
        Box(modifier = Modifier.padding(5.dp).background(
            color = Color.Black
            )
        .border(BorderStroke(2.dp, Color.White)))
    }
}
@Composable
fun PlayersCard()
{
    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(color = RedPlain).shadow(1.dp))
    {
        Box(modifier = Modifier.padding(5.dp).background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    RedGradientStart,
                    Black
                )
            )
        ).border(BorderStroke(2.dp, Color.White)))
        {

        }
    }
}

@Composable
fun HistoryPage(contentPadding:PaddingValues) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(contentPadding)
        .verticalScroll(rememberScrollState())) {
        Header(text = "AC Milan")
        ClubFlagImage()
        ClubHistoryText()
    }

}
@Composable
fun ClubFlagImage() {
    Image(
        painterResource(id = R.drawable.football_flag),
        "Club Flag",
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentScale = ContentScale.FillWidth)

}
@Composable
fun ClubHistoryText() {
    Text(stringResource(id = R.string.club_history), modifier = Modifier.padding(5.dp))
}
