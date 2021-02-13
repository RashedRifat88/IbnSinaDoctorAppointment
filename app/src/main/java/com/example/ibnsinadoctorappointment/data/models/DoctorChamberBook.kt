package com.example.ibnsinadoctorappointment.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_chamber_book_table")
data class DoctorChamberBook (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "column_dbNo")
    var dbNo : Int,


//    @ColumnInfo(name = "column_comments")
//    var comments : String,
//
//
//    @ColumnInfo(name = "column_doctorNote")
//    var doctorNote : String,
//
//
//    @ColumnInfo(name = "column_dfltFromTime")
//    var dfltFromTime : String,
//
//
//    @ColumnInfo(name = "column_dfltToTime")
//    var dfltToTime : String,
//
//
//    @ColumnInfo(name = "column_docShift")
//    var docShift : String,
//
//
//    @ColumnInfo(name = "column_roomNo")
//    var roomNo : String,
//
//
//    @ColumnInfo(name = "column_contactNo")
//    var contactNo : String,
//
//
//    @ColumnInfo(name = "column_feeNew")
//    var feeNew : String,
//
//
//    @ColumnInfo(name = "column_fee1stFu")
//    var fee1stFu : String,
//
//
//    @ColumnInfo(name = "column_fee2ndFu")
//    var fee2ndFu : String,
//
//
//    @ColumnInfo(name = "column_fee3rdFu")
//    var fee3rdFu : String,
//
//
//    @ColumnInfo(name = "column_fee4thFu")
//    var fee4thFu : String,
//
//
//    @ColumnInfo(name = "column_feeReportChk")
//    var feeReportChk : String,
//
//
//    @ColumnInfo(name = "column_days2TakeAppoint")
//    var days2TakeAppoint : String,
//
//
//    @ColumnInfo(name = "column_maxSlPerDay")
//    var maxSlPerDay : String,
//
//
//    @ColumnInfo(name = "column_activeFlag")
//    var activeFlag : String,
//
//
//    @ColumnInfo(name = "column_appActiveFlag")
//    var appActiveFlag : String,
//
//
//    @ColumnInfo(name = "column_doctor")
//    var doctor : String,
//
//
//    @ColumnInfo(name = "column_branchNo")
//    var branchNo : String,
//
//
//    @ColumnInfo(name = "column_createTime")
//    var createTime : String,
//
//
//    @ColumnInfo(name = "column_satStart")
//    var satStart : String,
//
//
//    @ColumnInfo(name = "column_satEnd")
//    var satEnd : String,
//
//
//    @ColumnInfo(name = "column_sunStart")
//    var sunStart : String,
//
//
//    @ColumnInfo(name = "column_sunEnd")
//    var sunEnd : String,
//
//
//    @ColumnInfo(name = "column_monStart")
//    var monStart : String,
//
//
//    @ColumnInfo(name = "column_monEnd")
//    var monEnd : String,
//
//
//    @ColumnInfo(name = "column_tueStart")
//    var tueStart : String,
//
//
//    @ColumnInfo(name = "column_tueEnd")
//    var tueEnd : String,
//
//
//    @ColumnInfo(name = "column_wedStart")
//    var wedStart : String,
//
//
//    @ColumnInfo(name = "column_wedEnd")
//    var wedEnd : String,
//
//
//    @ColumnInfo(name = "column_thuStart")
//    var thuStart : String,
//
//
//    @ColumnInfo(name = "column_thuEnd")
//    var thuEnd : String,
//
//
//    @ColumnInfo(name = "column_friStart")
//    var friStart : String,
//
//
//    @ColumnInfo(name = "column_friEnd")
//    var friEnd : String,
//
//
//    @ColumnInfo(name = "column_caption")
//    var caption : String,
//
//
//    @ColumnInfo(name = "column_appSl")
//    var appSl : String,
//

//
//
//    @ColumnInfo(name = "column_phone")
//    var phone : String,
//
//
    @ColumnInfo(name = "column_nickName")
    var nickName : String,


    @ColumnInfo(name = "column_avatarPath")
    var avatarPath : String,

//
//
    @ColumnInfo(name = "column_qualification")
    var qualification : String,
//
//
//    @ColumnInfo(name = "column_designation")
//    var designation : String,
//
//
    @ColumnInfo(name = "column_branchName")
    var branchName : String
//
//
//    @ColumnInfo(name = "column_deptId")
//    var deptId : String,
//
//
//    @ColumnInfo(name = "column_deptName")
//    var deptName : String,


)