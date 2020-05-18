package com.stn.room

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import com.stn.room.db.entity.ContentEntity
import com.stn.room.db.viewModel.ContentViewModel
import java.util.*

class InsertActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        // Date Picker 설정
        val insertChooseDate = findViewById<EditText>(R.id.insert_editChooseDate)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        insertChooseDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                insertChooseDate.setText("$year-$monthOfYear-$dayOfMonth") } , year, month, day)
            dpd.show()
        }

        // Back Button 설정
        val btnCancel = findViewById<Button>(R.id.insert_btnCancel)
        btnCancel.setOnClickListener {
            onBackPressed()
        }

        // Insert Button 설정
        val btnInsert = findViewById<Button>(R.id.insert_btnInsert)
        btnInsert.setOnClickListener {
            val editTitle = findViewById<EditText>(R.id.insert_editTitle).text.toString()
            val editContent = findViewById<EditText>(R.id.insert_editContent).text.toString()
            val chooseDate = findViewById<EditText>(R.id.insert_editChooseDate).text.toString()
            val content = ContentEntity(0, editTitle, editContent, chooseDate, Date().toString(), Date().toString())
            val contentViewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
            contentViewModel.insert(content) { finish() }
        }
    }
}

