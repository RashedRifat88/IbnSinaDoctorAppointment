package com.example.ibnsinadoctorappointment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ibnsinadoctorappointment.data.models.*
import com.example.ibnsinadoctorappointment.logic.dao.*

@Database(entities = [Investigation::class, Doctor::class, DoctorChamberBook::class, Branch::class, Department::class, HealthPackage::class, LastUpdate::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun investigationDao(): InvestigationDao
    abstract fun doctorDao(): DoctorDao
    abstract fun doctorChamberBookDao(): DoctorChamberBookDao
    abstract fun branchDao(): BranchDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun healthPackageDao(): HealthPackageDao
    abstract fun lastUpdateDao(): LastUpdateDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

//        MyApp.database =  Room.databaseBuilder(this, AppDatabase::class.java, "MyDatabase").allowMainThreadQueries().build()

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }


//        fun getDatabase(context: Context): MyDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MyDatabase::class.java,
//                    "my_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }



    }
}