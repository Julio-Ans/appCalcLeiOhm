package com.example.appcalcleiohm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


            val edtTens = findViewById<EditText>(R.id.edtTens)
            val edtResist = findViewById<EditText>(R.id.edtResist)
            val edtCorre = findViewById<EditText>(R.id.edtCorre)
            val btnCalc = findViewById<Button>(R.id.btnCalc)
            val txtResult = findViewById<TextView>(R.id.txvResult)



            fun calcular(tensaoStr: String, resistStr: String, correntStr: String): String {

                // Conta quantos campos estão preenchidos
                val camposPreenchidos = listOf(tensaoStr, resistStr, correntStr).count { it.isNotEmpty() }

                // Validação básica
                if (camposPreenchidos != 2) throw Exception("Preencha exatamente dois campos.")

                // Converte para Double (não-nulo)
                val t = if (tensaoStr.isNotEmpty()) tensaoStr.toDouble() else 0.0
                val r = if (resistStr.isNotEmpty()) resistStr.toDouble() else 0.0
                val c = if (correntStr.isNotEmpty()) correntStr.toDouble() else 0.0

                // Determina qual cálculo fazer
                return when {
                    tensaoStr.isEmpty() -> "Tensão: ${"%.2f".format(r * c)} V"
                    resistStr.isEmpty() -> "Resistência: ${"%.2f".format(t / c)} Ω"
                    else -> "Corrente: ${"%.2f".format(t / r)} A"
                }
            }


            btnCalc.setOnClickListener {
                try {
                    // Passa os textos diretamente sem verificar nulos
                    val resultado = calcular(
                        edtTens.text.toString(),
                        edtResist.text.toString(),
                        edtCorre.text.toString()
                    )
                    txtResult.text = resultado
                } catch (e: Exception) {
                    txtResult.text = "Erro: ${e.message}"
                }
            }
        }





    }

