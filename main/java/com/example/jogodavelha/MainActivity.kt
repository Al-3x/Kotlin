package com.example.jogodavelha

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Variável para ligar os elementos da interface

    // Vetor bidimensional que representará o tabuleiro de jogo
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    // Qual o Jogador está jogando
    var jogadorAtual = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater) // Infla o layout usando binding
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita a interface de borda a borda
        setContentView(binding.root) // Define a visualização do layout
    }

    // Função associada com todos os botões. @param view é o botão clicado
    fun buttonClick(view: View) {
        // O botão clicado é associado com uma constante
        val buttonSelecionado = view as Button

        // De acordo com o botão clicado, a posição da matriz receberá o Jogador
        when(buttonSelecionado.id){
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][2] = jogadorAtual
        }

        buttonSelecionado.setBackgroundColor(Color.BLUE) // Altera a cor do botão clicado
        buttonSelecionado.isEnabled = false // Desabilita o botão clicado

        // Recebe o jogador vencedor através da função verificaVencedor. @param tabuleiro
        var vencedor = verificaVencedor(tabuleiro)

        if (!vencedor.isNullOrBlank()) { // Se há um vencedor
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show() // Exibe mensagem
            val intent = Intent(this, MainActivity::class.java) // Reinicia a atividade
            startActivity(intent)
            finish()
        }

        // Alterna o jogador
        if (jogadorAtual == "X") {
            buttonSelecionado.setBackgroundResource(R.drawable.bot)
            jogadorAtual = "O"
        } else {
            buttonSelecionado.setBackgroundResource(R.drawable.bot)
            jogadorAtual = "X"
        }

        // Gera uma jogada aleatória para a CPU
        var rX = Random.nextInt(0, 3)
        var rY = Random.nextInt(0, 3)
        var i = 0
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)

            if (tabuleiro[rX][rY] != "X" && tabuleiro[rX][rY] != "O") {
                break
            }

            i++
        }

        tabuleiro[rX][rY] = "O" // Marca a jogada da CPU

        val posicao = rX * 3 + rY // Calcula a posição na matriz

        // Atualiza a interface para refletir a jogada da CPU
        when(posicao){
            0 -> {
                binding.buttonZero.setBackgroundResource(R.drawable.vasc)
                binding.buttonZero.isEnabled = false
            }
            1 -> {
                binding.buttonUm.setBackgroundResource(R.drawable.vasc)
                binding.buttonUm.isEnabled = false
            }
            2 -> {
                binding.buttonDois.setBackgroundResource(R.drawable.vasc)
                binding.buttonDois.isEnabled = false
            }
            3 -> {
                binding.buttonTres.setBackgroundResource(R.drawable.vasc)
                binding.buttonTres.isEnabled = false
            }
            4 -> {
                binding.buttonQuatro.setBackgroundResource(R.drawable.vasc)
                binding.buttonQuatro.isEnabled = false
            }
            5 -> {
                binding.buttonCinco.setBackgroundResource(R.drawable.vasc)
                binding.buttonCinco.isEnabled = false
            }
            6 -> {
                binding.buttonSeis.setBackgroundResource(R.drawable.vasc)
                binding.buttonSeis.isEnabled = false
            }
            7 -> {
                binding.buttonSete.setBackgroundResource(R.drawable.vasc)
                binding.buttonSete.isEnabled = false
            }
            8 -> {
                binding.buttonOito.setBackgroundResource(R.drawable.vasc)
                binding.buttonOito.isEnabled = false
            }
        }

        vencedor = verificaVencedor(tabuleiro) // Verifica novamente se há um vencedor

        if (!vencedor.isNullOrBlank()) { // Se há um vencedor
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show() // Exibe mensagem
            val intent = Intent(this, MainActivity::class.java) // Reinicia a atividade
            startActivity(intent)
            finish()
        }
    }

    // Função para verificar o vencedor do jogo
    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
        // Verifica linhas e colunas
        for (i in 0 until 3) {
            // Verifica se há três itens iguais na linha
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            // Verifica se há três itens iguais na coluna
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }
        // Verifica a quantidade de jogadores
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if (valor == "X" || valor == "O") {
                    empate++
                }
            }
        }
        // Se existem 9 jogadas e não há três letras iguais, houve um empate
        if (empate == 9) {
            return "Empate"
        }
        // Nenhum vencedor
        return null
    }
}
