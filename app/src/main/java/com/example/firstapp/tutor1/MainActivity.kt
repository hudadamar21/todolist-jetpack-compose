//package com.example.firstapp.tutor1
//
////import SampleData
//import android.content.res.Configuration
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.firstapp.ui.theme.FirstappTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            FirstappTheme {
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    Conversation(SampleData.conversationSample)
//                }
//            }
//        }
//    }
//}
//
//data class Message(val author: String, val body: String)
//
//@Composable
//fun MessageCard(msg: Message) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.padding(10.dp)
//    ) {
//        Image(
//            painter = painterResource(R.drawable.ryo),
//            contentDescription = "Ryo",
//            modifier = Modifier
//                // Set image size to 40 dp
//                .size(50.dp)
//                // Clip image to be shaped as a circle
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
//        )
//        Spacer(modifier = Modifier.padding(5.dp))
//        Column {
//            Text(
//                text = msg.author,
//                style = MaterialTheme.typography.titleSmall
//            )
//            Text(
//                text = msg.body,
//                style = MaterialTheme.typography.bodySmall,
//                color = Color.Gray
//            )
//        }
//    }
//}
//
//@Composable
//fun Conversation(messages: List<Message>) {
//    LazyColumn {
//        items(messages) { message ->
//            MessageCard(message)
//        }
//    }
//}
//
//@Preview(name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//
//@Composable
//fun PreviewMessageCard() {
//    FirstappTheme {
//        Surface(modifier = Modifier.fillMaxSize()) {
//            Conversation(SampleData.conversationSample)
//        }
//    }
//}
//
