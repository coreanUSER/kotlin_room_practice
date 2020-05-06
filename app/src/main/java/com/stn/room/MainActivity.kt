package com.stn.room

import android.content.pm.ApplicationInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BottomAppBar Binding
        val bottomAppBar: BottomAppBar = findViewById(R.id.bar)
        setSupportActionBar(bottomAppBar)

        fab.setOnClickListener {
            Toast.makeText(applicationContext, "클릭", Toast.LENGTH_SHORT).show()
        }

    }
}
