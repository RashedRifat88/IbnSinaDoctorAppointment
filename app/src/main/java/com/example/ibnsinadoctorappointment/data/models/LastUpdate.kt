package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_update_table")
data class LastUpdate (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "column_id")
    var id : Int,

    @ColumnInfo(name = "column_last_update")
    var last_update : String,


)