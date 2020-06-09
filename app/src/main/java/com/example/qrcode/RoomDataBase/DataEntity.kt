package com.example.qrcode.RoomDataBase

import androidx.room.*
import androidx.room.ForeignKey.CASCADE


@Entity(tableName = "data")
data class DataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "data") val data: String?,
    @ColumnInfo(name = "time") val time: String?,
    @ColumnInfo(name = "typeData")val typeData: String?
){

}



