package com.example.qrcode.RoomDataBase

import android.content.Context
import androidx.lifecycle.LiveData
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync

class DataRepository(private val dataDAO: DataDAO) {



   suspend fun insertData(data: String, time: String, typeData: String) {
          val dataEntity = DataEntity(null, data, time, typeData)
            dataDAO.insertData(dataEntity)
    }


    suspend fun insertData(dataEntity: DataEntity) {
        dataDAO.insertData(dataEntity)
    }


     fun getAllData(): LiveData<List<DataEntity>>? = dataDAO.getAllData()






}