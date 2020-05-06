package com.stn.room.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.stn.room.db.dao.ContentDao
import com.stn.room.db.database.ContentDatabase
import com.stn.room.db.entity.ContentEntity
import io.reactivex.Observable

class ContentRepository(application: Application) {

    private val contentDao: ContentDao by lazy {
        val db = ContentDatabase.getInstance(application)!!
        db.contentDao()
    }

    private val contents: LiveData<List<ContentEntity>> by lazy {
        contentDao.getAll()
    }

    fun getAll(): LiveData<List<ContentEntity>> {
        return contents
    }

    fun getById(id: Long): LiveData<ContentEntity> {
        return contentDao.getById(id)
    }

    fun insert(contentEntity: ContentEntity): Observable<Unit> {
        // AsyncTask 비동기 스레드 대신 RxJava2로 구현
        // Callable : 구독이 발생하였을 때 Call 함수가 호출되는 API
        return Observable.fromCallable { contentDao.insert(contentEntity) }
    }

    fun deleteById(id: Long): Observable<Unit> {
        return Observable.fromCallable { contentDao.deleteById(id) }
    }

    fun update(contentEntity: ContentEntity): Observable<Unit> {
        return Observable.fromCallable { contentDao.update(contentEntity) }
    }

}