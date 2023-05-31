package com.example.fcmilan.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fcmilan.R
import com.example.fcmilan.activities.ui.theme.FCMilanTheme

class GalleryActivity : ComponentActivity() {
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
                                painterResource(id = R.drawable.white_background),
                                contentScale = ContentScale.FillBounds)
                    }, color = Color.Transparent) {
                    GalleryPage()
                }
            }
        }
    }
}
@Composable
fun GalleryPage()
{
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
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
fun Greeting6(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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
@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    FCMilanTheme {
        Greeting6("Android")
    }
}