package com.stn.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class ModifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        var intent = intent
        var id = intent.getLongExtra("id", 0)
        var title = intent.getStringExtra("title")
        var content = intent.getStringExtra("content")
        var chooseDate = intent.getStringExtra("chooseDate")

        var editTitle = findViewById<EditText>(R.id.modify_editTitle)
        var editContent = findViewById<EditText>(R.id.modify_editContent)
        var editChooseDate = findViewById<EditText>(R.id.modify_editChooseDate)

        editTitle.setText(title)
        editContent.setText(content)
        editChooseDate.setText(chooseDate)
    }
}
