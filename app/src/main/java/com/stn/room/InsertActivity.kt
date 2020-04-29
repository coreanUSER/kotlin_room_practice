package com.stn.room

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.stn.room.db.AppDatabase
import com.stn.room.db.dao.ContentDao
import com.stn.room.db.entity.ContentEntity
import kotlinx.android.synthetic.main.activity_insert.*
import java.util.*

class InsertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        dateButton.setOnClickListener {
            showDatePickerDialog()
        }

        saveButton.setOnClickListener {
            var title: String? = titleInput.text.toString()
            var content: String? = contentInput.text.toString()
            var chooseDate: String? = dateInput.text.toString()

            val newContent = ContentEntity()
            if(title != null) {
                newContent.title = title
            } else {
                Toast.makeText(this, "Title is null", Toast.LENGTH_SHORT).show()
            }

            if(content != null) {
                newContent.content = content
            } else {
                Toast.makeText(this, "Content is null", Toast.LENGTH_SHORT).show()
            }

            if(chooseDate != null) {
                newContent.chooseDate = chooseDate
            } else {
                Toast.makeText(this, "ChooseDate is null", Toast.LENGTH_SHORT).show()
            }

            if(title != null && content != null && chooseDate != null) {
                val r = Runnable {
                    var contentDb = AppDatabase.getInstance(this)
                    contentDb?.contentDao()?.insert(newContent)!!
                }

                val thread = Thread(r)
                thread.start()
            } else {
                Toast.makeText(this, "Input Data Is Null", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
            dateInput.setText("$year-$month-$dayOfMonth")
        }, year, month, day)
        datePickerDialog.show()
    }
}
