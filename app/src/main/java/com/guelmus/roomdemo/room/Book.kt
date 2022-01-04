package com.guelmus.roomdemo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "books")
data class Book (
        @PrimaryKey
        val id : String,

        @ColumnInfo(name = "author_name")
        val author : String,

        @ColumnInfo(name = "book")
        val book : String
        )
