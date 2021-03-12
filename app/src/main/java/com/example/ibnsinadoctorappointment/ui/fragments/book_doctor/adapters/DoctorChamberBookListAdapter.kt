package com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.data.models.chamber_availavle.ChamberAvailableModel
import com.example.ibnsinadoctorappointment.retrofit.APIServices
import com.example.ibnsinadoctorappointment.retrofit.RetrofitClientInstance
import com.example.ibnsinadoctorappointment.ui.viewmodels.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_doctor_chamber_book_item.view.*
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.*
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.tv_book
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.tv_branch
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.tv_deg
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.tv_dept
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.tv_designation
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.tv_name
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DoctorChamberBookListAdapter :
    RecyclerView.Adapter<DoctorChamberBookListAdapter.DoctorChamberBookViewHolder>() {

    var chamberList = emptyList<DoctorChamberBook>()
    var doctor_appointment_date: String = ""

    inner class DoctorChamberBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        init {
//            itemView.cv_cardView.setOnClickListener {
//                val position = adapterPosition
//                Log.d("chamberListAdapter", "Item clicked at: $position")
//
//                val action: NavDirections =
//                DoctorChamberBookListFragmentDirections.actionDoctorChamberBookListFragmentToUpdateDoctorChamberBookFragment(chamberList[position])
//                itemView.findNavController().navigate(action)
//            }
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorChamberBookViewHolder {
        return DoctorChamberBookViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_doctor_chamber_book_item, parent, false)
        )
    }

    //todo: initialise the recycler view and set it up to show data (part2)
    override fun onBindViewHolder(holder: DoctorChamberBookViewHolder, position: Int) {
        val currentDoctorChamberBook = chamberList[position]
////        holder.itemView.iv_DoctorChamberBook_icon.setImageResource(currentDoctorChamberBook.imageId)
//
//        holder.itemView.tv_price.text = "Phone: ${currentDoctorChamberBook.phone}"

        holder.itemView.tv_name.text = "${currentDoctorChamberBook.nickName}"
        holder.itemView.tv_deg.text = "${currentDoctorChamberBook.designation}"
        holder.itemView.tv_branch.text = "${currentDoctorChamberBook.branchName}"

        holder.itemView.tv_date.text = "${currentDoctorChamberBook.appointmentLockedDate}"
        holder.itemView.tv_name.text = "${currentDoctorChamberBook.nickName}"
        holder.itemView.tv_deg.text = "${currentDoctorChamberBook.qualification}"
        holder.itemView.tv_designation.text = "${currentDoctorChamberBook.designation}"
        holder.itemView.tv_dept.text = "${currentDoctorChamberBook.deptName}"
        holder.itemView.tv_branch.text = "${currentDoctorChamberBook.branchName}"


//        holder.itemView.tv_book.setOnClickListener {
//            findNavController().navigate(R.id.action_bookDoctorFragment_to_patientFormFragment)
//        }


//        val bundle1 = bundleOf("nickName" to currentDoctorChamberBook.nickName)
//        val bundle2 = bundleOf("qualification" to currentDoctorChamberBook.qualification)
////        view.findNavController().navigate(R.id.confirmationAction, bundle)

        val bundle = Bundle()
        bundle.putString("fromWhere", "DoctorChamberBook")
        bundle.putString("doctork_chamber_id", currentDoctorChamberBook.dbNo.toString())
        bundle.putString("days2TakeAppoint", currentDoctorChamberBook.days2TakeAppoint)
        bundle.putString("doctork_name", currentDoctorChamberBook.nickName)
        bundle.putString("doctork_qualification", currentDoctorChamberBook.qualification)
        bundle.putString("doctor_branch", currentDoctorChamberBook.branchName)
        bundle.putString("doctor_appointment_date", doctor_appointment_date)

        holder.itemView.tv_book.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_bookDoctorFragment_to_patientFormFragment,
                bundle
            )
        )

//        holder.itemView.tv_book.setOnClickListener{ view ->
////            view.findNavController().navigate(R.id.action_bookDoctorFragment_to_patientFormFragment, bundle)
//
//            Navigation.findNavController(view).navigate(R.id.action_bookDoctorFragment_to_patientFormFragment,bundle)
//        }


    }



    override fun getItemCount(): Int {
        return chamberList.size
    }


    fun setData(DoctorChamberBook: List<DoctorChamberBook>, doctor_appointment_date: String) {

        this.chamberList = DoctorChamberBook
        this.doctor_appointment_date = doctor_appointment_date
        notifyDataSetChanged()
        Log.d("tag55551", "chamberList: $chamberList")
        Log.d("tag55551", "chamberList.size: ${chamberList.size}")
    }






}