@file:Suppress("ClassName")

package ufrn.edu.atividadeav1
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import database.DataBase
import model.Books
import ufrn.edu.atividadeav1.databinding.ActivityCadastrarBinding

class cadastrar : AppCompatActivity() {

    private lateinit var db: DataBase
    private lateinit var binding: ActivityCadastrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            this,
            DataBase::class.java,
            "books"
        ).allowMainThreadQueries().build()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cadastrar)

        binding.btnSalvar.setOnClickListener {
            val titulo = binding.tituloText.text.toString()
            val autor = binding.autorText.text.toString()
            val ano = binding.anoText.text.toString().toIntOrNull() ?: 0
            val nota = binding.notaRating.rating.toInt() //.toString().toIntOrNull() //?: 0

            if (titulo.isNotEmpty() && autor.isNotEmpty()) {
                val livro = Books(titulo, autor, ano, nota)

                db.booksDAO().insert(livro)

                binding.tituloText.text.clear()
                binding.autorText.text.clear()
                binding.anoText.text.clear()


                Toast.makeText(this, "livro inserido", Toast.LENGTH_SHORT).show()
            } else {
                // Exibir mensagem de erro
            }
        }

        binding.btnCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}