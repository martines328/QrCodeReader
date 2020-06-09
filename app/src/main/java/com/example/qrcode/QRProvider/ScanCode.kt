package com.example.qrcode.QRProvider

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.qrcode.R
import com.example.qrcode.RoomDataBase.AppDataBase
import com.example.qrcode.RoomDataBase.DataEntity
import com.example.qrcode.UI.MainActivity
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import io.reactivex.rxjava3.schedulers.Schedulers.newThread
import kotlinx.android.synthetic.main.activity_scan_code.*
import org.jetbrains.anko.doAsync
import org.koin.android.ext.android.inject
import java.time.LocalDateTime


class ScanCode : AppCompatActivity() {

    var REQUEST_IMAGE_CAPTURE = 1

    val context = ScanCode@ this


    val mainActivity: MainActivity by inject()


    init {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_code)
        scancode()

        Log.d("mylog", mainActivity.toString())
        Log.d("mylog", mainActivity.provideDB().toString())

    }


    fun scancode() {


        checkPermission()
    }


    fun checkPermission() {

        val cameraPermission =
            ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
        val externalStoragePermission = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (cameraPermission == PackageManager.PERMISSION_DENIED ||
                    externalStoragePermission == PackageManager.PERMISSION_DENIED)
        ) {
            dispatchTakePictureIntent()
        }

    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(context.packageManager)?.also {
                ActivityCompat.startActivityForResult(
                    this,
                    takePictureIntent,
                    REQUEST_IMAGE_CAPTURE,
                    null
                )

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Log.d("mylog", data.toString())
            val imageBitmap = data?.extras?.get("data") as Bitmap
            Log.d("mylog", imageBitmap.toString())
            imgview.setImageBitmap(scaleDown(imageBitmap, 3000F, false))
            decodeBitmapQrcode(imageBitmap)

        }
    }

    fun decodeBitmapQrcode(bitmap: Bitmap) {
        // val mybitmap = BitmapFactory.decodeResource(this.resources, com.example.qrcode.R.drawable.qrcode)
        // imgview.setImageBitmap(mybitmap)

        val detector: BarcodeDetector = BarcodeDetector.Builder(applicationContext)
            .setBarcodeFormats(Barcode.DATA_MATRIX or Barcode.QR_CODE).build()

        if (!detector.isOperational) {
            txtContent.text = "Could not set up the detector!"

        }

        val frame = Frame.Builder().setBitmap(bitmap).build()
        val barcodes: SparseArray<Barcode>? = detector.detect(frame)

        val codeResult = barcodes?.valueAt(0)

        txtContent.text = codeResult?.rawValue
        Log.d("mylog", codeResult?.rawValue)
        Log.d("mylog", codeResult.toString())

        if (codeResult != null) {
           // provideDecodeDataToDB(codeResult?.rawValue, LocalDateTime.now().toString(), "texttype")
        }


    }


    fun scaleDown(realImage: Bitmap, maxImageSize: Float, filter: Boolean): Bitmap? {
        val ratio = Math.min(
            maxImageSize / realImage.width
            , maxImageSize / realImage.height
        )

        val width = Math.round(ratio * realImage.width)
        val height = Math.round(ratio * realImage.height)

        return Bitmap.createScaledBitmap(realImage, width, height, filter)
    }


    /* fun provideDecodeDataToDB(data: String, time: String, typeData: String){

        doAsync {
            val db: AppDataBase? = AppDataBase.getAppDataBase(this@ScanCode)
            val dataEntity = DataEntity(null,data,time,typeData)
            db?.dataDao()?.insertData(dataEntity)


        }


    }*/



    /*  Observable.fromCallable({

            dataDao = db?.dataDao()

            val currentTime = LocalDateTime.now()
            var data1 = DataEntity( "test data ", currentTime.toString(), type)
            var data1 = DataEntity( null,"test data ", currentTime.toString())
            with(dataDao) {
                this?.insertData(data1)
            }


            db?.dataDao()?.getAllData()
        }).doOnNext({ list ->

           // Log.d("mylog", list.toString())
            var finalString = ""
            list?.map { finalString += it.data+ " - "+ it.id + " - "+  " - "+  "\n" }
            Log.d("mylog", finalString)
        }).subscribeOn(io()).observeOn(newThread()).subscribe()*/




}