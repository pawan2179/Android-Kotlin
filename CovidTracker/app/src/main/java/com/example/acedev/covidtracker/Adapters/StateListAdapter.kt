package com.example.acedev.covidtracker.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.acedev.covidtracker.APIClasses.StateData
import com.example.acedev.covidtracker.APIClasses.StateResponses
import com.example.acedev.covidtracker.R
import com.example.acedev.covidtracker.SpannableDelta

class StateListAdapter(val context : Context) : RecyclerView.Adapter<StateListAdapter.StateListViewHolder>() {

    var stateDataList = ArrayList<StateData>()

    inner class StateListViewHolder(state : View) : RecyclerView.ViewHolder(state) {
        val stateName : TextView = state.findViewById(R.id.tvStateName)
        val tvConfirm : TextView = state.findViewById(R.id.tvConfirmed)
        val tvActive : TextView = state.findViewById(R.id.tvActive)
        val tvRecovered : TextView = state.findViewById(R.id.tvRecovered)
        val tvDeceased : TextView = state.findViewById(R.id.tvDeceased)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateListViewHolder {
        val viewHolder = StateListViewHolder(LayoutInflater.from(context).inflate(R.layout.details_view, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: StateListViewHolder, position: Int) {
        holder.stateName.text = stateDataList[position].state
        holder.tvConfirm.text = SpannableDelta("${stateDataList[position].confirmed}\n ↑ ${stateDataList[position].deltaconfirmed?:"0"}",
                                               "#D50000",
                                                stateDataList[position].confirmed.length)

        holder.tvActive.text = stateDataList[position].active

        holder.tvDeceased.text = SpannableDelta("${stateDataList[position].deaths}\n ↑ ${stateDataList[position].deltadeaths?:"0"}",
                                                "#FFEA00",
                                                 stateDataList[position].deaths.length)
        holder.tvRecovered.text = SpannableDelta("${stateDataList[position].recovered}\n ↑ ${stateDataList[position].deltarecovered?:"0"}",
                                                "#00C853",
                                                 stateDataList[position].recovered.length)
    }

    override fun getItemCount(): Int = stateDataList.size

    fun updateList(updatedData : List<StateData>) {
        stateDataList.clear()
        stateDataList.addAll(updatedData)
        notifyDataSetChanged()
    }
}