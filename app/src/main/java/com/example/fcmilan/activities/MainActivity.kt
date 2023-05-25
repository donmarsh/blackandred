package com.example.fcmilan.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fcmilan.ui.theme.FCMilanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FCMilanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
    mainButton(buttonText = "HISTORY")
    mainButton(buttonText = "TROPHIES")
    mainButton(buttonText = "PLAYERS")
    mainButton(buttonText = "MATCHES")
    mainButton(buttonText = "GALLERY")
}
@Composable
fun mainButton(buttonText:String){
    Button(modifier = Modifier
        .width(120.dp)
        .height(40.dp),
        onClick = {
        /*TODO*/
        }) {

        Text(text = buttonText)
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FCMilanTheme {
        Greeting("Android")
    }
}