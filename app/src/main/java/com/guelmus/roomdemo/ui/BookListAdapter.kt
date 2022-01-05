package com.guelmus.roomdemo.ui
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guelmus.roomdemo.R
import com.guelmus.roomdemo.databinding.ListItemBookBinding
import com.guelmus.roomdemo.room.Book


/**
 * Adapter class for the BookRecycleView to display the list of Books.
 * @param context : context of the calling activity of fragment
 * */
class BookListAdapter(private val context: Context) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

	//List of books to be displayed on the recycleView
	private var bookList : List<Book> = mutableListOf()

	/**Create the required viewHolder object for each itemView to be displayed on the screen*/
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
		Log.i("BookListAdapter", "onCreateViewHolder: ViewHolder created")

		val itemBinding = ListItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return BookViewHolder(itemBinding)
	}

	/**Bind the data for each item in the list*/
	override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
		Log.i("BookListAdapter", "onBindViewHolder: position: $position")
		//Get the book object at the current position
		val book = bookList[position]
		holder.bind(book)
	}

	/**Get the count of Data in the List*/
	override fun getItemCount(): Int = bookList.size

	/** Populate Books to the RecyclerView*/
	fun setBooks(books : List<Book>){
		bookList = books
		notifyDataSetChanged()
	}

	/**View Holder for items list*/
	inner class BookViewHolder(private val itemBinding: ListItemBookBinding) : RecyclerView.ViewHolder(itemBinding.root) {

		///Bind data with the view objects(populate data)
		fun bind(book: Book) {
			with(itemBinding){
				tvAuthor.text = book.author
				tvBook.text = book.book
			}
		}

	}
}



