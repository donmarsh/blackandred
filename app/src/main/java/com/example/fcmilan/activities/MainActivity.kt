package com.example.fcmilan.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fcmilan.R
import com.example.fcmilan.ui.theme.Black
import com.example.fcmilan.ui.theme.FCMilanTheme
import com.example.fcmilan.ui.theme.RedGradientStart
import com.example.fcmilan.ui.theme.RedPlain

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
                        ButtonList()
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
fun ButtonList(){
   
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        MainButton(buttonText = "HISTORY")
        MainButton(buttonText = "TROPHIES")
        MainButton(buttonText = "PLAYERS")
        MainButton(buttonText = "MATCHES")
        MainButton(buttonText = "GALLERY")
    }

}
@Composable
fun MainButton(buttonText:String){
    val context = LocalContext.current
    Button(modifier = Modifier
        .width(250.dp)
        .height(85.dp).padding(20.dp),
        colors = buttonColors(RedPlain),
        shape = RectangleShape,
        onClick = {
        switchActivity(context, buttonText)
        }) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            RedGradientStart,
                           Black
                        )
                    )
                ).border(BorderStroke(2.dp, Color.White)).fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = buttonText)
        }

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