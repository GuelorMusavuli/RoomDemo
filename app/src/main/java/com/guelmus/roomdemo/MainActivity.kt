package com.guelmus.roomdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.guelmus.roomdemo.databinding.ActivityMainBinding
import com.guelmus.roomdemo.room.Book
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var bookViewModel : BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBookFab.setOnClickListener {
            val intent = Intent(this, NewBookActivity::class.java)
            getResult.launch(intent)
        }

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                if (result!!.resultCode == Activity.RESULT_OK) {
                    //getExtras i.e. the data from the intent and insert it into the Database

                    //Generate a random id
                    val id = UUID.randomUUID().toString()
                    val authorName =
                        result.data!!.extras!!.getString(NewBookActivity.NEW_AUTHOR).toString()
                    val bookName = result.data!!.getStringExtra(NewBookActivity.NEW_BOOK).toString()

                    val book = Book(id, authorName, bookName)
                    bookViewModel.insert(book)
                    Toast.makeText(applicationContext, R.string.saved, Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(applicationContext, R.string.not_saved, Toast.LENGTH_LONG).show()

                }
            }
    }


}