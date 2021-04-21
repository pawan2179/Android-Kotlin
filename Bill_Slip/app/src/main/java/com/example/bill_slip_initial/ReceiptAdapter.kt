package com.example.bill_slip_initial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bill_slip_initial.EntityClass.ItemModelEntity

class ReceiptAdapter (val context : Context) : RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {

    val allItems = ArrayList<ItemModelEntity>()

    inner class ReceiptViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_view_item)
        val txtViewID: TextView = itemView.findViewById(R.id.txt_view_item_no)
        val txtViewCost: TextView = itemView.findViewById(R.id.txt_view_cost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val viewHolder = ReceiptViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val currentItem = allItems[position]
        holder.imageView.setImageBitmap(currentItem.thumbnail)
        holder.txtViewID.text = (currentItem.id.toString())
        holder.txtViewCost.text = ("\u20B9" + currentItem.cost)
    }

    override fun getItemCount() = allItems.size

    fun updateList(newList : List<ItemModelEntity>) {
        allItems.clear()
        allItems.addAll(newList)
        notifyDataSetChanged()
    }
}