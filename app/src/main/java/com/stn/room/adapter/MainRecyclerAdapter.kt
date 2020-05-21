package com.stn.room.adapter

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.stn.room.MainActivity
import com.stn.room.ModifyActivity
import com.stn.room.R
import com.stn.room.db.entity.ContentEntity
import kotlinx.android.synthetic.main.content.view.*

class MainRecyclerAdapter ( private val dataSet: ArrayList<ContentEntity> )
    : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    // Recycler View 초기화 시 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutView= LayoutInflater.from(parent.context).inflate(R.layout.content, parent, false)
        return ViewHolder(layoutView)
    }

    override fun getItemCount(): Int = dataSet.size

    // View 생성 시 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        val listener = View.OnClickListener {
            var intent = Intent(it.context, ModifyActivity::class.java)
            intent.putExtra("id", item.id)
            intent.putExtra("title", item.title)
            intent.putExtra("content", item.content)
            intent.putExtra("chooseDate", item.chooseDate)
            it.context.startActivity(intent)
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    // View 에 데이터 설정
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: ContentEntity) {
            view.title.text = item.title
            view.content.text = item.content
            view.chooseDate.text = item.chooseDate
            view.setOnClickListener(listener)
        }
    }
}