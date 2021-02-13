package com.example.ibnsinadoctorappointment.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ibnsinadoctorappointment.data.models.LastUpdate

@Dao
interface LastUpdateDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLastUpdate(lastUpdate: LastUpdate)

    @Update
    suspend fun updateLastUpdate(lastUpdate: LastUpdate)

    @Delete
    suspend fun deleteLastUpdate(lastUpdate: LastUpdate)

    //call it in recyclerview
    @Query("SELECT * FROM last_update_table ORDER BY column_id ASC")
    fun getAllLastUpdates(): LiveData<List<LastUpdate>>

    @Query("DELETE FROM last_update_table")
    suspend fun deleteAll()


//    @Query("SELECT * FROM last_update_table WHERE column_nickName LIKE :name")
//    fun searchByName (name : String) :LiveData<List<LastUpdate>>
//
//    @Query("SELECT * FROM last_update_table WHERE column_qualification LIKE :name")
//    fun searchByBranch (name : String) :LiveData<List<LastUpdate>>
//
//    @Query("SELECT * FROM last_update_table WHERE column_designation LIKE :name")
//    fun searchByDept (name : String) :LiveData<List<LastUpdate>>

}