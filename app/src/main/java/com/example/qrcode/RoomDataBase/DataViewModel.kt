package com.example.qrcode.RoomDataBase

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class DataViewModel(val context: Context) : ViewModel() {


    private val repository: DataRepository
    val getAllData: LiveData<List<DataEntity>>

    init {

        val dataDao = AppDataBase.getAppDataBase(context)!!.dataDao()
        repository = DataRepository(dataDao)
        getAllData = repository.getAllData()!!

    }



    fun insert(data: String, time:String, type: String) = viewModelScope.launch(Dispatchers.IO){
        repository.insertData(data,time,type)
    }

    fun insert(dataEntity: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertData(dataEntity)
    }







}