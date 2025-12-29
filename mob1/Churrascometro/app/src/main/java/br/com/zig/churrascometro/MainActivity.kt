package br.com.zig.churrascometro

import android.os.Bundle
import android.widget.SeekBar
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

        val sbMen = findViewById<SeekBar>(R.id.seekBarMan)
        val sbGirl = findViewById<SeekBar>(R.id.seekBarGirl)
        val menQtt = findViewById<TextView>(R.id.textViewMan)
        val girlQtt = findViewById<TextView>(R.id.textViewGirl)

        sbMen?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                menQtt.text = progress.toString()
                calculate(progress, sbGirl.progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        sbGirl?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                girlQtt.text = progress.toString()
                calculate(sbMen.progress, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    fun calculate(menQtt: Int, girlQtt: Int) {
        val outPutSausage = findViewById<TextView>(R.id.textViewLing)
        val outPutMeat = findViewById<TextView>(R.id.textViewCarne)
        val sausageQtt: Double = ( (menQtt * 250.0) + (girlQtt * 150.0) ) / 1000
        val meatQtt: Double = ( (menQtt * 450.0) + (girlQtt * 300.0) ) / 1000

        outPutMeat.text = meatQtt.toString() + " Kg";
        outPutSausage.text = sausageQtt.toString() + " Kg";
    }
}