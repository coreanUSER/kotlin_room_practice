package com.stn.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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
        /*if(position < dataSet.size) {
            holder.title.text = dataSet[position].toString()
            holder.content.text = dataSet[position].toString()
            holder.chooseDate.text = dataSet[position].toString()
        }*/
        val item = dataSet[position]
        val listener = View.OnClickListener {
            Toast.makeText(it.context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
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