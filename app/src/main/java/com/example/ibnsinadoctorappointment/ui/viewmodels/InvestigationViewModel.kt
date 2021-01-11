package com.example.ibnsinadoctorappointment.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ibnsinadoctorappointment.data.database.MyDatabase
import com.example.ibnsinadoctorappointment.data.models.Investigation
import com.example.ibnsinadoctorappointment.logic.repository.InvestigationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InvestigationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InvestigationRepository
    val getAllInvestigations: LiveData<List<Investigation>>


    init {
        val InvestigationDao= MyDatabase.getDatabase(application).investigationDao()
        repository = InvestigationRepository(InvestigationDao)

        getAllInvestigations = repository.getAllInvestigations
    }

    fun addInvestigation(Investigation: Investigation) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addInvestigation(Investigation)
        }
    }

    fun updateInvestigation(Investigation: Investigation) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateInvestigation(Investigation)
        }
    }

    fun deleteInvestigation(Investigation: Investigation) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteInvestigation(Investigation)
        }
    }

    fun deleteAllInvestigations() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllInvestigations()
        }
    }


}