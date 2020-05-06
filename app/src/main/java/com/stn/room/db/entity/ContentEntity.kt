package com.stn.room.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// tableName 을 입력하지 않으면 Class 명을 Table 명으로 사용하게 된다.
@Entity(tableName = "content")
data class ContentEntity (
    // Room 은 Realm 과 다르게 Primary Key 를 자동으로 증가시켜주는 기능이 있다.
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    // ColumnInfo 는 생략가능
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "content") var content: String = "",
    @ColumnInfo(name = "chooseDate") var chooseDate: String = "",
    @ColumnInfo(name = "created") var created: Date = Date(),
    @ColumnInfo(name = "update") var update: Date = Date()
)