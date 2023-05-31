package com.example.fcmilan.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.fcmilan.R
import com.example.fcmilan.activities.ui.theme.FCMilanTheme

class TrophiesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FCMilanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrophyPage()
                }
            }
        }
    }
}


@Composable
fun TrophyPage() {
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
        Header(text = "TROPHIES")
        TrophyImage()
        TrophyInformation()
    }
}
@Composable()
fun TrophyImage() {
    Image(painterResource(id = R.drawable.trophies),
        "Trophy Image",
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        contentScale = ContentScale.FillWidth)
}
@Composable()
fun TrophyInformation() {
    StyledText(textResource(id = R.string.trophy_information))

}
@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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
@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    FCMilanTheme {
        Greeting3("Android")
    }
}