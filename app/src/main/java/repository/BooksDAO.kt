package repository

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import model.Books

@Dao
interface BooksDAO {
    @Insert
    fun insert(book: Books): Long

    @Query("select * from Books")
    fun listAllAux(): Cursor

    @Query("select * from Books")
    fun listAllCerto(): List<Books>

    @Query("select * from Books")
    fun listAll(): ArrayList<Books>{
        var lista = listAllAux()
        var livros: ArrayList<Books> = ArrayList()

        do {
            val indexT = lista.getColumnIndex("titulo")
            val indexAu = lista.getColumnIndex("autor")
            val indexAn = lista.getColumnIndex("ano")
            val indexN = lista.getColumnIndex("nota")

            var titu = lista.getString(indexT)
            var aut = lista.getString(indexAu)
            var an = lista.getInt(indexAn)
            var not = lista.getInt(indexN)

            livros.add(Books(titu, aut, an, not))
        }while (lista.moveToNext())
        return livros
    }

    @Delete
    fun delete(book: Books): Int

    @Update
    fun update(book: Books): Int

    @Query("select * from Books where titulo = :titulo")
    fun findOne(titulo: String): Books
}