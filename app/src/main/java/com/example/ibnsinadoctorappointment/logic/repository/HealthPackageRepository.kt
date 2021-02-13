package com.example.ibnsinadoctorappointment.logic.repository

import androidx.lifecycle.LiveData
import com.example.ibnsinadoctorappointment.data.models.HealthPackage
import com.example.ibnsinadoctorappointment.logic.dao.HealthPackageDao

class HealthPackageRepository (private val healthPackageDao: HealthPackageDao) {
    val getAllHealthPackages: LiveData<List<HealthPackage>> = healthPackageDao.getAllHealthPackages()

    suspend fun addHealthPackage(healthPackage: HealthPackage) {
        healthPackageDao.addHealthPackage(healthPackage)
    }

    suspend fun updateHealthPackage(healthPackage: HealthPackage) {
        healthPackageDao.updateHealthPackage(healthPackage)
    }

    suspend fun deleteHealthPackage(healthPackage: HealthPackage) {
        healthPackageDao.deleteHealthPackage(healthPackage)
    }

    suspend fun deleteAllHealthPackages() {
        healthPackageDao.deleteAll()
    }




//    fun searchByName(name : String): LiveData<List<HealthPackage>>{
//        return healthPackageDao.searchByName(name)
//    }
//
//
//
//    fun searchByBranch(name : String): LiveData<List<HealthPackage>>{
//        return healthPackageDao.searchByBranch(name)
//    }
//
//
//
//    fun searchByDept(name : String): LiveData<List<HealthPackage>>{
//        return healthPackageDao.searchByDept(name)
//    }


}