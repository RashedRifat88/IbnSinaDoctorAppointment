package com.example.ibnsinadoctorappointment.logic.repository

import androidx.lifecycle.LiveData
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.logic.dao.DoctorChamberBookDao

class DoctorChamberBookRepository (private val doctorChamberBookDao: DoctorChamberBookDao) {
    val getAllDoctorChamberBooks: LiveData<List<DoctorChamberBook>> = doctorChamberBookDao.getAllDoctorChamberBooks()

    suspend fun addDoctorChamberBook(doctorChamberBook: DoctorChamberBook) {
        doctorChamberBookDao.addDoctorChamberBook(doctorChamberBook)
    }

    suspend fun updateDoctorChamberBook(doctorChamberBook: DoctorChamberBook) {
        doctorChamberBookDao.updateDoctorChamberBook(doctorChamberBook)
    }

    suspend fun deleteDoctorChamberBook(doctorChamberBook: DoctorChamberBook) {
        doctorChamberBookDao.deleteDoctorChamberBook(doctorChamberBook)
    }

    suspend fun deleteAllDoctorChamberBooks() {
        doctorChamberBookDao.deleteAll()
    }


    fun searchByName(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByName(name)
    }



    fun searchByBranch(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByBranch(name)
    }



    fun searchByDept(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByDept(name)
    }



}