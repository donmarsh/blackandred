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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fcmilan.R
import com.example.fcmilan.activities.ui.theme.FCMilanTheme

class HistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FCMilanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HistoryPage()
                }
            }
        }
    }
}
@Composable
fun HistoryPage() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
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
    Text(stringResource(id = R.string.club_history))
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    FCMilanTheme {
        Greeting2("Android")
    }
}