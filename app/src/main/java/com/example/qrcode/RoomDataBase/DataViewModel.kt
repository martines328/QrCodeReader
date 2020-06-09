package com.example.qrcode.RoomDataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject


class DataViewModel : ViewModel() {


    val repository: DataRepository by inject<DataRepository>()





    init {

    }


}