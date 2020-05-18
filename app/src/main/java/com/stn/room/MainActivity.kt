package com.stn.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.stn.room.db.entity.ContentEntity
import com.stn.room.db.viewModel.ContentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar -> ActionBar 설정
        setSupportActionBar(toolBar)

        // Recycler View Data 설정
        var dataSet = ArrayList<ContentEntity>()

        // 저장된 데이터 가져오기
        // 데이터가 변화될 때마다 onChanged() Call Back
        val contentViewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        contentViewModel.getAll().observe(this, androidx.lifecycle.Observer {
            if(it.isNotEmpty()) {
                dataSet.clear()
                dataSet.addAll(it!!)
            } else {
                var contentEntity: ContentEntity = ContentEntity(0, "Sample Title", "Sample Content", Date().toString(), Date().toString(), Date().toString())
                dataSet.add(contentEntity)
            }
            recyclerView.adapter = MainRecyclerAdapter(dataSet)
        })

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, InsertActivity::class.java)
            startActivity(intent)
        }
    }
}
