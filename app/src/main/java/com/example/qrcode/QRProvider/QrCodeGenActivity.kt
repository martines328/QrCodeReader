package com.example.qrcode.QRProvider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.UiThread
import com.example.qrcode.R
import kotlinx.android.synthetic.main.activity_qr_code_gen.*
import kotlinx.coroutines.*
import org.jetbrains.anko.custom.async
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext


class QrCodeGenActivity : AppCompatActivity() {

    private val  QrCodeGenerator by inject<QrCodeGenerator>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_gen)


        val data = intent.getStringExtra("dataExtra")
        Log.d("mylog", "intent data ${data}")
        dataTV.text = data


        val gener = CoroutineScope(Dispatchers.Main).launch {
            qrcodeIV.setImageBitmap(QrCodeGenerator.generateQrCode(data))
            Log.d("mytag", "gener qrcode in coroutines")
        }



    }



}