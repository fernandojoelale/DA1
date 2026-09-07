package edu.uadeapps.a2doproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uadeapps.a2doproyecto.ui.theme._2doProyectoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            _2doProyectoTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {

                        //Contador()
                        PantallaEdad()

                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun datoEstudiante(
    etiqueta: String,
    valor: String
) {
    Text(
        text = "$etiqueta: $valor"
    )
}


fun descripcionEdad(edad: Int): String {
    return "Edad: $edad años"
}


@Composable
fun Contador() {

    var contador by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Valor: $contador",
            fontSize = 24.sp
        )

        Button(
            onClick = {
                contador++
            }
        ) {
            Text("Contador + 1")
        }

        Button(
            onClick = {
                if (contador > 0) {
                    contador--
                }
            }
        ) {
            Text("Contador - 1")
        }

        Button(
            onClick = {
                contador = 0
            }
        ) {
            Text("Reiniciar")
        }
    }
}

@Composable
fun PantallaEdad() {
    var nombre by remember { mutableStateOf("")}
    var edadTexto by remember { mutableStateOf("") }

    OutlinedTextField(
        value = nombre,
        onValueChange = { nombre = it },
        label = { Text("Ingrese Nombre") }
    )

    OutlinedTextField(
        value = edadTexto,
        onValueChange = { edadTexto = it },
        label = { Text("Ingrese Edad") }
    )

    val edadNumero = edadTexto.toIntOrNull()

    var resultado by remember { mutableStateOf("") }


    Button(onClick = {
        if (edadNumero != null) {
            resultado = ClasificadorEdad(edadNumero, nombre)
        }

        else {
            resultado = "Para la edad, ingrese un número entero"
        }
    }) {
        Text("Evaluar")

    }

    Text(resultado)


}


fun ClasificadorEdad(edad: Int, nombre:String): String {

    if (edad < 0)
        return "Por favor, ingrese un número válido"

    if (edad >= 18) {
        return "$nombre es mayor de edad"
    }
    else
        return "$nombre es menor de edad"

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _2doProyectoTheme {
        Greeting("Android")
    }
}


@Preview(showBackground = true)
@Composable
fun datoEstudiantePreview() {
    _2doProyectoTheme {
        // datoEstudiante("Nombre", "Fernando")
    }
}