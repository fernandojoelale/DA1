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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
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
                        //PantallaEdad()
                        //Calculadora()

//                        val materias = listOf(
//                            Materia("Programacion I", 1, true),
//                            Materia("Analisis Matematico II", 2, true),
//                            Materia("Desarrollo de Aplicaciones I", 3, false),
//                            Materia("Programacion III", 2, true),
//                            Materia("Ingenieria en Software", 5, false)
//                        )
//
//                        MateriasEnPantalla(materias)

                        TareasApp()

                    }
                }
            }
        }
    }
}


@Composable
fun TareasApp () {
    InputTareas()
}

@Composable
fun InputTareas() {

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Mis Tareas",
            fontSize = 24.sp
        )
    }

    var tarea by remember { mutableStateOf("")}
    val tareas = remember { mutableStateListOf<Tarea>() }

    OutlinedTextField(
        value = tarea,
        onValueChange = { tarea = it },
        label = { Text("Ingrese tarea") }
    )

    Button(onClick = { tareas.add(Tarea(tarea, false)) }) {
        Text("Agregar")
    }

    Button(
        onClick = { tareas.clear() }
    ) {
        Text("Borrar todas")
    }


    TareasList(tareas)


}

data class Tarea(
    val desc: String,
    val completada: Boolean
)



@Composable
fun TareasList(
    tareas: MutableList<Tarea>,
    modifier: Modifier = Modifier
) {
    var seleccionadas by remember { mutableStateOf(setOf<Tarea>()) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                "Tarea",
                modifier = Modifier.weight(2f),
                fontWeight = FontWeight.Bold
            )

            Text(
                "Estado",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(tareas) { tarea ->

                TareaItem(
                    tarea = tarea,
                    seleccionada = tarea in seleccionadas,
                    onSeleccionar = { marcada ->
                        seleccionadas = if (marcada) {
                            seleccionadas + tarea
                        } else {
                            seleccionadas - tarea
                        }
                    }
                )
            }
        }

        Row() {
            Row() {

            }

            Button(
                onClick = {
                    tareas.removeAll(seleccionadas)
                    seleccionadas = emptySet()
                }
            ) {
                Text("Borrar Seleccionadas")
            }

            Button(
                onClick = {
                    for (tarea in seleccionadas) {
                        val indice = tareas.indexOf(tarea)
                        tareas[indice] = tarea.copy(completada = true)
                    }
                    seleccionadas = emptySet()
                }
            ) {
                Text("Marcar completada")
            }
        }

        val countTareas =

                tareas.size

        Row {
            Text ("Cantidad de tareas: $countTareas")
        }




    }
}

@Composable
fun TareaItem(
    tarea: Tarea,
    seleccionada: Boolean,
    onSeleccionar: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Checkbox(
            checked = seleccionada,
            onCheckedChange = onSeleccionar
        )

        Text(
            tarea.desc,
            modifier = Modifier.weight(2f)
        )

        Text(
            text = if (tarea.completada) "Completada" else "No Completada",
            color = if (tarea.completada) Color.Green else Color.Red,
            modifier = Modifier.weight(1f)
        )
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
fun datoEstudiante(etiqueta: String, valor: String) {
    Text(
        text = "$etiqueta: $valor"
    )
}


fun descripcionEdad(edad: Int): String {
    return "Edad: $edad años"
}



data class Materia(
    val nombre: String,
    val anio: Int,
    val aprobada: Boolean
)

@Composable
fun MateriasEnPantalla(materias: List<Materia>) {

    var soloAprobadas by remember { mutableStateOf(false) }

    Row {
        Text("Filtrar solo aprobadas")

        Checkbox(
            checked = soloAprobadas,
            onCheckedChange = {
                soloAprobadas = it
            }
        )

    }

    val countMaterias =
        if (soloAprobadas) {
            materias.filter { it.aprobada }.size
        }
        else {
            materias.size
        }

    Row {
        Text ("Cantidad de materias: $countMaterias")
    }

    val materiasFiltradas = if (soloAprobadas == true) {
        materias.filter { it.aprobada }
    } else {
        materias
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                "Nombre",
                modifier = Modifier.weight(2f),
                fontWeight = FontWeight.Bold
            )

            Text(
                "Año",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )

            Text(
                "Estado",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn {
            items(materiasFiltradas) { materia ->
                MateriaItem(materia)
            }
        }
    }
}

@Composable
fun MateriaItem(materia: Materia) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            materia.nombre,
            modifier = Modifier.weight(2f)
        )

        Text(
            materia.anio.toString(),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = if (materia.aprobada) {
                "Aprobada"
            } else {"No aprobada"
            },
            color = if (materia.aprobada) {
                Color.Green
            } else {
                Color.Red
            },
            modifier = Modifier.weight(1f)
        )
    }
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

@Composable
fun Calculadora() {
    var a by remember { mutableStateOf("")}
    var b by remember { mutableStateOf("")}

    OutlinedTextField(
        value = a,
        onValueChange = { a = it },
        label = { Text("Ingrese un número") }
    )

    OutlinedTextField(
        value = b,
        onValueChange = { b = it },
        label = { Text("Ingrese otro número") }
    )

    val numeroA = a.toDoubleOrNull() ?: 0.0
    val numeroB = b.toDoubleOrNull() ?: 0.0
    var resultado by remember { mutableStateOf(0.0) }

    Row (Modifier.padding(top= 10.dp)) {
    Button(onClick =  {Log.d("CALCULADORA","Se ejectuó SUMAR");
        resultado = (Calcular(numeroA,numeroB, "Sumar"))}
    ) {
        Text("Sumar")
    }

    Button(onClick =  {resultado = (Calcular(numeroA,numeroB, "Restar"))}
    ) {
        Text("Restar")
    }

    Button(onClick =  {resultado = (Calcular(numeroA,numeroB, "Multiplicar"))}
    ) {
        Text("Multiplicar")
    }
    }

    Button (onClick = {resultado = 0.0}) {
        Text("Limpiar")
    }


        Text("Resultado ", Modifier.fillMaxWidth().padding(top= 20.dp), textAlign = TextAlign.Center )

        Text("$resultado",
            Modifier.fillMaxWidth().padding(top = 40.dp),

        textAlign = TextAlign.Center,
        fontSize = 56.sp,
        fontWeight = FontWeight.Bold,
            )

}

fun Calcular(a: Double, b: Double, operacion: String): Double {
    if (operacion == "Sumar") {
        return (a+b)
    }

    if (operacion == "Restar") {
        return (a-b)
    }

    if (operacion == "Multiplicar") {
        return (a*b)
    }

    return 0.0
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