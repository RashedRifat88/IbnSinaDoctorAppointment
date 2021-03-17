package com.example.ibnsinadoctorappointment.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ibnsinadoctorappointment.data.database.MyDatabase
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.logic.repository.DoctorChamberBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DoctorChamberBookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DoctorChamberBookRepository
    val getAllDoctorChamberBooks: LiveData<List<DoctorChamberBook>>


    init {
        val doctorChamberBookDao= MyDatabase.getDatabase(application).doctorChamberBookDao()
        repository = DoctorChamberBookRepository(doctorChamberBookDao)

        getAllDoctorChamberBooks = repository.getAllDoctorChamberBooks
    }

    fun addDoctorChamberBook(doctorChamberBook: DoctorChamberBook) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDoctorChamberBook(doctorChamberBook)
        }
    }

    fun updateDoctorChamberBook(doctorChamberBook: DoctorChamberBook) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDoctorChamberBook(doctorChamberBook)
        }
    }

    fun deleteDoctorChamberBook(doctorChamberBook: DoctorChamberBook) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDoctorChamberBook(doctorChamberBook)
        }
    }

    fun deleteAllDoctorChamberBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDoctorChamberBooks()
        }
    }



    fun searchByNameAndDeptAndBranchAndDate(name : String, date : String, branch_name : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return repository.searchByNameAndDeptAndBranchAndDate(name, date, branch_name, dept_name)
    }


    fun searchDoctorByNameAndBranchAndDate(name : String, date : String, branch_name : String): LiveData<List<DoctorChamberBook>>{
        return repository.searchDoctorByNameAndBranchAndDate(name, date, branch_name)
    }


    fun searchByNameAndDeptAndDate(name : String, date : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return repository.searchByNameAndDeptAndDate(name, date, dept_name)
    }


    fun searchByName1(date : String, branch_name : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return repository.searchByName1(date, branch_name, dept_name)
    }



    fun searchByName2(branch_name : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return repository.searchByName2(branch_name, dept_name)
    }


    fun searchDoctorByNameAndDate(name : String, date : String): LiveData<List<DoctorChamberBook>>{
        return repository.searchDoctorByNameAndDate(name, date)
    }



    fun searchByName3(date : String, dept_name : String): LiveData<List<DoctorChamberBook>>{
        return repository.searchByName3(date, dept_name)
    }


    fun searchByName4(branch_name : String, date : String): LiveData<List<DoctorChamberBook>>{
        return repository.searchByName4(branch_name, date)
    }



    fun searchByName(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByName(name)
    }


    fun getBranchByDoctorId(id: Int): DoctorChamberBook {
        return repository.getBranchByDoctorId(id)
    }


    fun searchByBranch(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByBranch(name)
    }


    fun searchByDept(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByDept(name)
    }

    fun searchByDate(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByDate(name)
    }


    fun searchBySatDay(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchBySatDay(name)
    }


    fun searchBySunDay(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchBySunDay(name)
    }


    fun searchByMonDay(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByMonDay(name)
    }


    fun searchByTueDay(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByTueDay(name)
    }


    fun searchByWebDay(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByWebDay(name)
    }


    fun searchByThuDay(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByThuDay(name)
    }


    fun searchByFriDay(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByFriDay(name)
    }




}