package com.example.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.jogodavelha.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    // Declaração da variável de ligação para a ActivityMenuBinding
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa a variável de ligação com o layout da atividade
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root) // Define o layout raiz da atividade

        // Configura o clique do botão para iniciar a PVPActivity
        binding.button.setOnClickListener {
            val intent = Intent(this, PVPActivity::class.java)
            startActivity(intent) // Inicia a próxima atividade
        }

        // Configura o clique do botão2 para iniciar a MainActivity
        binding.button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // Inicia a próxima atividade
        }
    }
}
