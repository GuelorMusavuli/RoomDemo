package com.guelmus.roomdemo.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    fun saveBook(book : Book)

//    @Query("SELECT * FROM books")
//    fun getAllBooks():LiveData<List<Book>>

    //the above but using a property
    @get:Query("SELECT * FROM books")
    val allBooks : LiveData<List<Book>>
}