package br.com.zig.frasedodia

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun quote(view: View) {
        val quotes = arrayOf(
            "Se der errado, pelo menos você ganhou experiência… e uma boa história pra contar",
            "Trabalhe duro hoje para poder reclamar com mais propriedade amanhã",
            "O importante não é chegar rápido, é chegar sem quebrar tudo no caminho",
            "Persistência é tentar de novo depois de um café",
            "Você não precisa ser perfeito, só melhor que ontem (ou pelo menos tentar)",
            "Sonhe grande, mas comece pequeno — tipo levantando da cadeira",
            "Fracassar faz parte do sucesso, infelizmente ninguém pulou essa etapa",
            "Se parece difícil, provavelmente vale a pena (ou dá um ótimo meme)",
            "Não desista agora, o bug pode estar só na linha de cima",
            "Faça o seu melhor hoje para poder procrastinar com a consciência tranquila amanhã"
        )

        val index = (0..9).random()
        val textView = findViewById<TextView>(R.id.textViewOutPut)
        textView.text = quotes[index]
    }
}