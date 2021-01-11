package com.example.ibnsinadoctorappointment.ui.fragments.doctor_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters.DoctorListAdapter
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_doctor_list.*


class DoctorListFragment : Fragment(R.layout.fragment_doctor_list) {


    private lateinit var doctorList: List<Doctor>
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var adapter: DoctorListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DoctorListAdapter()
        rv_doctor.adapter = adapter
        rv_doctor.layoutManager = LinearLayoutManager(context)

        //Instantiate and create viewmodel observers
        viewModels()

    }


    private fun viewModels() {
        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)

        doctorViewModel.getAllDoctors.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            Log.d("tagrifat33333", "doctor list: $it")

            if (it.isEmpty()) {
                rv_doctor.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_doctor.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }
        })
    }

}