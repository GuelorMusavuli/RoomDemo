package com.guelmus.roomdemo.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.guelmus.roomdemo.databinding.ActivityNewBookBinding

class NewBookActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityNewBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveBook()
    }

    private fun saveBook(){
        binding.bAdd.setOnClickListener {

            //To be passed to the MainActivity
            val resultIntent = Intent()

            if (TextUtils.isEmpty(binding.etAuthorName.text) ||
                    (TextUtils.isEmpty(binding.etBookName.text))){
                setResult(Activity.RESULT_CANCELED, resultIntent )
            }else{
                val author = binding.etAuthorName.text.toString()
                val book = binding.etBookName.text.toString()

                resultIntent.putExtra(NEW_AUTHOR, author)
                resultIntent.putExtra(NEW_BOOK, book)
                setResult(Activity.RESULT_OK, resultIntent)
            }
            finish()
        }
    }

    companion object {
        const val NEW_AUTHOR = "new_author"
        const val NEW_BOOK = "new_book"
    }
}