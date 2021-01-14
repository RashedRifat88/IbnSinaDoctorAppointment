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


}