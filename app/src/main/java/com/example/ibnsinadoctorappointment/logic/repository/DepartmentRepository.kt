package com.example.ibnsinadoctorappointment.logic.repository

import androidx.lifecycle.LiveData
import com.example.ibnsinadoctorappointment.data.models.Department
import com.example.ibnsinadoctorappointment.logic.dao.DepartmentDao

class DepartmentRepository (private val departmentDao: DepartmentDao) {
    val getAllDepartments: LiveData<List<Department>> = departmentDao.getAllDepartments()

    suspend fun addDepartment(department: Department) {
        departmentDao.addDepartment(department)
    }

    suspend fun updateDepartment(department: Department) {
        departmentDao.updateDepartment(department)
    }

    suspend fun deleteDepartment(department: Department) {
        departmentDao.deleteDepartment(department)
    }

    suspend fun deleteAllDepartments() {
        departmentDao.deleteAll()
    }




//    fun searchByName(name : String): LiveData<List<Department>>{
//        return departmentDao.searchByName(name)
//    }
//
//
//
//    fun searchByBranch(name : String): LiveData<List<Department>>{
//        return departmentDao.searchByBranch(name)
//    }
//
//
//
//    fun searchByDept(name : String): LiveData<List<Department>>{
//        return departmentDao.searchByDept(name)
//    }


}