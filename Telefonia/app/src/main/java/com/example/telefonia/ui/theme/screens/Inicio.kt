package com.example.telefonia.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.telefonia.ui.theme.viewModel.ViewModelMsj

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Inicio(viewModel: ViewModelMsj) {
    Scaffold {
        Box(modifier = Modifier
            .fillMaxSize()
            , contentAlignment = Alignment.Center){
            Column(modifier = Modifier) {
                OutlinedTextField(
                    value = viewModel.Numero,
                    onValueChange ={viewModel.updateNumero(it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    label = { Text(text = "Ingresa un Telefono")},
                    modifier = Modifier.size(350.dp,70.dp)
                )
                OutlinedTextField(
                    value = viewModel.Mensaje,
                    onValueChange ={viewModel.updateMensaje(it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Escriba el mensaje a enviar")},
                    modifier = Modifier.size(350.dp,70.dp)
                )
            }
        }
    }
}
