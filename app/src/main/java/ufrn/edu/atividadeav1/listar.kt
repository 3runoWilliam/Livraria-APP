package ufrn.edu.atividadeav1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import database.DataBase
import model.Books
import ufrn.edu.atividadeav1.databinding.ActivityListarBinding

class listar : AppCompatActivity() {
    private lateinit var binding: ActivityListarBinding
    private lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listar)

        db = Room.databaseBuilder(
            this,
            DataBase::class.java,
            "books"
        ).allowMainThreadQueries().build()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val livros = getLivrosFromDatabase()

        val adapter = LivroAdapter(livros)
        recyclerView.adapter = adapter
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


