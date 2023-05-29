package com.example.fcmilan.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fcmilan.R
import com.example.fcmilan.ui.theme.FCMilanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FCMilanTheme {
                // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = with (Modifier){
                            fillMaxSize()
                                .paint(
                                    // Replace with your image id
                                    painterResource(id = R.drawable.red_background),
                                    contentScale = ContentScale.FillBounds)
                        }, color = Color.Transparent) {
                        buttonList()
                    }


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
@Composable
fun buttonList(){
   
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        mainButton(buttonText = "HISTORY")
        mainButton(buttonText = "TROPHIES")
        mainButton(buttonText = "PLAYERS")
        mainButton(buttonText = "MATCHES")
        mainButton(buttonText = "GALLERY")
    }

}
@Composable
fun mainButton(buttonText:String){
    val context = LocalContext.current
    Button(modifier = Modifier
        .width(200.dp)
        .height(120.dp).padding(20.dp),
        onClick = {
        switchActivity(context, buttonText)
        }) {

        Text(text = buttonText)
    }
}
fun switchActivity(context: Context, name:String)
{
    var intent = Intent(context,HistoryActivity::class.java)
    if(name == "HISTORY")
    {
        intent = Intent(context,HistoryActivity::class.java)
    }
    else if(name=="TROPHIES")
    {
        intent = Intent(context,TrophiesActivity::class.java)

    }
    else if(name=="PLAYERS")
    {
        intent = Intent(context,PlayersActivity::class.java)

    }
    else if(name=="MATCHES")
    {
        intent = Intent(context,MatchesActivity::class.java)

    }
    else if(name=="GALLERY")
    {
        intent = Intent(context,GalleryActivity::class.java)

    }
    context.startActivity(intent)
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FCMilanTheme {
        Greeting("Main Activity")
    }
}