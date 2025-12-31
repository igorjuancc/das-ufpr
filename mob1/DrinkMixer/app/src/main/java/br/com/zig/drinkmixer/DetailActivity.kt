package br.com.zig.drinkmixer

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val drinks = arrayOf("Batida de Sonho de Valsa", "Drink dos Deuses", "Quentão")

        val ingredientsBatida = "3 bombons sonho de valsa\n" +
                "1/2 garrafa de pinga\n" +
                "1 lata de leite condensado\n" +
                "1 latinha de guaraná"
        val prepareBatida =
            "Bater tudo no liquidificador, ficando os chocolates crocantes por cima.\n" +
                    "Servir gelado."

        val ingredientsDrinkSonhos = "1 vidro (100 ml) de leite de coco\n" +
                "1 copo (100 ml) de suco de maracuja\n" +
                "1 copo(100 ml) de groselha\n" +
                "Cerca de 1 lata de leite condensado\n" +
                "1 copo (100 ml) de cachaça\n" +
                "Gelo picado"
        val prepareDrinkSonhos =
            "Bater os 5 primeiros ingredientes no liquidificador e acrescentar o gelo na hora de servir."

        val ingredientsQuentao = "1 garrafa de cachaça (600 ml)\n" +
                "600 ml de água\n" +
                "500 g de açúcar\n" +
                "casca de 2 laranjas\n" +
                "casca de 1 limão\n" +
                "50 g de gengibre em pedacinhos\n" +
                "cravo-da-índia a gosto\n" +
                "canela em pau a gosto\n" +
                "1 maçã cortada em pedacinhos"
        val prepareQuentao =
            "Colocar em uma panela grande o açúcar, as cascas de laranja, as cascas de limão, o gengibre, o cravo e a canela.\n" +
                    "Quando o açúcar estiver derretendo, colocar a cachaça e a água, deixando cozinhar por 20 a 25 minutos em fogo médio.\n" +
                    "Filtre e, após, coloque a maçã picadinha.\n" +
                    "Manter no fogo, após o preparo."

        val ingredients = arrayOf(ingredientsBatida, ingredientsDrinkSonhos, ingredientsQuentao)
        val prepare = arrayOf(prepareBatida, prepareDrinkSonhos, prepareQuentao)

        val nomeOutPut = findViewById<TextView>(R.id.nomeOutPut)
        val igredientesOutPut = findViewById<TextView>(R.id.igredientesOutPut)
        val preparoOutPut = findViewById<TextView>(R.id.preparoOutPut)

        val bundle = intent.extras
        val drinkIndex = if (bundle?.getInt("drinkIndex") != null)
            bundle.getInt("drinkIndex")
        else
            0

        nomeOutPut.text = drinks[drinkIndex]
        igredientesOutPut.text = ingredients[drinkIndex]
        preparoOutPut.text = prepare[drinkIndex]

        igredientesOutPut.movementMethod = android.text.method.ScrollingMovementMethod()
        preparoOutPut.movementMethod = android.text.method.ScrollingMovementMethod()
    }
}