package com.example.ibnsinadoctorappointment.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ibnsinadoctorappointment.data.database.MyDatabase
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.logic.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DoctorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DoctorRepository
    val getAllDoctors: LiveData<List<Doctor>>


    init {
        val DoctorDao = MyDatabase.getDatabase(application).doctorDao()
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



    fun searchByName(name: String): LiveData<List<Doctor>> {
        return repository.searchByName(name)
    }


    fun searchByBranch(name: String): LiveData<List<Doctor>> {
        return repository.searchByBranch(name)
    }


    fun searchByDept(name: String): LiveData<List<Doctor>> {
        return repository.searchByDept(name)
    }


    fun getDoctorByDoctorId(id: Int): Doctor {
        return repository.getDoctorByDoctorId(id)
    }



//    ///
//    fun searchByNameAndDeptAndBranch(name : String, branch_name : String, dept_name : String): LiveData<List<Doctor>>{
//        return repository.searchByNameAndDeptAndBranch(name, branch_name, dept_name)
//    }
//
//
//    fun searchDoctorByNameAndBranch(name : String, branch_name : String): LiveData<List<Doctor>>{
//        return repository.searchDoctorByNameAndBranch(name, branch_name)
//    }
//
//
//    fun searchByNameAndDept(name : String, dept_name : String): LiveData<List<Doctor>>{
//        return repository.searchByNameAndDept(name, dept_name)
//    }
//
//
//    fun searchByName1( branch_name : String, dept_name : String): LiveData<List<Doctor>>{
//        return repository.searchByName1( branch_name, dept_name)
//    }
//
//
//
//    fun searchByName2(branch_name : String, dept_name : String): LiveData<List<Doctor>>{
//        return repository.searchByName2(branch_name, dept_name)
//    }
//    ///


}