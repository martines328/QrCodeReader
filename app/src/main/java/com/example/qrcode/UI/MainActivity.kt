package com.example.qrcode.UI

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qrcode.DI.mainActivityDI
import com.example.qrcode.DI.myModules
import com.example.qrcode.QRProvider.ScanCode
import com.example.qrcode.R
import com.example.qrcode.RoomDataBase.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {


    private var db: AppDataBase? = null
    private var dataDao: DataDAO? = null

    val dataViewModel: DataViewModel by viewModel()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val scanfab: View = findViewById(R.id.scan_fab)
        scanfab.setOnClickListener(){ view -> scanFabCLickAction() }

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        db = AppDataBase.getAppDataBase(context = this)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)

            modules( listOf( mainActivityDI, myModules))

        }

    }


     fun provideDB(): AppDataBase? {
        Log.d("mylog", db.toString())
         return db


    }


    fun provideContext() : Context{
        return this
    }


    private fun scanFabCLickAction(){
        val intent = Intent(this, ScanCode::class.java)
        startActivity(intent)
    }









}