package com.example.qrcode.UI

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qrcode.QRProvider.QrCodeGenActivity
import com.example.qrcode.R
import com.example.qrcode.RoomDataBase.DataEntity
import org.jetbrains.anko.find

class DataAdapter internal constructor(val context: Context) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var data = emptyList<DataEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_item_scan_result,parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int  = data.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

          val reversed  = data.reversed()
        val currentData = reversed[position]

        holder.dataTV.text = currentData.data
        holder.timeTv.text = currentData.time


        val currentdata: String = currentData.data.toString()
        holder.view.setOnClickListener {

            val intent = Intent(context, QrCodeGenActivity::class.java)
            intent.putExtra("dataExtra", currentData.data.toString())
            context.startActivity(intent)
        }
    }


    internal fun setData(data : List<DataEntity>){
        this.data = data
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view : View = itemView
        val dataTV = itemView.findViewById(R.id.dataText) as TextView
        val timeTv = itemView.findViewById<TextView>(R.id.timeTV)

    }


}