package com.example.qrcode.RoomDataBase

import android.content.Context
import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class DataRepository(val context: Context) {


    var dao: DataDAO? = null
    var getAllData: LiveData<List<DataEntity>>?

    init {
        dao = AppDataBase?.getAppDataBase(context)?.dataDao()
        getAllData = dao?.getAllData()

    }


    fun insertData(data: String, time: String, typeData: String) {

        val dataEntity = DataEntity(null, data, time, typeData)
        dao?.insertData(dataEntity)

    }

    fun insertData(dataEntity: DataEntity) {
        dao?.insertData(dataEntity)
    }


    fun getAllData(): LiveData<List<DataEntity>>? {
        return getAllData
    }






}