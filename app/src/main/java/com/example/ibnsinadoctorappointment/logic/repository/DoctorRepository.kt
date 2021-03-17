package com.example.ibnsinadoctorappointment.logic.repository

import androidx.lifecycle.LiveData
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.logic.dao.DoctorDao

class DoctorRepository (private val doctorDao: DoctorDao) {
    val getAllDoctors: LiveData<List<Doctor>> = doctorDao.getAllDoctors()

    suspend fun addDoctor(doctor: Doctor) {
        doctorDao.addDoctor(doctor)
    }

    suspend fun updateDoctor(doctor: Doctor) {
        doctorDao.updateDoctor(doctor)
    }

    suspend fun deleteDoctor(doctor: Doctor) {
        doctorDao.deleteDoctor(doctor)
    }

    suspend fun deleteAllDoctors() {
        doctorDao.deleteAll()
    }




    fun searchByName(name : String): LiveData<List<Doctor>>{
        return doctorDao.searchByName(name)
    }



    fun searchByBranch(name : String): LiveData<List<Doctor>>{
        return doctorDao.searchByBranch(name)
    }



    fun searchByDept(name : String): LiveData<List<Doctor>>{
        return doctorDao.searchByDept(name)
    }


    fun getDoctorByDoctorId(id : Int): Doctor{
        return doctorDao.getDoctorByDoctorId(id)
    }


    ///

//
//    fun searchByNameAndDeptAndBranch(name : String, branch_name : String, dept_name : String): LiveData<List<Doctor>>{
//        return doctorDao.searchByNameAndDeptAndBranch(name, branch_name, dept_name)
//    }
//
//
//
//
//    fun searchDoctorByNameAndBranch(name : String, branch_name : String): LiveData<List<Doctor>>{
//        return doctorDao.searchDoctorByNameAndBranch(name, branch_name)
//    }
//
//
//    fun searchByNameAndDept(name : String, dept_name : String): LiveData<List<Doctor>>{
//        return doctorDao.searchByNameAndDept(name, dept_name)
//    }
//
//
//
//
//    fun searchByName1(branch_name : String, dept_name : String): LiveData<List<Doctor>>{
//        return doctorDao.searchByName1(branch_name, dept_name)
//    }
//
//
//
//    fun searchByName2(branch_name : String, dept_name : String): LiveData<List<Doctor>>{
//        return doctorDao.searchByName2(branch_name, dept_name)
//    }
//
//
//    fun searchDoctorByName(name : String, date : String): LiveData<List<Doctor>>{
//        return doctorDao.searchDoctorByName(name)
//    }
//
//
//
//    fun searchByName3(date : String, dept_name : String): LiveData<List<Doctor>>{
//        return doctorDao.searchByName3(dept_name)
//    }
//
//
//
//    fun searchByName4(branch_name : String, date : String): LiveData<List<Doctor>>{
//        return doctorDao.searchByName4(branch_name)
//    }
//
//    ///


}