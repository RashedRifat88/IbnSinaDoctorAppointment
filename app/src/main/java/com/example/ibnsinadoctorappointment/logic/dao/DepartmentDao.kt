package com.example.ibnsinadoctorappointment.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibnsinadoctorappointment.data.models.Department

@Dao
interface DepartmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDepartment(department: Department)

    @Update
    suspend fun updateDepartment(department: Department)

    @Delete
    suspend fun deleteDepartment(department: Department)

    //call it in recyclerview
    @Query("SELECT * FROM department_table ORDER BY column_id ASC")
    fun getAllDepartments(): LiveData<List<Department>>

    @Query("DELETE FROM department_table")
    suspend fun deleteAll()


//    @Query("SELECT * FROM department_table WHERE column_nickName LIKE :name")
//    fun searchByName (name : String) :LiveData<List<Department>>
//
//    @Query("SELECT * FROM department_table WHERE column_qualification LIKE :name")
//    fun searchByBranch (name : String) :LiveData<List<Department>>
//
//    @Query("SELECT * FROM department_table WHERE column_designation LIKE :name")
//    fun searchByDept (name : String) :LiveData<List<Department>>

}