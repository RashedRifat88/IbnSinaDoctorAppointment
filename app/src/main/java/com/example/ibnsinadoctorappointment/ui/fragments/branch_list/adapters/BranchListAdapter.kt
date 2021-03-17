package com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Branch
import kotlinx.android.synthetic.main.recycler_branch_list_item.view.*
import kotlinx.android.synthetic.main.recycler_investigation_item2.view.*
import kotlinx.android.synthetic.main.recycler_investigation_item2.view.tv_name
import kotlinx.android.synthetic.main.recycler_investigation_item2.view.tv_sl


class BranchListAdapter : RecyclerView.Adapter<BranchListAdapter.BranchViewHolder>() {

    var investigationsList = emptyList<Branch>()

    inner class BranchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        init {
//            itemView.cv_cardView.setOnClickListener {
//                val position = adapterPosition
//                Log.d("investigationsListAdapter", "Item clicked at: $position")
//
//                val action: NavDirections =
//                BranchListFragmentDirections.actionBranchListFragmentToUpdateBranchFragment(investigationsList[position])
//                itemView.findNavController().navigate(action)
//            }
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        return BranchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_branch_list_item, parent, false)
        )
    }

    //todo: initialise the recycler view and set it up to show data (part2)
    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val currentBranch = investigationsList[position]
//        holder.itemView.iv_Branch_icon.setImageResource(currentBranch.imageId)

        holder.itemView.tv_name.text = "${currentBranch.branchName}"
        holder.itemView.tv_desc.text = "${currentBranch.branchDesc}"
        holder.itemView.tv_address1.text = "${currentBranch.address1}"
        holder.itemView.tv_address2.text = "${currentBranch.address2}"
        holder.itemView.tv_email.text = "${currentBranch.email}"
        holder.itemView.tv_web.text = "${currentBranch.web}"
        holder.itemView.tv_contact1.text = "${currentBranch.contact1}"
//        holder.itemView.tv_price.text = "BDT: ${currentBranch.price}"
        holder.itemView.tv_sl.text = "${position + 1}"

        //            holder.itemView.tv_desc.isVisible = false

        if (currentBranch.branchDesc.isEmpty()) {
            holder.itemView.linear1.setVisibility(View.GONE)
//            holder.itemView.tv_desc.isVisible = false
        }
        if (currentBranch.address1.isEmpty()) {
            holder.itemView.linear2.setVisibility(View.GONE)
//            holder.itemView.tv_address1.isVisible = false
        }
        if (currentBranch.address2.isEmpty()) {
            holder.itemView.linear3.setVisibility(View.GONE)
//            holder.itemView.tv_address2.isVisible = false
        }
        if (currentBranch.email.isEmpty()) {
            holder.itemView.linear4.setVisibility(View.GONE)
//            holder.itemView.tv_email.isVisible = false
        }
        if (currentBranch.web.isEmpty()) {
            holder.itemView.linear5.setVisibility(View.GONE)
//            holder.itemView.tv_web.isVisible = false
        }
        if (currentBranch.contact1.isEmpty()) {
            holder.itemView.linear6.setVisibility(View.GONE)
//            holder.itemView.tv_contact1.isVisible = false
        }


        holder.itemView.tv_desc.setVisibility(View.GONE)
    }

    override fun getItemCount(): Int {
        return investigationsList.size
    }

    fun setData(Branch: List<Branch>) {
        this.investigationsList = Branch
        notifyDataSetChanged()
    }


}