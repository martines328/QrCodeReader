package com.example.qrcode.DI

import com.example.qrcode.RoomDataBase.AppDataBase
import com.example.qrcode.UI.MainActivity
import org.koin.dsl.module

val mainActivityDI = module {
    single { MainActivity() }
}

