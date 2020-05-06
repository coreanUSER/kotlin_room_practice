package com.stn.room.db.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stn.room.db.dao.ContentDao

abstract class ContentDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    // Kotlin 에서는 static 이 존재하지 않으므로, 동일한 역활을 해주는 companion object 를 사용.
    companion object {
        private val DB_NAME = "content.db"
        private var INSTANCE: ContentDatabase? = null

        fun getInstance(context: Context): ContentDatabase? {
            return INSTANCE ?: synchronized(ContentDao::class.java) {
                INSTANCE ?: buildDataBase(
                        context
                    )
            }
        }

        private fun buildDataBase(context: Context): ContentDatabase {
            return Room.databaseBuilder(context.applicationContext, ContentDatabase::class.java, DB_NAME)
                .build().also { INSTANCE = it }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}