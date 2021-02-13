package com.example.ibnsinadoctorappointment.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ibnsinadoctorappointment.data.database.MyDatabase
import com.example.ibnsinadoctorappointment.data.models.LastUpdate
import com.example.ibnsinadoctorappointment.logic.repository.LastUpdateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LastUpdateViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LastUpdateRepository
    val getAllLastUpdates: LiveData<List<LastUpdate>>


    init {
        val LastUpdateDao = MyDatabase.getDatabase(application).lastUpdateDao()
        repository = LastUpdateRepository(LastUpdateDao)

        getAllLastUpdates = repository.getAllLastUpdates
    }

    fun addLastUpdate(LastUpdate: LastUpdate) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLastUpdate(LastUpdate)
        }
    }

    fun updateLastUpdate(LastUpdate: LastUpdate) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLastUpdate(LastUpdate)
        }
    }

    fun deleteLastUpdate(LastUpdate: LastUpdate) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLastUpdate(LastUpdate)
        }
    }

    fun deleteAllLastUpdates() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLastUpdates()
        }
    }

//    fun searchByName(name: String): LiveData<List<LastUpdate>> {
//        return repository.searchByName(name)
//    }
//
//
//    fun searchByBranch(name: String): LiveData<List<LastUpdate>> {
//        return repository.searchByBranch(name)
//    }
//
//
//    fun searchByDept(name: String): LiveData<List<LastUpdate>> {
//        return repository.searchByDept(name)
//    }


}