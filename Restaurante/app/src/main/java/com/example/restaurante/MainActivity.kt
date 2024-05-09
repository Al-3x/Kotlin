package com.example.restaurante

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurante.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    //Aqui, estamos substituindo o método onCreate da classe base
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val username = intent.extras?.getString("username")

        //Aqui, estamos verificando se a variável username não é nula e não está vazia (ou seja, tem algum valor)
        if(!username.isNullOrEmpty()){
            binding.textOla.setText("Olá $username")
        }

        //Aqui, estamos configurando um ouvinte de clique para o botão chamado buttonFechar
        binding.buttonFechar.setOnClickListener {
            finishAffinity()
        }

        //Vai mostrar a quantidade de itens selecionados e mostrar o valor
        binding.buttonPedir.setOnClickListener {
            val i = Intent(this, SplashActivity::class.java)
            i.putExtra("quantidadePizza", binding.editQuantidadePizza.text.toString())
            i.putExtra("quantidadeSalada", binding.editQuantidadeSalada.text.toString())
            i.putExtra("quantidadeHamburguer", binding.editQuantidadeHamburguer.text.toString())
            startActivity(i)
            finish()
        }


        binding.checkPizza.setOnClickListener {
            if(binding.checkPizza.isChecked()){
                binding.editQuantidadePizza.setText("1")
                binding.textPrecoPizza.visibility = View.VISIBLE
            }else{
                binding.editQuantidadePizza.setText("0")
                binding.textPrecoPizza.visibility = View.GONE
            }
        }

        binding.checkSalada.setOnClickListener {
            if(binding.checkSalada.isChecked()){
                binding.editQuantidadeSalada.setText("1")
                binding.textPrecoSalada.visibility = View.VISIBLE
            }else{
                binding.editQuantidadeSalada.setText("0")
                binding.textPrecoSalada.visibility = View.GONE
            }
        }

        binding.checkHamburger.setOnClickListener {
            if(binding.checkHamburger.isChecked()){
                binding.editQuantidadeHamburguer.setText("1")
                binding.textPrecoHamburguer.visibility = View.VISIBLE
            }else{
                binding.editQuantidadeHamburguer.setText("0")
                binding.textPrecoHamburguer.visibility = View.GONE
            }
        }

    }
}
