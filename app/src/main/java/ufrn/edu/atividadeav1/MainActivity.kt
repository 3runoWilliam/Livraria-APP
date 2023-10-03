package ufrn.edu.atividadeav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ufrn.edu.atividadeav1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cadastrar.setOnClickListener {
            val intent = Intent(this, cadastrar::class.java)

            startActivity(intent)
        }

        binding.listar.setOnClickListener {
            val intent = Intent(this, listar::class.java)

            startActivity(intent)
        }
    }
}
