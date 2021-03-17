package com.example.ibnsinadoctorappointment.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook

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

    @Query("SELECT * FROM doctor_table WHERE column_id = :id")
    fun getDoctorByDoctorId(id: Int): Doctor


    ///
//
//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :branch_name AND column_deptName LIKE :dept_name AND column_nickName LIKE :name")
//    fun searchByNameAndDeptAndBranch(
//        name: String,
//        branch_name: String,
//        dept_name: String
//    ): LiveData<List<Doctor>>
//
//
//
//
//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :branch_name AND column_nickName LIKE :name")
//    fun searchDoctorByNameAndBranch(
//        name: String,
//        branch_name: String
//    ): LiveData<List<Doctor>>
//
//
//
//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_deptName LIKE :dept_name AND column_nickName LIKE :name")
//    fun searchByNameAndDept(
//        name: String,
//        dept_name: String
//    ): LiveData<List<Doctor>>
//
//
//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :branch_name AND column_deptName LIKE :dept_name")
//    fun searchByName1(
//        branch_name: String,
//        dept_name: String
//    ): LiveData<List<Doctor>>
//
//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :branch_name AND column_deptName LIKE :dept_name")
//    fun searchByName2(branch_name: String, dept_name: String): LiveData<List<Doctor>>
//
//
//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_nickName LIKE :name")
//    fun searchDoctorByName(name: String): LiveData<List<Doctor>>
//
//
//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_deptName LIKE :dept_name")
//    fun searchByName3(dept_name: String): LiveData<List<Doctor>>
//
//
//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :branch_name")
//    fun searchByName4(branch_name: String): LiveData<List<Doctor>>
//
//
//    ///

    ///

}