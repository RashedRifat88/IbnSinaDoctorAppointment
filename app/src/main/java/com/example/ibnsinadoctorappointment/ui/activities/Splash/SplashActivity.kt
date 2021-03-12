package com.example.ibnsinadoctorappointment.ui.activities.Splash

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.database.shared_pref.SharedPreference
import com.example.ibnsinadoctorappointment.data.models.*
import com.example.ibnsinadoctorappointment.data.models.branch.BranchModel
import com.example.ibnsinadoctorappointment.data.models.department.DepartmentModel
import com.example.ibnsinadoctorappointment.data.models.doctor.Content
import com.example.ibnsinadoctorappointment.data.models.doctor.DoctorModel
import com.example.ibnsinadoctorappointment.data.models.doctor_chamber_book.DoctorChamberBookModel
import com.example.ibnsinadoctorappointment.data.models.investigation.InvestigationModel
import com.example.ibnsinadoctorappointment.data.models.last_update.LastUpdateModel
import com.example.ibnsinadoctorappointment.retrofit.APIServices
import com.example.ibnsinadoctorappointment.retrofit.RetrofitClientInstance
import com.example.ibnsinadoctorappointment.ui.activities.MainActivity
import com.example.ibnsinadoctorappointment.ui.viewmodels.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    private val permission = 101
    var mMediaPlayer: MediaPlayer? = null

    private lateinit var investigationViewModel: InvestigationViewModel
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var doctorChamberBookViewModel: DoctorChamberBookViewModel
    private lateinit var branchViewModel: BranchViewModel
    private lateinit var departmentViewModel: DepartmentViewModel
    private lateinit var healthPackageViewModel: HealthPackageViewModel
    private lateinit var lastUpdateViewModel: LastUpdateViewModel

    var sharedPreference: SharedPreference? = null
    private val KEY_BRANCH_UPDATE = "branch_update"
    private val KEY_CHAMBER_UPDATE = "chamber_update"
    private val KEY_DEPARTMENT_UPDATE = "department_update"
    private val KEY_DOCTOR_UPDATE = "doctor_update"
    private val KEY_INVESTIGATION_UPDATE = "investigation_update"
    private val KEY_LAST_UPDATE = "last_update"
    private val KEY_PACKAGE_UPDATE = "package_update"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        startTone()

        sharedPreference = SharedPreference(this)
        loadJsonDataIntoDatabase_last_update()
//        loadJsonDataIntoDatabase()

        hideTitleBar()
        setContentView(R.layout.activity_splash)

        val motionLayout = findViewById<MotionLayout>(R.id.motionLayout)
        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

//                stopTone()

                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

        })


//        userPhoneNumber()
    }

    private fun userPhoneNumber() {
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as
                TelephonyManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE),
                permission
            )
        }

        var userPhone = telephonyManager.line1Number
        Log.d("tagRifat33333", "userPhone is: " + telephonyManager.line1Number)
