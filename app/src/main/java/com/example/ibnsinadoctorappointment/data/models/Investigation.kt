package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "investigation_table")
data class Investigation (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "column_id")
    var id : Int,

    @ColumnInfo(name = "column_name")
    var name : String,

    @ColumnInfo(name = "column_price")
    var price : String,

    @ColumnInfo(name = "column_discount_percentage")
    var discount_percentage : String


)