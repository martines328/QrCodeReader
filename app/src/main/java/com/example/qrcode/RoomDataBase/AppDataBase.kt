package com.example.qrcode.RoomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = arrayOf(DataEntity::class), version = 1)
@TypeConverters(TimeTypeConverter::class)
abstract class AppDataBase(): RoomDatabase() {

    abstract fun dataDao(): DataDAO


    companion object{

        var INSTANCE: AppDataBase? = null

        fun getAppDataBase(context: Context): AppDataBase?{

            if (INSTANCE==null){
                synchronized(AppDataBase::class){
                    INSTANCE = Room.databaseBuilder(context,AppDataBase::class.java,"dbScan").build()
                }
            }
            return INSTANCE
        }

        fun destroyAppDataBase(){
            INSTANCE= null
        }

    }


}