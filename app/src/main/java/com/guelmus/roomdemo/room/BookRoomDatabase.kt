package com.guelmus.roomdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookRoomDatabase : RoomDatabase(){
    abstract fun bookDao() : BookDao

    companion object{

        @Volatile // ensure the instance's value always up-to-date to all execution set
        private var bookRoomInstance : BookRoomDatabase? = null

        fun getDatabase(context : Context) : BookRoomDatabase?{

            //Only one set of execution(single thread) can access the db at a time.
            synchronized(this){

                var instance = bookRoomInstance

                if(instance == null){
                    instance = Room.databaseBuilder(
                                context.applicationContext,
                                BookRoomDatabase::class.java,
                             "book_database "
                         )
                        .build()
                        bookRoomInstance = instance
                }
                return instance
            }

        }
    }
}