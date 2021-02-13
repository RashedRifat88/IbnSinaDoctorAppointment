package com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Investigation
import kotlinx.android.synthetic.main.recycler_investigation_item2.view.*


class InvestigationListAdapter : RecyclerView.Adapter<InvestigationListAdapter.InvestigationViewHolder>() {

    var investigationsList = emptyList<Investigation>()

    inner class InvestigationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        init {
//            itemView.cv_cardView.setOnClickListener {
//                val position = adapterPosition
//                Log.d("investigationsListAdapter", "Item clicked at: $position")
//
//                val action: NavDirections =
//                InvestigationListFragmentDirections.actionInvestigationListFragmentToUpdateInvestigationFragment(investigationsList[position])
//                itemView.findNavController().navigate(action)
//            }
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvestigationViewHolder {
        return InvestigationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_investigation_item2, parent,false))
    }

    //todo: initialise the recycler view and set it up to show data (part2)
    override fun onBindViewHolder(holder: InvestigationViewHolder, position: Int) {
        val currentInvestigation = investigationsList[position]
//        holder.itemView.iv_Investigation_icon.setImageResource(currentInvestigation.imageId)

        holder.itemView.tv_name.text = "${currentInvestigation.name}"
        holder.itemView.tv_price.text = "BDT: ${currentInvestigation.price}"
        holder.itemView.tv_sl.text = "${position+1}"

        Log.d("tag2345", "currentInvestigation.discount_percentage: " + currentInvestigation.discount_percentage)

        var discount = currentInvestigation.discount_percentage
        if (discount != null){
            holder.itemView.tv_discount_percentage.text = "${currentInvestigation.discount_percentage} %"
        }else{
            holder.itemView.tv_discount_percentage.text = "N/A"
        }
    }

    override fun getItemCount(): Int {
        return investigationsList.size
    }

    fun setData(Investigation: List<Investigation>) {
        this.investigationsList = Investigation
        notifyDataSetChanged()
    }


}