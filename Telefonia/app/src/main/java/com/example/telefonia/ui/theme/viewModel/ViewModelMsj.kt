package com.example.telefonia.ui.theme.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.telefonia.BroadcastReciver

class ViewModelMsj (): ViewModel() {
    var Mensaje by mutableStateOf("")
    var Numero by mutableStateOf("")

    fun updateMensaje(value: String){
        Mensaje = value
        BroadcastReciver.Telefono.mensaje=value
    }
    fun updateNumero(value: String){
        Numero = value
        BroadcastReciver.Telefono.numero=value
    }
}