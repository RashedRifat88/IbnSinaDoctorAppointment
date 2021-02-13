package com.example.ibnsinadoctorappointment.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ibnsinadoctorappointment.data.database.MyDatabase
import com.example.ibnsinadoctorappointment.data.models.Department
import com.example.ibnsinadoctorappointment.logic.repository.DepartmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DepartmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DepartmentRepository
    val getAllDepartments: LiveData<List<Department>>


    init {
        val DepartmentDao = MyDatabase.getDatabase(application).departmentDao()
        repository = DepartmentRepository(DepartmentDao)

        getAllDepartments = repository.getAllDepartments
    }

    fun addDepartment(Department: Department) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDepartment(Department)
        }
    }

    fun updateDepartment(Department: Department) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDepartment(Department)
        }
    }

    fun deleteDepartment(Department: Department) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDepartment(Department)
        }
    }

    fun deleteAllDepartments() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDepartments()
        }
    }

//    fun searchByName(name: String): LiveData<List<Department>> {
//        return repository.searchByName(name)
//    }
//
//
//    fun searchByBranch(name: String): LiveData<List<Department>> {
//        return repository.searchByBranch(name)
//    }
//
//
//    fun searchByDept(name: String): LiveData<List<Department>> {
//        return repository.searchByDept(name)
//    }


}