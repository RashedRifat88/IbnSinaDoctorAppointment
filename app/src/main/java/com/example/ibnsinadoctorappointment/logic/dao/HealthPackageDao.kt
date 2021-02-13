package com.example.ibnsinadoctorappointment.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibnsinadoctorappointment.data.models.HealthPackage

@Dao
interface HealthPackageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHealthPackage(healthPackage: HealthPackage)

    @Update
    suspend fun updateHealthPackage(healthPackage: HealthPackage)

    @Delete
    suspend fun deleteHealthPackage(healthPackage: HealthPackage)

    //call it in recyclerview
    @Query("SELECT * FROM health_package_table ORDER BY column_id ASC")
    fun getAllHealthPackages(): LiveData<List<HealthPackage>>

    @Query("DELETE FROM health_package_table")
    suspend fun deleteAll()


//    @Query("SELECT * FROM health_package_table WHERE column_nickName LIKE :name")
//    fun searchByName (name : String) :LiveData<List<HealthPackage>>
//
//    @Query("SELECT * FROM health_package_table WHERE column_qualification LIKE :name")
//    fun searchByBranch (name : String) :LiveData<List<HealthPackage>>
//
//    @Query("SELECT * FROM health_package_table WHERE column_designation LIKE :name")
//    fun searchByDept (name : String) :LiveData<List<HealthPackage>>

}