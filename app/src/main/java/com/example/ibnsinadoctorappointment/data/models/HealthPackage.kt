package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_package_table")
data class HealthPackage (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "column_id")
    var ID : Int,

//    @ColumnInfo(name = "column_name")
//    var Name : String,


    @ColumnInfo(name = "column_item_package_id")
    var package_id : Int,

    @ColumnInfo(name = "column_item_name")
    var name : String,



)