package com.app.task.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.task.Interface.ItemListener
import com.app.task.R
import com.app.task.models.MedicineData

/**
 * Created by Raju
 */

class MedicineAdapter(
    var medicineItemList: List<MedicineData>,
    var context: Context,
    val itemClickListner: ItemListener
) : RecyclerView.Adapter<MedicineAdapter.NameViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_medicine, parent, false)

        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bindData(
            medicineItemList,
            itemClickListner,
            context,
            position
        )
    }

    /**
     * Returns item counts
     * or list size
     */

    override fun getItemCount(): Int {
        return medicineItemList.size
    }

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName = itemView.findViewById<TextView>(R.id.txtName)
        fun bindData(
            alertItemList: List<MedicineData>,
            itemClickListner: ItemListener,
            context: Context,
            position: Int
        ) {

            txtName?.text = alertItemList[position].name
            itemView.setOnClickListener() {
                itemClickListner.itemClicked(position, context)
            }
        }
    }
}