package com.example.ibnsinadoctorappointment.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibnsinadoctorappointment.data.models.Branch

@Dao
interface BranchDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBranch(branch: Branch)

    @Update
    suspend fun updateBranch(branch: Branch)

    @Delete
    suspend fun deleteBranch(branch: Branch)

    //call it in recyclerview
    @Query("SELECT * FROM branch_table ORDER BY column_id ASC")
    fun getAllBranchs(): LiveData<List<Branch>>

    @Query("DELETE FROM branch_table")
    suspend fun deleteAll()


//    @Query("SELECT column_branchName FROM branch_table WHERE column_id LIKE :name")
//    fun searchById (name : String) :LiveData<List<Branch>>




//    @Query("SELECT * FROM branch_table WHERE column_nickName LIKE :name")
//    fun searchByName (name : String) :LiveData<List<Branch>>
//
//    @Query("SELECT * FROM branch_table WHERE column_qualification LIKE :name")
//    fun searchByBranch (name : String) :LiveData<List<Branch>>
//
//    @Query("SELECT * FROM branch_table WHERE column_designation LIKE :name")
//    fun searchByDept (name : String) :LiveData<List<Branch>>

}