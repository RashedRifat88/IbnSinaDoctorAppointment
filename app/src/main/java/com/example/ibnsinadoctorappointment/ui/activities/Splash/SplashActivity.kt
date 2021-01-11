package com.example.ibnsinadoctorappointment.ui.activities.Splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.ViewModelProvider
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.data.models.Investigation
import com.example.ibnsinadoctorappointment.ui.activities.MainActivity
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorChamberBookViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.InvestigationViewModel
import org.json.JSONObject

class SplashActivity : AppCompatActivity() {


    private lateinit var investigationViewModel: InvestigationViewModel
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var doctorChamberBookViewModel: DoctorChamberBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadJsonDataIntoDatabase()

        hideTitleBar()
        setContentView(R.layout.activity_splash)

        val motionLayout = findViewById<MotionLayout>(R.id.motionLayout)
        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

        })
    }


    private fun loadJsonDataIntoDatabase() {
        deleteInvestigationData()
        loadInvestigationData()

//        deleteDoctorData()
//        loadDoctorData()

        deleteDoctorChamberBookData()
        loadDoctorChamberBookData()
    }

    private fun hideTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


    }



    private fun deleteDoctorData() {
        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        doctorViewModel.deleteAllDoctors()
    }

    private fun loadDoctorData() {
        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)

        val jsonText = resources.openRawResource(R.raw.doctor_response)
            .bufferedReader().use { it.readText() }

        val obj = JSONObject(jsonText)
        val itemArray = obj.getJSONArray("content")
        for (i in 0 until itemArray.length()) {
            val itemDetail = itemArray.getJSONObject(i)
            Log.d("tagRifat33333", "name is: " + itemDetail.getString("nickName"))
            val doctor = Doctor(
                itemDetail.getInt("id"),
                itemDetail.getString("nickName"),
                itemDetail.getString("phone"),
                itemDetail.getString("designation")
            )
            doctorViewModel.addDoctor(doctor)
        }

        Log.d("tagRifat1234", "json is: " + jsonText)
    }


    private fun deleteDoctorChamberBookData() {
        doctorChamberBookViewModel =
            ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorChamberBookViewModel.deleteAllDoctorChamberBooks()
    }

    private fun loadDoctorChamberBookData() {
        doctorChamberBookViewModel =
            ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)

        val jsonText = resources.openRawResource(R.raw.doctor_chamber_response)
            .bufferedReader().use { it.readText() }

        val obj = JSONObject(jsonText)
        val itemArray = obj.getJSONArray("content")
        for (i in 0 until itemArray.length()) {
            val itemDetail = itemArray.getJSONObject(i)
            Log.d("tagRifat1234", "name is: " + itemDetail.getString("dbNo"))
            val doctorBook = DoctorChamberBook(
                itemDetail.getInt("dbNo"),
                itemDetail.getString("nickName"),
                itemDetail.getString("qualification"),
                itemDetail.getString("branchName")
//                itemDetail.getString("designation")
            )
            doctorChamberBookViewModel.addDoctorChamberBook(doctorBook)
        }

        Log.d("tagRifat1234", "json is: " + jsonText)
    }


    private fun deleteInvestigationData() {
        investigationViewModel = ViewModelProvider(this).get(InvestigationViewModel::class.java)
        investigationViewModel.deleteAllInvestigations()
    }

    private fun loadInvestigationData() {
        investigationViewModel = ViewModelProvider(this).get(InvestigationViewModel::class.java)
        insertInvestigations()
    }

    private fun insertInvestigations() {

        val jsonText = resources.openRawResource(R.raw.investigation_response)
            .bufferedReader().use { it.readText() }

        val obj = JSONObject(jsonText)
        val itemArray = obj.getJSONArray("items")
        for (i in 0 until itemArray.length()) {
            val itemDetail = itemArray.getJSONObject(i)
            Log.d("tagRifat1234", "name is: " + itemDetail.getString("name"))
            val investigation = Investigation(
                i + 1,
                itemDetail.getString("name"),
                itemDetail.getString("price"),
                itemDetail.getString("discount_percentage")
            )
            investigationViewModel.addInvestigation(investigation)
        }

        Log.d("tagRifat1234", "json is: " + jsonText)


//        val investigation = Investigation(0,"ABORH(BL.GROUP)","0.00", "35 percent")
//        investigationViewModel.addInvestigation(investigation)

    }


}