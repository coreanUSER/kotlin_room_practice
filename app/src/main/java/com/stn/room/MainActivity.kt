package com.stn.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stn.room.db.entity.ContentEntity
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

        for(i in 1..50) {
            var contentEntity: ContentEntity = ContentEntity(0, "$i", "Content", "Date", Date(), Date())
            dataSet.add(contentEntity)
        }
        recyclerView.adapter = MainRecyclerAdapter(dataSet)
    }
}
