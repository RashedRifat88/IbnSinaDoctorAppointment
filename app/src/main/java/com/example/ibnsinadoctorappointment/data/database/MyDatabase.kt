package com.example.ibnsinadoctorappointment.data.database

import com.example.ibnsinadoctorappointment.data.models.Investigation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.logic.dao.DoctorChamberBookDao
import com.example.ibnsinadoctorappointment.logic.dao.DoctorDao
import com.example.ibnsinadoctorappointment.logic.dao.InvestigationDao

@Database(entities = [Investigation::class, Doctor::class, DoctorChamberBook::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun investigationDao(): InvestigationDao
    abstract fun doctorDao(): DoctorDao
    abstract fun doctorChamberBookDao(): DoctorChamberBookDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}