package com.stn.room

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            showDate()
        }

        saveButton.setOnClickListener {
            val newContent = ContentEntity()
            newContent.title = titleInput.text.toString()
            newContent.content = contentInput.text.toString()
            newContent.chooseDate = dateInput.text.toString()

            val r = Runnable {
                var contentDb = AppDatabase.getInstance(this)
                contentDb?.contentDao().insert(newContent)
            }

            val thread = Thread(r)
            thread.start()
        }
    }

    private fun showDate() {
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
