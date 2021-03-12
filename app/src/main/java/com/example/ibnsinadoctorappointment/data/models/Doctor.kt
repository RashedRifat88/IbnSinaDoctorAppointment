package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_table")
data class Doctor(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "column_id")
    var id: Int,

    @ColumnInfo(name = "column_nickName")
    var nickName: String,


    @ColumnInfo(name = "column_avatarPath")
    var avatarPath: String,


    @ColumnInfo(name = "column_doctorDept")
    var doctorDept: String,

    @ColumnInfo(name = "column_branchNo")
    var branchNo: Int,

    @ColumnInfo(name = "column_qualification")
    var qualification: String,

    @ColumnInfo(name = "column_designation")
    var designation: String,


    @ColumnInfo(name = "column_doctorBranch")
    var doctorBranch: String


)