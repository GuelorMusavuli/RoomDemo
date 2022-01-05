package com.guelmus.roomdemo.ui

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.guelmus.roomdemo.room.Book
import com.guelmus.roomdemo.room.BookDao
import com.guelmus.roomdemo.room.BookRoomDatabase


class BookViewModel(application: Application): AndroidViewModel(application){

	private val bookDao: BookDao
	val allBooks : LiveData<List<Book>>

	init {
		//Db initialization
		val bookDb = BookRoomDatabase.getDatabase(application)
		bookDao = bookDb!!.bookDao()
		allBooks = bookDao.allBooks
	}

	fun insert(book: Book) {
		InsertAsyncTask(bookDao).execute(book)
	}

	companion object {
		private class InsertAsyncTask(private val bookDao: BookDao) : AsyncTask<Book, Void, Void>() {

			override fun doInBackground(vararg books: Book): Void? {
				bookDao.saveBook(books[0])
				return null
			}

		}
	}
}