package com.guelmus.roomdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.guelmus.roomdemo.databinding.ActivityMainBinding
import com.guelmus.roomdemo.room.Book
import com.guelmus.roomdemo.ui.BookListAdapter
import com.guelmus.roomdemo.ui.BookViewModel
import com.guelmus.roomdemo.ui.NewBookActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Initialize the adapter and link it up with the associated Rv
        val bookListAdapter = BookListAdapter(this)
        binding.bookRv.apply {
            adapter = bookListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        //Set Layout Manager
//      val layoutManager = LinearLayoutManager(context)
//      layoutManager.orientation = RecyclerView.VERTICAL
//      recyclerView?.layoutManager =layoutManager

        binding.addBookFab.setOnClickListener {
            val intent = Intent(this, NewBookActivity::class.java)
            getResult.launch(intent)
        }

        // Initialize ViewModel
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        bookViewModel.allBooks.observe(this, androidx.lifecycle.Observer {
            //update the UI
            it?.let {
                bookListAdapter.setBooks(it)
            }
        })

        getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                if (result!!.resultCode == Activity.RESULT_OK) {
                    //getExtras i.e. the data from the intent and insert it into the Database

                    //Generate a random id
                    val id = UUID.randomUUID().toString()
                    val authorName = result.data!!.extras!!.getString(NewBookActivity.NEW_AUTHOR).toString()
                    val bookName = result.data!!.extras!!.getString(NewBookActivity.NEW_BOOK).toString()

                    val book = Book(id, authorName, bookName)
                    bookViewModel.insert(book)
                    Toast.makeText(applicationContext, R.string.saved, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, R.string.not_saved, Toast.LENGTH_LONG).show()

                }
            }
    }
}