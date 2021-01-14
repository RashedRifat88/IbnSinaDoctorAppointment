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


    fun searchByName(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByName(name)
    }


    fun searchByBranch(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByBranch(name)
    }


    fun searchByDept(name : String) : LiveData<List<DoctorChamberBook>> {
        return repository.searchByDept(name)
    }


}