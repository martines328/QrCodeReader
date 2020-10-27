package com.example.qrcode.DI

import androidx.lifecycle.ViewModel
import com.example.qrcode.QRProvider.QrCodeGenerator
import com.example.qrcode.RoomDataBase.AppDataBase
import com.example.qrcode.RoomDataBase.DataRepository
import com.example.qrcode.RoomDataBase.DataViewModel
import com.example.qrcode.UI.MainActivity
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainActivityDI = module {
    single { MainActivity() }

}

val myModules: Module = module {

    viewModel { DataViewModel(androidContext()) }

    single{ DataRepository(get())}
    single { QrCodeGenerator() }


}