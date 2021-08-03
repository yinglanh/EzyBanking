package com.example.ezybanking.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ezybanking.R
import com.example.ezybanking.model.Transaction
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var transactionList = emptyList<Transaction>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return transactionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = transactionList[position]
        holder.itemView.tv_id.text = currentItem.id.toString()
        holder.itemView.tv_amount.text = currentItem.amount.toString()
        holder.itemView.tv_description.text = currentItem.description
        holder.itemView.tv_date.text = currentItem.date
    }

    fun setData(transaction: List<Transaction>){
        this.transactionList = transaction
        notifyDataSetChanged()
    }
}