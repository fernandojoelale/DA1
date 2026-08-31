package edu.uadeapps.a2doproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.uadeapps.a2doproyecto.ui.theme._2doProyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _2doProyectoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    fichaEstudiante(Modifier.padding(innerPadding))
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
fun fichaEstudiante(modifier: Modifier = Modifier) {
    val nombre = "Fernando"
    val edad = 26
    val promedio = 7
    val cursaProgramacion = true

    Column ( modifier = modifier){
        Text("Nombre: $nombre")
        Text("Edad: $edad")
        Text("Promedio: $promedio")
        Text("Cursa progamación?: $cursaProgramacion")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _2doProyectoTheme {
        Greeting("Android")
    }
}