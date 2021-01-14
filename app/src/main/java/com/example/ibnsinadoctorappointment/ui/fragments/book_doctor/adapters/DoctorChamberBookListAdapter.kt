package com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.*


class DoctorChamberBookListAdapter : RecyclerView.Adapter<DoctorChamberBookListAdapter.DoctorChamberBookViewHolder>() {

    var investigationsList = emptyList<DoctorChamberBook>()

    inner class DoctorChamberBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        init {
//            itemView.cv_cardView.setOnClickListener {
//                val position = adapterPosition
//                Log.d("investigationsListAdapter", "Item clicked at: $position")
//
//                val action: NavDirections =
//                DoctorChamberBookListFragmentDirections.actionDoctorChamberBookListFragmentToUpdateDoctorChamberBookFragment(investigationsList[position])
//                itemView.findNavController().navigate(action)
//            }
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorChamberBookViewHolder {
        return DoctorChamberBookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_doctor_chamber_book_item, parent,false))
    }

    //todo: initialise the recycler view and set it up to show data (part2)
    override fun onBindViewHolder(holder: DoctorChamberBookViewHolder, position: Int) {
        val currentDoctorChamberBook = investigationsList[position]
////        holder.itemView.iv_DoctorChamberBook_icon.setImageResource(currentDoctorChamberBook.imageId)
//
        holder.itemView.tv_name.text = "${currentDoctorChamberBook.nickName}"
//        holder.itemView.tv_price.text = "Phone: ${currentDoctorChamberBook.phone}"
        holder.itemView.tv_deg.text = "${currentDoctorChamberBook.qualification}"
        holder.itemView.tv_branch.text = "${currentDoctorChamberBook.branchName}"


//        holder.itemView.tv_book.setOnClickListener {
//            findNavController().navigate(R.id.action_bookDoctorFragment_to_patientFormFragment)
//        }



//        val bundle1 = bundleOf("nickName" to currentDoctorChamberBook.nickName)
//        val bundle2 = bundleOf("qualification" to currentDoctorChamberBook.qualification)
////        view.findNavController().navigate(R.id.confirmationAction, bundle)

        val bundle = Bundle()
        bundle.putString("doctork_name", currentDoctorChamberBook.nickName)
        bundle.putString("doctork_qualification", currentDoctorChamberBook.qualification)

        holder.itemView.tv_book.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_bookDoctorFragment_to_patientFormFragment, bundle)
        )



    }

    override fun getItemCount(): Int {
        return investigationsList.size
    }

    fun setData(DoctorChamberBook: List<DoctorChamberBook>) {
        this.investigationsList = DoctorChamberBook
        notifyDataSetChanged()
    }


}