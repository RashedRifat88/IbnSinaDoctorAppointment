package com.example.ibnsinadoctorappointment.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ibnsinadoctorappointment.data.database.MyDatabase
import com.example.ibnsinadoctorappointment.data.models.HealthPackage
import com.example.ibnsinadoctorappointment.logic.repository.HealthPackageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HealthPackageViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HealthPackageRepository
    val getAllHealthPackages: LiveData<List<HealthPackage>>


    init {
        val HealthPackageDao = MyDatabase.getDatabase(application).healthPackageDao()
        repository = HealthPackageRepository(HealthPackageDao)

        getAllHealthPackages = repository.getAllHealthPackages
    }

    fun addHealthPackage(HealthPackage: HealthPackage) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHealthPackage(HealthPackage)
        }
    }

    fun updateHealthPackage(HealthPackage: HealthPackage) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHealthPackage(HealthPackage)
        }
    }

    fun deleteHealthPackage(HealthPackage: HealthPackage) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHealthPackage(HealthPackage)
        }
    }

    fun deleteAllHealthPackages() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllHealthPackages()
        }
    }

//    fun searchByName(name: String): LiveData<List<HealthPackage>> {
//        return repository.searchByName(name)
//    }
//
//
//    fun searchByBranch(name: String): LiveData<List<HealthPackage>> {
//        return repository.searchByBranch(name)
//    }
//
//
//    fun searchByDept(name: String): LiveData<List<HealthPackage>> {
//        return repository.searchByDept(name)
//    }


}