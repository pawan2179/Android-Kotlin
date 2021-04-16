package com.example.bill_slip_initial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReceiptAdapter (private val orderItem : List<ShopList>) : RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {
    private var index : Int = 1
    class ReceiptViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_view_item)
        val txtViewID: TextView = itemView.findViewById(R.id.txt_view_item_no)
        val txtViewCost: TextView = itemView.findViewById(R.id.txt_view_cost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ReceiptViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val currentItem = orderItem[position]
        holder.imageView.setImageBitmap(currentItem.getImage())
        holder.txtViewID.text = (currentItem.getID().toString())
        holder.txtViewCost.text = ("\u20B9" + currentItem.getCost().toString())
    }

    override fun getItemCount() = orderItem.size
}