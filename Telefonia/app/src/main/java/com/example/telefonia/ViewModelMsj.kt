package com.example.telefonia

import android.telephony.SmsManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModelMsj (): ViewModel(){
    var Mensaje by mutableStateOf("")
    var Numero by mutableStateOf("")

    fun updateMensaje(value: String){
        Mensaje = value
    }
    fun updateNumero(value: String){
        Numero = value
    }
    fun sendSMS() {
        try {
            if (Numero.isNotEmpty() && Mensaje.isNotEmpty()) {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(Numero, null, Mensaje, null, null)

                // Puedes realizar acciones adicionales después de enviar el SMS si es necesario
                // Por ejemplo, podrías limpiar los campos de entrada o mostrar un mensaje de éxito.
                clearFields()
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun clearFields() {
        Numero = ""
        Mensaje = ""
    }
}