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


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_doctorId = :name")
    fun getBranchByDoctorId(name: Int): DoctorChamberBook


//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_nickName LIKE :name")
//    fun searchByName (name : String) :LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE :date NOT LIKE '00:00:00' AND column_branchName LIKE :branch_name AND column_deptName LIKE :dept_name AND column_nickName LIKE :name")
    fun searchByNameAndDeptAndBranchAndDate(
        name: String,
        date: String,
        branch_name: String,
        dept_name: String
    ): LiveData<List<DoctorChamberBook>>




    @Query("SELECT * FROM doctor_chamber_book_table WHERE :date NOT LIKE '00:00:00' AND column_branchName LIKE :branch_name AND column_nickName LIKE :name")
    fun searchDoctorByNameAndBranchAndDate(
        name: String,
        date: String,
        branch_name: String
    ): LiveData<List<DoctorChamberBook>>



    @Query("SELECT * FROM doctor_chamber_book_table WHERE :date NOT LIKE '00:00:00' AND column_deptName LIKE :dept_name AND column_nickName LIKE :name")
    fun searchByNameAndDeptAndDate(
        name: String,
        date: String,
        dept_name: String
    ): LiveData<List<DoctorChamberBook>>





    @Query("SELECT * FROM doctor_chamber_book_table WHERE :date NOT LIKE '00:00:00' AND column_branchName LIKE :branch_name AND column_deptName LIKE :dept_name")
    fun searchByName1(
        date: String,
        branch_name: String,
        dept_name: String
    ): LiveData<List<DoctorChamberBook>>

    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :branch_name AND column_deptName LIKE :dept_name")
    fun searchByName2(branch_name: String, dept_name: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_nickName LIKE :name  AND :date NOT LIKE '00:00:00'")
    fun searchDoctorByNameAndDate(name: String, date: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE :date NOT LIKE '00:00:00' AND column_deptName LIKE :dept_name")
    fun searchByName3(date: String, dept_name: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :branch_name AND :date NOT LIKE '00:00:00'")
    fun searchByName4(branch_name: String, date: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_nickName LIKE :name")
    fun searchByName(name: String): LiveData<List<DoctorChamberBook>>

    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_branchName LIKE :name")
    fun searchByBranch(name: String): LiveData<List<DoctorChamberBook>>

    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_deptName LIKE :name")
    fun searchByDept(name: String): LiveData<List<DoctorChamberBook>>

    @Query("SELECT * FROM doctor_chamber_book_table WHERE :date <> '00:00:00' ")
    fun searchByDate(date: String): LiveData<List<DoctorChamberBook>>


//    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_friStart != '00:00:00' ")
//    fun searchByDate(date: String): LiveData<List<DoctorChamberBook>>

//    @Query("SELECT * FROM doctor_chamber_book_table WHERE  column_deptName NOT LIKE :date")
//    fun searchByDate(date: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_satStart NOT LIKE :name")
    fun searchBySatDay(name: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_sunStart NOT LIKE :name")
    fun searchBySunDay(name: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_monStart NOT LIKE :name")
    fun searchByMonDay(name: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_tueStart NOT LIKE :name")
    fun searchByTueDay(name: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_wedStart NOT LIKE :name")
    fun searchByWebDay(name: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_thuStart NOT LIKE :name")
    fun searchByThuDay(name: String): LiveData<List<DoctorChamberBook>>


    @Query("SELECT * FROM doctor_chamber_book_table WHERE column_friStart NOT LIKE :name")
    fun searchByFriDay(name: String): LiveData<List<DoctorChamberBook>>


    @Query("DELETE FROM doctor_table")
    suspend fun deleteAll()

}