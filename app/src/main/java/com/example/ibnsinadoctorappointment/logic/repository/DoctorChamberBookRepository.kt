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




    fun searchByNameAndDeptAndBranchAndDate(name : String, date : String, branch_name : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByNameAndDeptAndBranchAndDate(name, date, branch_name, dept_name)
    }




    fun searchDoctorByNameAndBranchAndDate(name : String, date : String, branch_name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchDoctorByNameAndBranchAndDate(name, date, branch_name)
    }


    fun searchByNameAndDeptAndDate(name : String, date : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByNameAndDeptAndDate(name, date, dept_name)
    }




    fun searchByName1(date : String, branch_name : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByName1(date, branch_name, dept_name)
    }



    fun searchByName2(branch_name : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByName2(branch_name, dept_name)
    }


    fun searchDoctorByNameAndDate(name : String, date : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchDoctorByNameAndDate(name, date)
    }



    fun searchByName3(date : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByName3(date, dept_name)
    }



    fun searchByName4(branch_name : String, date : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByName4(branch_name, date)
    }


    fun getBranchByDoctorId(name : Int): String{
        return doctorChamberBookDao.getBranchByDoctorId(name)
    }




    fun searchByBranch(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByBranch(name)
    }



    fun searchByDept(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByDept(name)
    }



    fun searchByDate(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByDate(name)
    }



    fun searchBySatDay(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchBySatDay(name)
    }



    fun searchBySunDay(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchBySunDay(name)
    }



    fun searchByMonDay(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByMonDay(name)
    }



    fun searchByTueDay(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByTueDay(name)
    }



    fun searchByWebDay(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByWebDay(name)
    }



    fun searchByThuDay(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByThuDay(name)
    }



    fun searchByFriDay(name : String): LiveData<List<DoctorChamberBook>>{
        return doctorChamberBookDao.searchByFriDay(name)
    }



}