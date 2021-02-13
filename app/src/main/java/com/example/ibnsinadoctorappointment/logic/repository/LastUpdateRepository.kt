package com.example.ibnsinadoctorappointment.logic.repository

import androidx.lifecycle.LiveData
import com.example.ibnsinadoctorappointment.data.models.LastUpdate
import com.example.ibnsinadoctorappointment.logic.dao.LastUpdateDao

class LastUpdateRepository (private val lastUpdateDao: LastUpdateDao) {
    val getAllLastUpdates: LiveData<List<LastUpdate>> = lastUpdateDao.getAllLastUpdates()

    suspend fun addLastUpdate(lastUpdate: LastUpdate) {
        lastUpdateDao.addLastUpdate(lastUpdate)
    }

    suspend fun updateLastUpdate(lastUpdate: LastUpdate) {
        lastUpdateDao.updateLastUpdate(lastUpdate)
    }

    suspend fun deleteLastUpdate(lastUpdate: LastUpdate) {
        lastUpdateDao.deleteLastUpdate(lastUpdate)
    }

    suspend fun deleteAllLastUpdates() {
        lastUpdateDao.deleteAll()
    }




//    fun searchByName(name : String): LiveData<List<LastUpdate>>{
//        return lastUpdateDao.searchByName(name)
//    }
//
//
//
//    fun searchByBranch(name : String): LiveData<List<LastUpdate>>{
//        return lastUpdateDao.searchByBranch(name)
//    }
//
//
//
//    fun searchByDept(name : String): LiveData<List<LastUpdate>>{
//        return lastUpdateDao.searchByDept(name)
//    }


}