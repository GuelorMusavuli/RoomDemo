package com.guelmus.roomdemo.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface BookDao {

    @Insert
    fun saveBook(book : Book)

}