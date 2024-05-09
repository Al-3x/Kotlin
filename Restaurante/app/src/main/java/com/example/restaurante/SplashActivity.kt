package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            //atribui um valor
            val i = intent
            //vai iniciar a atividade pedido activity
            val j = Intent(this,PedidoActivity::class.java)
            j.putExtras(i)
            //vai iniciar a atividade
            startActivity(j)
        },2000)

    }
}
