package com.example.qrcode.RoomDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DataDAO {

    @Insert
    suspend fun insertData(data: DataEntity)

    @Query("select * from data")
     fun getAllData(): LiveData<List<DataEntity>>

}