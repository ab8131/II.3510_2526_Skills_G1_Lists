package com.example.unfinishedactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unfinishedactivity.ui.theme.UnfinishedActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Thème de l'application
            UnfinishedActivityTheme {
                UnfinishedMainScreen()
            }
        }
    }
}

// Enum pour différencier les types de liste
enum class ListType {
    SIMPLE, LAZY, RICH
}

@Composable
fun UnfinishedMainScreen() {
    var currentList by remember { mutableStateOf<ListType?>(null) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Boutons pour choisir le type de liste
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { currentList = ListType.SIMPLE }) { Text("List") }
                Button(onClick = { currentList = ListType.LAZY }) { Text("LazyList") }
                Button(onClick = { currentList = ListType.RICH }) { Text("RichList") }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Affichage de la liste choisie ou message par défaut
            when (currentList) {
                ListType.SIMPLE -> TODOListPlaceholder("Simple List")
                ListType.LAZY -> TODOListPlaceholder("Lazy List")
                ListType.RICH -> TODOListPlaceholder("Rich List")
                null -> Text("Clique sur un bouton pour afficher une liste")
            }
        }
    }
}

// Composable affichant un placeholder avec instructions
@Composable
fun TODOListPlaceholder(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$name à compléter !")

        Spacer(modifier = Modifier.height(16.dp))

        // Commentaires guidant l'étudiant
        Text(text = "→ Il faut compléter cette fonction.")
        Text(text = "→ Pour Simple List : utilisez Column avec Text pour chaque item.")
        Text(text = "→ Pour Lazy List : utilisez LazyColumn avec items().")
        Text(text = "→ Pour Rich List : ajoutez une Image + Text pour chaque item.")
        Text(text = "→ Il est possible de personnaliser le design avec padding, couleurs, etc.")
    }
}
