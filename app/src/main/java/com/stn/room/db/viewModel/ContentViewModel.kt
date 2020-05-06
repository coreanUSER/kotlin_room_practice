package com.stn.room.db.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.stn.room.db.entity.ContentEntity
import com.stn.room.db.repository.ContentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ContentViewModel(application: Application): AndroidViewModel(application) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val repository: ContentRepository by lazy {
        ContentRepository(application)
    }

    private val contents: LiveData<List<ContentEntity>> by lazy {
        repository.getAll()
    }

    fun getAll() = contents

    fun getById(id: Long): LiveData<ContentEntity> {
        return repository.getById(id)
    }

    fun insert(contentEntity: ContentEntity, next: () -> Unit) {
        disposable.add( repository.insert(contentEntity)
            //  Schedulers.io() : 비동기 스레드 사용 - 데이터 가공
            .subscribeOn(Schedulers.io())
            // AndroidSchedulers.mainThread() : UI 스레드 사용 - 데이터 처리
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
        )
    }

    fun update(contentEntity: ContentEntity, next: () -> Unit) {
        disposable.add( repository.update(contentEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
        )
    }

    fun delete(id: Long, next: () -> Unit) {
        disposable.add( repository.deleteById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}