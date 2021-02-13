package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "branch_table")
data class Branch (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "column_id")
    var id : Int,

    @ColumnInfo(name = "column_branchName")
    var branchName : String,

//    @ColumnInfo(name = "column_address1")
//    var address1 : String,
//
//    @ColumnInfo(name = "column_address2")
//    var address2 : String,

    @ColumnInfo(name = "column_contact1")
    var contact1 : String,

    @ColumnInfo(name = "column_contact2")
    var contact2 : String,

    @ColumnInfo(name = "column_contactPerson")
    var contactPerson : String

)