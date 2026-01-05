package br.com.zig.harrypotterdbapp.controller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.zig.harrypotterdbapp.R
import br.com.zig.harrypotterdbapp.data.dao.HPCharDAO
import br.com.zig.harrypotterdbapp.model.HPChar

class NewHPChar : AppCompatActivity() {

    private lateinit var hpCharDAO: HPCharDAO
    private var hpCharID: Int = 0
    private lateinit var etName: EditText
    private lateinit var etHouse: EditText
    private lateinit var etAncestry: EditText
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_hpchar)

        hpCharDAO = HPCharDAO(this)

        etName = findViewById(R.id.etName)
        etHouse = findViewById(R.id.etHouse)
        etAncestry = findViewById(R.id.etAncestry)
        btnDelete = findViewById(R.id.btnDelete)

        //Verifica se estamos em modo de edição (personagem existe)
        hpCharID = intent.getIntExtra("charID", 0)
        if (hpCharID != 0) {
            btnDelete.visibility = Button.VISIBLE
            editChar()
        }
    }

    fun saveChar(view: View) {
        if (etName.text.isNotEmpty() && etHouse.text.isNotEmpty() && etAncestry.text.isNotEmpty()) {
            if (hpCharID == 0) {
                // Inserção de um novo personagem
                val newChar = HPChar(
                    name = etName.text.toString(),
                    house = etHouse.text.toString(),
                    ancestry = etAncestry.text.toString()
                )
                hpCharDAO.addHPChar(newChar)
                Toast.makeText(this, "Personagem adicionado", Toast.LENGTH_SHORT).show()
            } else {
                // Atualização de personagem existente
                val updateChar = HPChar(
                    id = hpCharID,
                    name = etName.text.toString(),
                    house = etHouse.text.toString(),
                    ancestry = etAncestry.text.toString()
                )
                hpCharDAO.updateChar(updateChar)
                Toast.makeText(this, "Personagem atualizado", Toast.LENGTH_SHORT).show()
            }
            finish() // Volta para a MainActivity
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    // Carrega o personagem existente nos campos de edição
    private fun editChar() {
        val char = hpCharDAO.getCharById(hpCharID)
        char?.let{
            etName.setText(char.name)
            etHouse.setText(char.house)
            etAncestry.setText(char.ancestry)
        }
    }

    fun deleteChar(view: View) {
        hpCharDAO.deleteChar(hpCharID)
        Toast.makeText(this, "Personagem excluído", Toast.LENGTH_SHORT).show()
        finish()
    }
}