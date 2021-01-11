package com.example.ibnsinadoctorappointment.logic.repository

import androidx.lifecycle.LiveData
import com.example.ibnsinadoctorappointment.data.models.Investigation
import com.example.ibnsinadoctorappointment.logic.dao.InvestigationDao

class InvestigationRepository (private val investigationDao: InvestigationDao) {
    val getAllInvestigations: LiveData<List<Investigation>> = investigationDao.getAllInvestigations()

    suspend fun addInvestigation(investigation: Investigation) {
        investigationDao.addInvestigation(investigation)
    }

    suspend fun updateInvestigation(investigation: Investigation) {
        investigationDao.updateInvestigation(investigation)
    }

    suspend fun deleteInvestigation(investigation: Investigation) {
        investigationDao.deleteInvestigation(investigation)
    }

    suspend fun deleteAllInvestigations() {
        investigationDao.deleteAll()
    }


}