package com.stn.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.stn.room.db.entity.ContentEntity

@Dao
interface ContentDao {
    @Query("SELECT * FROM content")
    fun getAll(): LiveData<List<ContentEntity>>

    @Query("SELECT * FROM content WHERE id = :id")
    fun getById(id: Long): LiveData<ContentEntity>

    @Query("DELETE FROM content WHERE id = :id")
    fun deleteById(id: Long)

    @Delete
    fun delete(contentEntity: ContentEntity)

    @Insert
    fun insert(contentEntity: ContentEntity)

    @Update
    fun update(contentEntity: ContentEntity)
}