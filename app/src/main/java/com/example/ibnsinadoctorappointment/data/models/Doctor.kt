package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_table")
data class Doctor(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "column_id")
    var id: Int,

//    @ColumnInfo(name = "column_username")
//    var username : String,

    @ColumnInfo(name = "column_nickName")
    var nickName: String,

//    @ColumnInfo(name = "column_sex")
//    var sex : String,
//
//    @ColumnInfo(name = "column_avatar")
//    var avatar : String,
//
//    @ColumnInfo(name = "column_email")
//    var email : String,

//    @ColumnInfo(name = "column_phone")
//    var phone : String,

//    @ColumnInfo(name = "column_userType")
//    var userType : String,


    @ColumnInfo(name = "column_avatarPath")
    var avatarPath: String,
//
//    @ColumnInfo(name = "column_note")
//    var note : String,
//
//    @ColumnInfo(name = "column_qualification")
//    var qualification : String,
//
//    @ColumnInfo(name = "column_exChamberNote")
//    var exChamberNote : String,
//
//    @ColumnInfo(name = "column_appActiveFlag")
//    var appActiveFlag : String,
//
//    @ColumnInfo(name = "column_averageTime")
//    var averageTime : String,

    @ColumnInfo(name = "column_doctorDept")
    var doctorDept: String,

    @ColumnInfo(name = "column_branchNo")
    var branchNo: Int,


//    @ColumnInfo(name = "column_doctorSpecializations")
//    var doctorSpecializations : String


    @ColumnInfo(name = "column_qualification")
    var qualification: String,

    @ColumnInfo(name = "column_designation")
    var designation: String


)