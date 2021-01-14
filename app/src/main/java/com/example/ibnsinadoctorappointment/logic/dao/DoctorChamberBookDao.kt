package com.example.ibnsinadoctorappointment.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook

@Dao
interface DoctorChamberBookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDoctorChamberBook(doctor: DoctorChamberBook)

    @Update
    suspend fun updateDoctorChamberBook(doctor: DoctorChamberBook)

    @Delete
    suspend fun deleteDoctorChamberBook(doctor: DoctorChamberBook)

    //call it in recyclerview
    @Query("SELECT * FROM doctor_chamber_book_table ORDER BY column_dbNo ASC")
    fun getAllDoctorChamberBooks(): LiveData<List<DoctorChamberBook>>

    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_nickName LIKE :name")
    fun searchByName (name : String) :LiveData<List<DoctorChamberBook>>

    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :name")
    fun searchByBranch (name : String) :LiveData<List<DoctorChamberBook>>

    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_qualification LIKE :name")
    fun searchByDept (name : String) :LiveData<List<DoctorChamberBook>>

    @Query("DELETE FROM doctor_table")
    suspend fun deleteAll()

}