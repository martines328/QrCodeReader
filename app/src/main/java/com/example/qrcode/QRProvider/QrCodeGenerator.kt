package com.example.qrcode.QRProvider

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException

open class QrCodeGenerator {

   suspend fun generateQrCode(data:String): Bitmap?{

        val width = 500
        val height = 500

        val bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()

        try {
            val bitMAtrix = codeWriter.encode(data,BarcodeFormat.QR_CODE,width,height)
            for (x in 0 until width){
                for (y in 0 until height){
                    bitmap.setPixel(x,y, if (bitMAtrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }
        }catch (e:WriterException){
            Log.d("mylog", "generateQrCode ${e.message}")
        }

        return bitmap

    }



}