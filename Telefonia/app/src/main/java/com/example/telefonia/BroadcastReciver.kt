package com.example.telefonia

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.ServiceState
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.widget.Toast

class BroadcastReciver: BroadcastReceiver() {
    private var mTelephonyManager: TelephonyManager? = null
    private var mContext: Context? = null
    object Telefono{
        var mensaje=""
        var numero=""
    }
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        mContext = context

        if (action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            mTelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val ExtraerNumero = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            if (ExtraerNumero != null) {
                EnviarMensaje(ExtraerNumero)
            }
        }
    }
    private fun EnviarMensaje(numero: String) {
        val smsMensaje = SmsManager.getDefault()
        val mensaje = Telefono.mensaje
        if (numero==Telefono.numero){
            smsMensaje.sendTextMessage(numero,null,mensaje,null,null)
        }
    }
}