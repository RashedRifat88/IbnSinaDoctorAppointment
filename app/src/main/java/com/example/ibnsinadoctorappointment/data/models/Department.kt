package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "department_table")
data class Department (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "column_id")
    var id : Int,

    @ColumnInfo(name = "column_name")
    var name : String,

    @ColumnInfo(name = "column_enabled")
    var enabled : Boolean,


)