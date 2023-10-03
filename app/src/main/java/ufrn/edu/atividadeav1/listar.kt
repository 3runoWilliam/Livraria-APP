package ufrn.edu.atividadeav1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import database.DataBase
import model.Books
import ufrn.edu.atividadeav1.databinding.ActivityListarBinding

class listar : AppCompatActivity() {
    private lateinit var binding: ActivityListarBinding
    private lateinit var db: DataBase

    var coun = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listar)

        db = Room.databaseBuilder(
            this,
            DataBase::class.java,
            "books"
        ).allowMainThreadQueries().build()
    }

    override fun onResume() {
        super.onResume()

        var livros = db.booksDAO().listAllCerto()


        binding.nomeAno.text = livros[coun].ano.toString()
        binding.nomeAutor.text = livros[coun].autor
        binding.nomeTitulo.text = livros[coun].titulo
        binding.nomeNota.text = livros[coun].nota.toString()

        binding.btnProximo.setOnClickListener {
            if (coun < livros.size - 1) {
                Log.i("entrou no if", coun.toString())
                coun++
                onResume()
            }else {
                Log.i("entrou no else", coun.toString())
                coun = livros.size
                Toast.makeText(this,"Acabou os livros", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnAnterior.setOnClickListener {
            if (coun > 0) {
                coun--
                onResume()
            }else{
                coun = 0
                Toast.makeText(this,"Este e o primeiro livro", Toast.LENGTH_SHORT).show()
            }
        }


    }

    @SuppressLint("Range")
    private fun getLivrosFromDatabase(): List<Books> {
        val cursor = db.booksDAO().listAllAux()
        val livros: MutableList<Books> = mutableListOf()

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val titulo = cursor.getString(cursor.getColumnIndex("titulo"))
                val autor = cursor.getString(cursor.getColumnIndex("autor"))
                val ano = cursor.getInt(cursor.getColumnIndex("ano"))
                val nota = cursor.getInt(cursor.getColumnIndex("nota"))

                livros.add(Books(titulo, autor, ano, nota))
            } while (cursor.moveToNext())
            cursor.close()
        }

        return livros
    }
}


