package com.example.ibnsinadoctorappointment.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ibnsinadoctorappointment.data.database.MyDatabase
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.logic.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DoctorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DoctorRepository
    val getAllDoctors: LiveData<List<Doctor>>


    init {
        val DoctorDao= MyDatabase.getDatabase(application).doctorDao()
        repository = DoctorRepository(DoctorDao)

        getAllDoctors = repository.getAllDoctors
    }

    fun addDoctor(Doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDoctor(Doctor)
        }
    }

    fun updateDoctor(Doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDoctor(Doctor)
        }
    }

    fun deleteDoctor(Doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDoctor(Doctor)
        }
    }

    fun deleteAllDoctors() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDoctors()
        }
    }

    fun searchByName(name : String) : LiveData<List<Doctor>> {
        return repository.searchByName(name)
    }


    fun searchByBranch(name : String) : LiveData<List<Doctor>> {
        return repository.searchByBranch(name)
    }


    fun searchByDept(name : String) : LiveData<List<Doctor>> {
        return repository.searchByDept(name)
    }


}