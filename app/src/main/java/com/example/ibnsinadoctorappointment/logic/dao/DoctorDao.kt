package com.example.ibnsinadoctorappointment.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibnsinadoctorappointment.data.models.Doctor

@Dao
interface DoctorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDoctor(doctor: Doctor)

    @Update
    suspend fun updateDoctor(doctor: Doctor)

    @Delete
    suspend fun deleteDoctor(doctor: Doctor)

    //call it in recyclerview
    @Query("SELECT * FROM doctor_table ORDER BY column_id ASC")
    fun getAllDoctors(): LiveData<List<Doctor>>

    @Query("DELETE FROM doctor_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM doctor_table WHERE column_nickName LIKE :name")
    fun searchByName (name : String) :LiveData<List<Doctor>>

    @Query("SELECT * FROM doctor_table WHERE column_qualification LIKE :name")
    fun searchByBranch (name : String) :LiveData<List<Doctor>>

    @Query("SELECT * FROM doctor_table WHERE column_designation LIKE :name")
    fun searchByDept (name : String) :LiveData<List<Doctor>>

}