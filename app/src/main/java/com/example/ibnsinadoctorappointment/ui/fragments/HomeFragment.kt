package com.example.ibnsinadoctorappointment.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.data.models.Investigation
import com.example.ibnsinadoctorappointment.data.models.doctor.Content
import com.example.ibnsinadoctorappointment.ui.viewmodels.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment(R.layout.fragment_home) {



    private lateinit var investigationViewModel: InvestigationViewModel
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var doctorChamberBookViewModel: DoctorChamberBookViewModel
    private lateinit var branchViewModel: BranchViewModel
    private lateinit var departmentViewModel: DepartmentViewModel
    private lateinit var healthPackageViewModel: HealthPackageViewModel
    private lateinit var lastUpdateViewModel: LastUpdateViewModel

    private lateinit var doctorList: List<Doctor>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickEvent()

        loadAnimations()

//        deleteDoctorData()
//        loadDoctorData()

        loadDoctor()

    }


    private fun loadDoctor() {
        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)

        doctorViewModel.getAllDoctors.observe(viewLifecycleOwner, Observer {
            doctorList = it
        })

//        updateDoctorData()
    }


//    private fun updateDoctorData() {
//        doctorChamberBookViewModel =
//            ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
//
//
//        for (i in 0 until doctorList.size) {
//            val itemDetail = doctorList.get(i)
//
//            var branch: DoctorChamberBook =
//                doctorChamberBookViewModel.getBranchByDoctorId(itemDetail.id)
//
//            Log.d("tagRifat33333444444", "branch.branchName is: " + branch.branchName)
//
////                        var doctorBranch: String = branch.value?.branchName.toString()
//            var doctorBranch = branch.branchName?.toString()
////                        var doctorBranch: String = branch.
//            Log.d("tagRifat33333444444", "itemDetail.id is: " + itemDetail.id)
//            Log.d("tagRifat33333444444", "doctorBranch is: " + doctorBranch)
//            Log.d("tagRifat33333444444", "branch is: " + branch)
//
//
//            val doctor = Doctor(
//                itemDetail.id,
//                itemDetail.nickName,
//                itemDetail.avatarPath,
//                itemDetail.doctorDept,
//                itemDetail.branchNo,
//                itemDetail.qualification,
//                itemDetail.designation,
//                doctorBranch
//            )
//
//
//            doctorViewModel.updateDoctor(doctor)
//        }
//    }


    private fun deleteDoctorData() {
        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        doctorViewModel.deleteAllDoctors()
    }

//    private fun loadDoctorData() {
//        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
//
//        val jsonText = resources.openRawResource(R.raw.doctor_response)
//            .bufferedReader().use { it.readText() }
//
//        val obj = JSONObject(jsonText)
//        val itemArray = obj.getJSONArray("content")
//        for (i in 0 until itemArray.length()) {
//            val itemDetail = itemArray.getJSONObject(i)
//            Log.d("tagRifat33333", "name is: " + itemDetail.getString("nickName"))
//            val doctor = Doctor(
//                itemDetail.getInt("id"),
//                itemDetail.getString("nickName"),
//                itemDetail.getString("qualification"),
//                itemDetail.getString("designation")
//            )
//            doctorViewModel.addDoctor(doctor)
//        }
//
//        Log.d("tagRifat1234", "json is: " + jsonText)
//    }


    private fun clickEvent() {
        linear1.setOnClickListener {
//                findNavController().navigate(R.id.action_homeFragment_to_doctorListFragment)
            val action = HomeFragmentDirections.actionHomeFragmentToDoctorListFragment()
            findNavController().navigate(action)
        }

        linear2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bookDoctorFragment)
        }

        linear3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_investigationFragment)
        }

        linear4.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_healthPackageFragment)
        }

    }


    private fun loadAnimations() {
        val ttb = AnimationUtils.loadAnimation(context, R.anim.ttb);
        val stb = AnimationUtils.loadAnimation(context, R.anim.stb);
        val btt1 = AnimationUtils.loadAnimation(context, R.anim.btt1);
        val btt2 = AnimationUtils.loadAnimation(context, R.anim.btt2);
        val btt3 = AnimationUtils.loadAnimation(context, R.anim.btt3);
        val btt4 = AnimationUtils.loadAnimation(context, R.anim.btt4);
        val btt5 = AnimationUtils.loadAnimation(context, R.anim.btt5);
        val btt6 = AnimationUtils.loadAnimation(context, R.anim.btt6);

//        iv_logo.startAnimation(ttb)

        linear1.startAnimation(btt1)
        linear2.startAnimation(btt2)
        linear3.startAnimation(btt3)
        linear4.startAnimation(btt4)
        linear5.startAnimation(btt5)
        linear6.startAnimation(btt6)

    }

    private fun loadAnimations2() {
        iv_logo.playAnimation()

//        iv_logo.cancelAnimation();
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
//        iv_logo.playAnimation()
//        loadAnimations()
    }

    override fun onStop() {
        super.onStop()
//        iv_logo.cancelAnimation()

//        cancelAnimations()
    }

}