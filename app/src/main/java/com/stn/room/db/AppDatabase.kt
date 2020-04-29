package com.stn.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stn.room.db.dao.ContentDao

abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    // Kotlin 에서는 static 이 존재하지 않으므로, 동일한 역활을 해주는 companion object 를 사용.
    companion object {
        private val DB_NAME = "room-db"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context)
            }
        }

        fun buildDataBase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }

        fun destroyInstance() {
            instance = null
        }
    }
}