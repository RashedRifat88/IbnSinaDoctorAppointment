package com.example.ibnsinadoctorappointment.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibnsinadoctorappointment.data.models.Investigation

@Dao
interface InvestigationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInvestigation(investigation: Investigation)

    @Update
    suspend fun updateInvestigation(investigation: Investigation)

    @Delete
    suspend fun deleteInvestigation(investigation: Investigation)

    //call it in recyclerview
    @Query("SELECT * FROM investigation_table ORDER BY column_id ASC")
    fun getAllInvestigations(): LiveData<List<Investigation>>

    @Query("DELETE FROM investigation_table")
    suspend fun deleteAll()

}