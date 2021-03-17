package com.example.ibnsinadoctorappointment.ui.fragments.doctor_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.ui.fragments.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_doctor_details.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.linear1
import kotlinx.android.synthetic.main.fragment_patient_form.*


class DoctorDetailsFragment : Fragment(R.layout.fragment_doctor_details) {


    private var doctorName: String = ""
    private var doctorDeg: String = ""
    private var doctor_department: String = ""
    private var doctor_designation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doctorName = arguments?.getString("doctor_name").toString()
        doctorDeg = arguments?.getString("doctor_qualification").toString()
        doctor_department = arguments?.getString("doctor_department").toString()
        doctor_designation = arguments?.getString("doctor_designation").toString()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickEvent()
        setText()
    }


    private fun setText() {
        tv_speciality_name.text = doctor_department

        tv_name_details.text = doctorName
        tv_degree_details.text = doctorDeg
        tv_designation_details.text = doctor_designation
        tv_branch_details.text = doctorDeg
//        tv_mobile_details.text = doctorDeg
    }


    private fun clickEvent() {

        val bundle = Bundle()
        bundle.putString("doctor_name", doctorName)
        bundle.putString("doctor_qualification", doctorDeg)

        btn_book_details.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_doctorDetailsFragment_to_patientFormFragment, bundle)
        )
    }




}