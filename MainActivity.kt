package com.example.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.activity.ui.theme.ActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityTheme {
                MainScreen()
            }
        }
    }
}

enum class ListType {
    SIMPLE, LAZY, RICH
}

@Composable
fun MainScreen() {
    var currentList by remember { mutableStateOf<ListType?>(null) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = { currentList = ListType.SIMPLE }) { Text("List") }
                Button(onClick = { currentList = ListType.LAZY }) { Text("LazyList") }
                Button(onClick = { currentList = ListType.RICH }) { Text("RichList") }
            }

            Spacer(modifier = Modifier.height(24.dp))

            when (currentList) {
                ListType.SIMPLE -> SimpleList()
                ListType.LAZY -> LazyList()
                ListType.RICH -> RichList()
                null -> Text("Clique sur un bouton pour afficher une liste")
            }
        }
    }
}

@Composable
fun SimpleList() {
    Column(modifier = Modifier.padding(16.dp)) {
        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        items.forEach { Text(it) }
    }
}

@Composable
fun LazyList() {
    val items = (1..20).map { "Item $it" }
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun RichList() {
    val items = (1..10).map { "Item $it" }
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.android),
                    contentDescription = "Image",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(item)
            }
        }
    }
}
