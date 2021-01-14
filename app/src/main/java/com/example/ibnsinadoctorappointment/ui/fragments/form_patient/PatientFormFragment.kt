package com.example.ibnsinadoctorappointment.ui.fragments.form_patient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.example.ibnsinadoctorappointment.R
import kotlinx.android.synthetic.main.fragment_patient_form.*


class PatientFormFragment : Fragment(R.layout.fragment_patient_form) {


    private var doctorName: String = ""
    private var doctorDeg: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doctorName = arguments?.getString("doctork_name").toString()
        doctorDeg = arguments?.getString("doctork_qualification").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setText()
    }

    private fun setText() {
        tv_doctor_name.text = doctorName
        tv_doctor_deg.text = doctorDeg
    }

}