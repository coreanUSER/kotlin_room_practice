package com.stn.room.db.dao

import androidx.room.*
import com.stn.room.db.entity.ContentEntity

@Dao
interface ContentDao {
    @Query("SELECT * FROM content")
    fun getAll(): List<ContentEntity>

    @Query("SELECT * FROM content WHERE id = :id")
    fun getById(id: Long): ContentEntity

    @Insert
    fun insert(contentEntity: ContentEntity)

    @Update
    fun update(contentEntity: ContentEntity)

    @Delete
    fun delete(contentEntity: ContentEntity)
}