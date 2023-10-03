package database

import androidx.room.Database
import androidx.room.RoomDatabase
import model.Books
import repository.BooksDAO

@Database(entities = [Books::class], version = 1)
abstract class DataBase:RoomDatabase() {
    abstract fun booksDAO(): BooksDAO
}