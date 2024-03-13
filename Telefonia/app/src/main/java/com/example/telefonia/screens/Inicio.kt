package com.example.telefonia.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telefonia.ViewModelMsj

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Inicio(viewModel: ViewModelMsj) {
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.Numero,
                    onValueChange = { viewModel.updateNumero(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "Número de Teléfono") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                )

                OutlinedTextField(
                    value = viewModel.Mensaje,
                    onValueChange = { viewModel.updateMensaje(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "Escriba el Mensaje") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                Button(
                    onClick = {
                        // Acción al hacer clic en el botón
                        // Aquí puedes agregar lógica para enviar el mensaje de texto
                        viewModel.sendSMS()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Enviar SMS")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MensajePreview() {
    // Aquí puedes crear un ViewModel de ejemplo para la previsualización
    val viewModel = ViewModelMsj()
    Inicio(viewModel = viewModel)
}