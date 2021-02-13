package com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.ui.viewmodels.BranchViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import kotlinx.android.synthetic.main.recycler_doctor_item2.view.*
import kotlinx.android.synthetic.main.recycler_investigation_item2.view.*
import kotlinx.android.synthetic.main.recycler_investigation_item2.view.tv_name


class DoctorListAdapter : RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>() {

    private var context: Context? = null
//    private lateinit var branchViewModel: BranchViewModel


    var doctorsList = emptyList<Doctor>()

    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


//        init {
//            itemView.cv_cardView.setOnClickListener {
//                val position = adapterPosition
//                Log.d("doctorsListAdapter", "Item clicked at: $position")
//
//                val action: NavDirections =
//                DoctorListFragmentDirections.actionDoctorListFragmentToUpdateDoctorFragment(doctorsList[position])
//                itemView.findNavController().navigate(action)
//            }
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        context = parent.context
        return DoctorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_doctor_item2,
                parent,
                false
            )
        )
    }

    //todo: initialise the recycler view and set it up to show data (part2)
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val currentDoctor = doctorsList[position]
//        holder.itemView.iv_Doctor_icon.setImageResource(currentDoctor.imageId)


//        branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)

        var branch_id: Int = currentDoctor.branchNo

        holder.itemView.tv_name.text = "${currentDoctor.nickName}"
        holder.itemView.tv_deg.text = "${currentDoctor.qualification}"
//        holder.itemView.tv_sl.text = "${position+1}"

        holder.itemView.tv_dept.text = "${currentDoctor.doctorDept}"
        holder.itemView.tv_designation.text = "${currentDoctor.designation}"
        holder.itemView.tv_branch.text = "${currentDoctor.designation}"
        var img_rul  = "${currentDoctor.avatarPath}"

//        Log.d("tag2345", "currentDoctor.discount_percentage: " + currentDoctor.discount_percentage)



        val bundle = Bundle()
        bundle.putString("doctork_name", currentDoctor.nickName)
        bundle.putString("doctork_qualification", currentDoctor.qualification)

        holder.itemView.tv_book.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_doctorListFragment_to_patientFormFragment,
                bundle
            )
        )


        holder.itemView.tv_details.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_doctorListFragment_to_doctorDetailsFragment,
                bundle
            )
        )

    }

    override fun getItemCount(): Int {
        return doctorsList.size
    }

    fun setData(Doctor: List<Doctor>) {
        this.doctorsList = Doctor
        notifyDataSetChanged()
    }




//    private fun searchDoctorByName(title: String) {
//        var searchText = title
//        searchText = "%$title%"
//
//        branchViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
//        branchViewModel.searchByName(searchText).observe(viewLifecycleOwner, Observer {
//            doctorList = it
//
//        })
//
//    }



}