//        Log.d("tagRifat33333", "simSerialNumber is: " + telephonyManager.simSerialNumber)
//        Log.d("tagRifat33333", "simSerialNumber is: " + telephonyManager.setNetworkSelectionModeAutomatic())
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray) {
        when (requestCode) {
            permission -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



//
//    private fun startTone() {
//
//        try {
//            mMediaPlayer = MediaPlayer.create(this, R.raw.sound_bird1)
//            mMediaPlayer!!.start()
//            mMediaPlayer!!.isLooping = true
//        } catch (e: java.lang.Exception) {
//            Log.d("taq1000", "ringtone exception :  $e")
//        }
//    }
//
//    private fun stopTone() {
//        Log.d("taq1000", "ringtone stop called :  ")
//        try {
//            if (mMediaPlayer?.isPlaying()!!) {
//                mMediaPlayer!!.stop()
//                mMediaPlayer!!.release()
//                //            mMediaPlayer = null;
//            }
//        } catch (e: Exception) {
//        }
//    }




    private fun loadJsonDataIntoDatabase_last_update() {
        lastUpdateViewModel = ViewModelProvider(this).get(LastUpdateViewModel::class.java)
        deleteLastUpdateData()
        lastUpdateApiCall()
    }


    private fun lastUpdateApiCall() {
        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.getLastUpdates()

//        doctorChamberBookApiCall()

        call.enqueue(object : Callback<LastUpdateModel> {
            override fun onResponse(
                call: Call<LastUpdateModel>,
                response: Response<LastUpdateModel>
            ) {
                if (response.isSuccessful) {

                    val last_update = response.body()!!.last_update
                    val branch_update = response.body()!!.branch_update
                    val chamber_update = response.body()!!.chamber_update
                    val department_update = response.body()!!.department_update
                    val doctor_update = response.body()!!.doctor_update
                    val investigation_update = response.body()!!.investigation_update
                    val package_update = response.body()!!.package_update
//                    val last_update = response.body()!!.last_update

                    if (!last_update.equals(
                            sharedPreference!!.getValueString(KEY_LAST_UPDATE),
                            ignoreCase = true
                        )
                    ) {
                        sharedPreference!!.save(KEY_LAST_UPDATE, last_update)
                    }

                    if (!branch_update.equals(
                            sharedPreference!!.getValueString(KEY_BRANCH_UPDATE),
                            ignoreCase = true
                        )
                    ) {
                        sharedPreference!!.save(KEY_BRANCH_UPDATE, branch_update)

                        branchViewModel =
                            ViewModelProvider(this@SplashActivity).get(BranchViewModel::class.java)
                        deleteBranchData()
                        branchApiCall()
                    }

                    if (!chamber_update.equals(
                            sharedPreference!!.getValueString(KEY_CHAMBER_UPDATE),
                            ignoreCase = true
                        )
                    ) {
                        sharedPreference!!.save(KEY_CHAMBER_UPDATE, chamber_update)

                        doctorChamberBookViewModel =
                            ViewModelProvider(this@SplashActivity).get(DoctorChamberBookViewModel::class.java)
                        deleteDoctorChamberBookData()
                        doctorChamberBookApiCall()
                    }

                    if (!department_update.equals(
                            sharedPreference!!.getValueString(KEY_DEPARTMENT_UPDATE),
                            ignoreCase = true
                        )
                    ) {
                        sharedPreference!!.save(KEY_DEPARTMENT_UPDATE, department_update)

                        departmentViewModel = ViewModelProvider(this@SplashActivity).get(
                            DepartmentViewModel::class.java
                        )
                        deleteDepartmentData()
                        departmentApiCall()
                    }

                    if (!doctor_update.equals(
                            sharedPreference!!.getValueString(KEY_DOCTOR_UPDATE),
                            ignoreCase = true
                        )
                    ) {
                        sharedPreference!!.save(KEY_DOCTOR_UPDATE, doctor_update)

                        doctorViewModel =
                            ViewModelProvider(this@SplashActivity).get(DoctorViewModel::class.java)
                        deleteDoctorData()
                        doctorApiCall()
                    }

                    if (!investigation_update.equals(
                            sharedPreference!!.getValueString(KEY_INVESTIGATION_UPDATE),
                            ignoreCase = true
                        )
                    ) {
                        sharedPreference!!.save(KEY_INVESTIGATION_UPDATE, investigation_update)

                        investigationViewModel = ViewModelProvider(this@SplashActivity).get(
                            InvestigationViewModel::class.java
                        )
                        deleteInvestigationData()
                        investigationApiCall()

                    }

                    if (!package_update.equals(
                            sharedPreference!!.getValueString(KEY_PACKAGE_UPDATE),
                            ignoreCase = true
                        )
                    ) {
                        sharedPreference!!.save(KEY_PACKAGE_UPDATE, package_update)
                    }


                    Log.d("tagRifat33333444", "last_update response is: " + last_update)
                    Log.d("tagRifat33333444", "last_update response is: " + response.body())

                    Toast.makeText(
                        this@SplashActivity,
                        "response last_update : " + last_update.toString(),
                        Toast.LENGTH_LONG
                    ).show()

                }
            }

            override fun onFailure(call: Call<LastUpdateModel>, t: Throwable) {
                Log.d("tagRifat33333444", "last_update response is: " + t.message)
                Toast.makeText(this@SplashActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }


    private fun loadJsonDataIntoDatabase() {


//        doctorChamberBookViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
//        deleteDoctorChamberBookData()
//        doctorChamberBookApiCall()

//        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
//        deleteDoctorData()
//        doctorApiCall()


//        investigationViewModel = ViewModelProvider(this).get(InvestigationViewModel::class.java)
//        deleteInvestigationData()
//        investigationApiCall()


//        branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)
//        deleteBranchData()
//        branchApiCall()


//        departmentViewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
//        deleteDepartmentData()
//        departmentApiCall()

    }

    private fun doctorApiCall() {

        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.getAllDoctors()

        call.enqueue(object : Callback<DoctorModel> {
            override fun onResponse(call: Call<DoctorModel>, response: Response<DoctorModel>) {
                if (response.isSuccessful) {

                    val contents: List<Content> = response.body()!!.content

                    Toast.makeText(
                        this@SplashActivity,
                        "response: " + contents.toString(),
                        Toast.LENGTH_LONG
                    ).show()

//                    doctorChamberBookViewModel = ViewModelProvider(this@SplashActivity).get(DoctorChamberBookViewModel::class.java)
//                    var doctorBranch = doctorChamberBookViewModel.getBranchByDoctorId(53)
                    Log.d("tagRifat33333", "response is: " + contents)


                    for (i in 0 until contents.size) {
                        val itemDetail = contents.get(i)

//                        var doctorBranch = doctorChamberBookViewModel.getBranchByDoctorId(itemDetail.id)

                        val doctor = Doctor(
                            itemDetail.id,
                            itemDetail.nickName,
                            itemDetail.avatarPath,
                            itemDetail.doctorDept.name,
                            itemDetail.branchNo,
                            itemDetail.qualification,
                            itemDetail.designation,
                            itemDetail.branchNo.toString()

                        )
                        doctorViewModel.addDoctor(doctor)
                    }

                }
            }

            override fun onFailure(call: Call<DoctorModel>, t: Throwable) {
                Toast.makeText(this@SplashActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun departmentApiCall() {
        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.getDepartments()

        call.enqueue(object : Callback<DepartmentModel> {
            override fun onResponse(
                call: Call<DepartmentModel>,
                response: Response<DepartmentModel>
            ) {
                if (response.isSuccessful) {

                    val contents: List<com.example.ibnsinadoctorappointment.data.models.department.Content> =
                        response.body()!!.content

                    Toast.makeText(
                        this@SplashActivity,
                        "response: " + contents.toString(),
                        Toast.LENGTH_LONG
                    ).show()

                    Log.d("tagRifat33333", "response is: " + contents)

                    for (i in 0 until contents.size) {
                        val itemDetail = contents.get(i)

                        val doctor = Department(
                            itemDetail.id,
                            itemDetail.name,
                            itemDetail.enabled
                        )
                        departmentViewModel.addDepartment(doctor)
                    }

                }
            }

            override fun onFailure(call: Call<DepartmentModel>, t: Throwable) {
                Toast.makeText(this@SplashActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun branchApiCall() {

        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.getBranches()

        call.enqueue(object : Callback<BranchModel> {
            override fun onResponse(call: Call<BranchModel>, response: Response<BranchModel>) {
                if (response.isSuccessful) {

                    val contents: List<com.example.ibnsinadoctorappointment.data.models.branch.Content> =
                        response.body()!!.content

                    Toast.makeText(
                        this@SplashActivity,
                        "response: " + contents.toString(),
                        Toast.LENGTH_LONG
                    ).show()

                    Log.d("tagRifat33333", "doctor response is: " + contents)

                    for (i in 0 until contents.size) {
                        val itemDetail = contents.get(i)

                        val doctor = Branch(
                            itemDetail.id,
                            itemDetail.branchName,
//                            itemDetail.address1 as String,
//                            itemDetail.address2 as String,
                            itemDetail.contact1,
                            itemDetail.contact2,
                            itemDetail.contactPerson
                        )
                        branchViewModel.addBranch(doctor)
                    }

                }
            }

            override fun onFailure(call: Call<BranchModel>, t: Throwable) {
                Toast.makeText(this@SplashActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }


    private fun doctorChamberBookApiCall() {

        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.getChamberDoctors()

        call.enqueue(object : Callback<DoctorChamberBookModel> {
            override fun onResponse(
                call: Call<DoctorChamberBookModel>,
                response: Response<DoctorChamberBookModel>
            ) {
                if (response.isSuccessful) {

                    val contents: List<com.example.ibnsinadoctorappointment.data.models.doctor_chamber_book.Content> =
                        response.body()!!.content

                    Log.d("tagRifat33333", "DoctorChamberBook response is: " + contents)

                    Toast.makeText(
                        this@SplashActivity,
                        "response: " + contents.toString(),
                        Toast.LENGTH_LONG
                    ).show()


                    for (i in 0 until contents.size) {
                        val itemDetail = contents.get(i)

                        val doctor = DoctorChamberBook(
                            itemDetail.dbNo,
                            itemDetail.doctor,
                            itemDetail.nickName,
                            itemDetail.avatarPath.toString(),
                            itemDetail.qualification.toString(),
                            itemDetail.designation.toString(),
                            itemDetail.branchName,
                            itemDetail.deptName,
                            itemDetail.appointmentLockedDate.toString(),

                            itemDetail.satStart.toString(),
                            itemDetail.satEnd.toString(),
                            itemDetail.sunStart.toString(),
                            itemDetail.sunEnd.toString(),
                            itemDetail.monStart.toString(),
                            itemDetail.monEnd.toString(),
                            itemDetail.tueStart.toString(),
                            itemDetail.tueEnd.toString(),
                            itemDetail.wedStart.toString(),
                            itemDetail.wedEnd.toString(),
                            itemDetail.thuStart.toString(),
                            itemDetail.thuEnd.toString(),
                            itemDetail.friStart.toString(),
                            itemDetail.friEnd.toString(),

                            itemDetail.days2TakeAppoint.toString()
                        )
                        doctorChamberBookViewModel.addDoctorChamberBook(doctor)
                    }

                }
            }

            override fun onFailure(call: Call<DoctorChamberBookModel>, t: Throwable) {
                Toast.makeText(this@SplashActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }


    private fun investigationApiCall() {

        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.getInvestigations()

        call.enqueue(object : Callback<InvestigationModel> {
            override fun onResponse(
                call: Call<InvestigationModel>,
                response: Response<InvestigationModel>
            ) {
                if (response.isSuccessful) {

                    val contents: List<com.example.ibnsinadoctorappointment.data.models.investigation.Content> =
                        response.body()!!.content

                    Toast.makeText(
                        this@SplashActivity,
                        "response: " + contents.toString(),
                        Toast.LENGTH_LONG
                    ).show()

                    Log.d("tagRifat33333", "response is: " + contents)

                    for (i in 0 until contents.size) {
                        val itemDetail = contents.get(i)

                        val investigation = Investigation(
                            i + 1,
                            itemDetail.name,
                            itemDetail.price,
                            itemDetail.discount_percentage
                        )
                        investigationViewModel.addInvestigation(investigation)
                    }

                }
            }

            override fun onFailure(call: Call<InvestigationModel>, t: Throwable) {
                Toast.makeText(
                    this@SplashActivity, " " +
                            "Failure message${t.message}", Toast.LENGTH_SHORT
                ).show()
            }
        })

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
//                itemDetail.getString("phone"),
//                itemDetail.getString("designation")
//            )
//            doctorViewModel.addDoctor(doctor)
//        }
//
//        Log.d("tagRifat1234", "json is: " + jsonText)
//    }


    private fun deleteDoctorChamberBookData() {
        doctorChamberBookViewModel =
            ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorChamberBookViewModel.deleteAllDoctorChamberBooks()
    }

//    private fun loadDoctorChamberBookData() {
//        doctorChamberBookViewModel =
//            ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
//
//        val jsonText = resources.openRawResource(R.raw.doctor_chamber_response)
//            .bufferedReader().use { it.readText() }
//
//        val obj = JSONObject(jsonText)
//        val itemArray = obj.getJSONArray("content")
//        for (i in 0 until itemArray.length()) {
//            val itemDetail = itemArray.getJSONObject(i)
//            Log.d("tagRifat1234", "name is: " + itemDetail.getString("dbNo"))
//            val doctorBook = DoctorChamberBook(
//                itemDetail.getInt("dbNo"),
//                itemDetail.getString("nickName"),
//                itemDetail.getString("qualification"),
//                itemDetail.getString("branchName")
////                itemDetail.getString("designation")
//            )
//            doctorChamberBookViewModel.addDoctorChamberBook(doctorBook)
//        }
//
//        Log.d("tagRifat1234", "json is: " + jsonText)
//    }


    private fun deleteInvestigationData() {
        investigationViewModel = ViewModelProvider(this).get(InvestigationViewModel::class.java)
        investigationViewModel.deleteAllInvestigations()
    }

//    private fun loadInvestigationData() {
//        investigationViewModel = ViewModelProvider(this).get(InvestigationViewModel::class.java)
//        insertInvestigations()
//    }

//    private fun insertInvestigations() {
//
//        val jsonText = resources.openRawResource(R.raw.investigation_response)
//            .bufferedReader().use { it.readText() }
//
//        val obj = JSONObject(jsonText)
//        val itemArray = obj.getJSONArray("items")
//        for (i in 0 until itemArray.length()) {
//            val itemDetail = itemArray.getJSONObject(i)
//            Log.d("tagRifat1234", "name is: " + itemDetail.getString("name"))
//            val investigation = Investigation(
//                i + 1,
//                itemDetail.getString("name"),
//                itemDetail.getString("price"),
//                itemDetail.getString("discount_percentage")
//            )
//            investigationViewModel.addInvestigation(investigation)
//        }
//
//        Log.d("tagRifat1234", "json is: " + jsonText)
//
//    }


    private fun deleteLastUpdateData() {
        lastUpdateViewModel = ViewModelProvider(this).get(LastUpdateViewModel::class.java)
        lastUpdateViewModel.deleteAllLastUpdates()
    }

    private fun deleteBranchData() {
        branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)
        branchViewModel.deleteAllBranchs()
    }

    private fun deleteDepartmentData() {
        departmentViewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
        departmentViewModel.deleteAllDepartments()
    }

    private fun deleteHealthPackageData() {
        healthPackageViewModel = ViewModelProvider(this).get(HealthPackageViewModel::class.java)
        healthPackageViewModel.deleteAllHealthPackages()
    }


}