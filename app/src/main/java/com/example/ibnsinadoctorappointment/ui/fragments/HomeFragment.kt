package com.example.ibnsinadoctorappointment.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ibnsinadoctorappointment.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickEvent()

        loadAnimations()
//        loadAnimations2()

    }

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


        iv_logo.startAnimation(ttb)

        linear1.startAnimation(btt1)
        linear2.startAnimation(btt2)
        linear3.startAnimation(btt3)
        linear4.startAnimation(btt4)

    }

    private fun loadAnimations2() {
        iv_logo.playAnimation()

//        iv_logo.cancelAnimation();
    }


    override fun onStart() {
        super.onStart()
        iv_logo.playAnimation()
    }

    override fun onStop() {
        super.onStop()
//        iv_logo.cancelAnimation()
    }

}