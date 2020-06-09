package com.example.qrcode.UI

import androidx.lifecycle.ViewModel
import com.example.qrcode.RoomDataBase.AppDataBase
import com.example.qrcode.RoomDataBase.DataDAO
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


class DataViewModel : ViewModel() {

    var dao: DataDAO? = null
    val mainAct: MainActivity by inject()






    init {
        dao = AppDataBase?.getAppDataBase(mainAct.provideContext())?.dataDao()
    }


}