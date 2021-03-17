package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_chamber_book_table")
data class DoctorChamberBook(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "column_dbNo")
    var dbNo: Int,

    @ColumnInfo(name = "column_doctorId")
    var doctorId: Int,

    @ColumnInfo(name = "column_nickName")
    var nickName: String,

    @ColumnInfo(name = "column_avatarPath")
    var avatarPath: String,

    @ColumnInfo(name = "column_qualification")
    var qualification: String,

    @ColumnInfo(name = "column_designation")
    var designation: String,

    @ColumnInfo(name = "column_branchName")
    var branchName: String,

    @ColumnInfo(name = "column_branchNo")
    var branchNo: String,

    @ColumnInfo(name = "column_deptName")
    var deptName: String,

    @ColumnInfo(name = "column_appointmentLockedDate")
    var appointmentLockedDate: String,


    @ColumnInfo(name = "column_satStart")
    var satStart: String,

    @ColumnInfo(name = "column_satEnd")
    var satEnd: String,

    @ColumnInfo(name = "column_sunStart")
    var sunStart: String,

    @ColumnInfo(name = "column_sunEnd")
    var sunEnd: String,

    @ColumnInfo(name = "column_monStart")
    var monStart: String,

    @ColumnInfo(name = "column_monEnd")
    var monEnd: String,

    @ColumnInfo(name = "column_tueStart")
    var tueStart: String,

    @ColumnInfo(name = "column_tueEnd")
    var tueEnd: String,

    @ColumnInfo(name = "column_wedStart")
    var wedStart: String,

    @ColumnInfo(name = "column_wedEnd")
    var wedEnd: String,

    @ColumnInfo(name = "column_thuStart")
    var thuStart: String,

    @ColumnInfo(name = "column_thuEnd")
    var thuEnd: String,

    @ColumnInfo(name = "column_friStart")
    var friStart: String,

    @ColumnInfo(name = "column_friEnd")
    var friEnd: String,



    @ColumnInfo(name = "column_days2TakeAppoint")
    var days2TakeAppoint: String


)