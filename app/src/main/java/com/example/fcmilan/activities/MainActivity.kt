package com.example.fcmilan.activities

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fcmilan.FootballApplication
import com.example.fcmilan.R
import com.example.fcmilan.entities.Fixture
import com.example.fcmilan.entities.Player
import com.example.fcmilan.models.fixtures.FixturesApiResponse
import com.example.fcmilan.models.players.PlayersResponse
import com.example.fcmilan.services.FootballApi
import com.example.fcmilan.services.RetrofitClient
import com.example.fcmilan.services.toFixture
import com.example.fcmilan.services.toPlayerEntity
import com.example.fcmilan.ui.theme.Black
import com.example.fcmilan.ui.theme.FCMilanTheme
import com.example.fcmilan.ui.theme.RedGradientStart
import com.example.fcmilan.ui.theme.RedPlain
import com.example.fcmilan.viewmodels.MainViewModel
import com.example.fcmilan.viewmodels.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val API_TEAM_ID = "489"
    private val LEAGUE_ID = "135" //serie A
    private val SEASON = Calendar.getInstance().get(Calendar.YEAR)-1
    private val mainViewModel: MainViewModel by viewModels {
            MainViewModelFactory((application as FootballApplication).playerRepository,
                (application as FootballApplication).fixtureRepository)
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.setIsHomeScreen(true)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val isCurrentScreenHomeScreen by mainViewModel.isHomeCurrent.observeAsState()
            val fixtures by mainViewModel.allFixtures.observeAsState()
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
                                        mainViewModel.setIsHomeScreen(true)
                                        Log.e("isHomeScreen",mainViewModel.isHomeCurrent.value.toString())
                                        ButtonList(contentPadding = innerPadding, navigation = navController)
                                    }
                                    composable("TROPHIES") {
                                        mainViewModel.setIsHomeScreen(false)
                                        TrophyPage(contentPadding = innerPadding)
                                    }
                                    composable("GALLERY") {
                                        mainViewModel.setIsHomeScreen(false)
                                        GalleryPage(contentPadding = innerPadding)
                                    }
                                    composable("History") {
                                        mainViewModel.setIsHomeScreen(false)
                                        HistoryPage(contentPadding = innerPadding)
                                    }
                                    composable("MATCHES") {
                                        mainViewModel.setIsHomeScreen(false)
                                        MatchesPage(contentPadding = innerPadding,fixtures!!)
                                    }
                                    composable("PLAYERS") {
                                        mainViewModel.setIsHomeScreen(false)
                                        Log.e("isHomeScreen",mainViewModel.isHomeCurrent.value.toString())
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
        getFixturesList()
        //getPlayers()
    }
    private fun getFixturesList(){
        val fixturesEntities = arrayListOf<Fixture>()
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(FootballApi::class.java)
        val getFixturesResponse = apiInterface.getFixtures(LEAGUE_ID, SEASON.toString(), API_TEAM_ID)
        getFixturesResponse.enqueue(object : Callback<FixturesApiResponse> {
            override fun onResponse(
                call: Call<FixturesApiResponse>,
                response: Response<FixturesApiResponse>
            ) {
                Log.d("fixtures_response","${response.code()}")
                if(response.code()==200)
                {
                    val fixturesApiResponse = response.body()!!
                    val fixturesResponse = fixturesApiResponse.fixturesResponse
                    fixturesResponse.forEach{e->
                        Log.d("fixture_venue",e.fixture!!.venue!!.name!!)
                        fixturesEntities.add(e.toFixture())
                    }
                    mainViewModel.deleteFixtures()
                    mainViewModel.insertFixtures(fixturesEntities)
                }
            }

            override fun onFailure(call: Call<FixturesApiResponse>, t: Throwable) {
                Log.d("failed response",t.message.toString())

                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
            }

        })

    }
    private fun getPlayers(){
        val allPlayers:ArrayList<PlayersResponse> = arrayListOf()
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(FootballApi::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            val getPlayersResponse = apiInterface.getPlayers(SEASON.toString(), API_TEAM_ID,"1").execute()
            if(getPlayersResponse.isSuccessful)
            {
                val totalPages = getPlayersResponse.body()!!.paging!!.total!!
                allPlayers.addAll(getPlayersResponse.body()!!.playersResponse)
                Log.d("player response",getPlayersResponse.message())
                for(i in 2..totalPages)
                {
                    val nextPlayersResponse = apiInterface.getPlayers(SEASON.toString(),API_TEAM_ID,""+i).execute()
                    allPlayers.addAll(nextPlayersResponse.body()!!.playersResponse)
                }
                Log.d("totalPlayers",""+allPlayers.size)
                val playerList = arrayListOf<Player>()
                for(i in 0 until allPlayers.size)
                {
                    val currentPlayer = allPlayers[i].player!!
                    playerList.add(currentPlayer.toPlayerEntity())
                }
                mainViewModel.deletePlayers()
                mainViewModel.insertPlayers(playerList)

            }
            else
            {
                Log.d("error",getPlayersResponse.errorBody()!!.string())
            }

        }

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
fun MatchesPage(contentPadding: PaddingValues,fixtures:List<Fixture> )
{
    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .padding(contentPadding)){
       items(fixtures){ fixture->
           FixturesCard(fixture = fixture)


       }
    }


}
@Composable
fun FixturesCard(fixture: Fixture)
{
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.time = sdf.parse(fixture.date) as Date
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    val time =  String.format("%02d:%02d", hour, minute)
    val date = String.format("%02d.%02d", day, month)

    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(color = RedPlain))
    {
        Box(modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
            .background(
                color = Color.Black
            )
            .border(BorderStroke(2.dp, Color.White))){
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(time, color = Color.Black,
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color = Color.White),
                    textAlign = TextAlign.Center)
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column() {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(fixture.homeTeamUrl)
                                .build(),
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp)
                                .padding(start = 10.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Black, CircleShape),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Home Team Image",
                        )
                        Text(fixture.homeTeam,
                            style  = TextStyle(fontSize = 11.sp),
                            modifier = Modifier
                                .width(70.dp)
                                .padding(start = 10.dp),
                            color = Color.White,
                            textAlign = TextAlign.Center)

                    }


                    Spacer(Modifier.weight(1f))
                    Column() {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(fixture.awayTeamUrl)
                                .build(),
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Black, CircleShape),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Away Team Image"
                        )
                        Text(fixture.awayTeam,
                            style  = TextStyle(fontSize = 11.sp),
                            modifier = Modifier
                                .width(70.dp)
                                .padding(end = 10.dp),
                            color = Color.White,
                            textAlign = TextAlign.Center)
                    }



                }
                Text(date, color = Color.Black,
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color = Color.White),
                    textAlign = TextAlign.Center)

            }


        }
    }
}
@Composable
fun PlayersCard()
{
    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(color = RedPlain)
        .shadow(1.dp))
    {
        Box(modifier = Modifier
            .padding(5.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        RedGradientStart,
                        Black
                    )
                )
            )
            .border(BorderStroke(2.dp, Color.White)))
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
