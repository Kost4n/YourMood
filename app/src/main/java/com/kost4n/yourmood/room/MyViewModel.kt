package com.kost4n.yourmood.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {
    private val recDao by lazy {
        RecDB.getDatabase(application.applicationContext).getRecDao()
    }

    fun addRec(rec: Rec) = viewModelScope.launch(Dispatchers.IO) {
        recDao.addRec(rec)
    }

    private var recs: LiveData<List<Rec>> = recDao.getRecs()

    fun getRecs() = recs

}