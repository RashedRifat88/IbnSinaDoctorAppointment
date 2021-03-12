package com.example.ibnsinadoctorappointment.ui.fragments.form_patient

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Branch
import com.example.ibnsinadoctorappointment.data.models.chamber_availavle.ChamberAvailableModel
import com.example.ibnsinadoctorappointment.retrofit.APIServices
import com.example.ibnsinadoctorappointment.retrofit.RetrofitClientInstance
import com.example.ibnsinadoctorappointment.ui.viewmodels.BranchViewModel
import kotlinx.android.synthetic.main.fragment_book_doctor.*
import kotlinx.android.synthetic.main.fragment_patient_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class PatientFormFragment : Fragment(R.layout.fragment_patient_form) {


    private var fromWhere: String = ""
    private var doctork_chamber_id: String = ""
    private var days2TakeAppoint: String = ""
    private var doctorName: String = ""
    private var doctorDeg: String = ""
    private var doctor_branch: String = ""
    private var doctor_appointment_date: String = ""

    private lateinit var branchViewModel: BranchViewModel
    private lateinit var branchList: List<Branch>
    private var branchListString: MutableList<String> = ArrayList()
    private var branch_name: String = ""
    private var strDate_of_week: String = ""
    private var strDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fromWhere = arguments?.getString("fromWhere").toString()
        doctork_chamber_id = arguments?.getString("doctork_chamber_id").toString()
        days2TakeAppoint = arguments?.getString("days2TakeAppoint").toString()
        doctorName = arguments?.getString("doctork_name").toString()
        doctorDeg = arguments?.getString("doctork_qualification").toString()
        doctor_branch = arguments?.getString("doctor_branch").toString()
        doctor_appointment_date = arguments?.getString("doctor_appointment_date").toString()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setText()

        branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)
        loadSpinner1()
        datepicker()

//        checkChamberAvailabilityApiCall()
        setView()
    }

    private fun setView() {

        if (fromWhere.equals("DoctorChamberBook")){
            checkChamberAvailabilityApiCall()

            spinRelative1_form.visibility = View.GONE
            tv_branch_form.visibility = View.VISIBLE
            tv_branch_form.text = "Branch: "+ doctor_branch
        }
    }

    @SuppressLint("HardwareIds")
    private fun setText() {
        tv_doctor_name.text = doctorName
        tv_doctor_deg.text = doctorDeg
        tv_date_search_form.text = doctor_appointment_date


//        val tm = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//
//        val telNumber = if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.READ_SMS
//            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.READ_PHONE_NUMBERS
//            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.READ_PHONE_STATE
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
////            Log.d("telNumberN", "telNumberN 1: "+telNumber)
//        } else {
//            return
//        }
//
//        var telNumberN: String =  tm.line1Number
//        Log.d("tagRifat33333444", "telNumberN: "+telNumberN)
//
//        et_pat_mobile.setText(telNumberN)
//
////        Toast.makeText(this, "telNumberN: "+telNumberN, Toast.LENGTH_SHORT).show()



//        et_pat_mobile.text = telephonyManager.line1Number


    }



    fun loadSpinner1() {
        branchViewModel.getAllBranchs.observe(viewLifecycleOwner, Observer {
            branchList = it

            Log.d("tagrifat33333", "branch list: $it")
            Log.d("tagrifat33333", "branchList.size:" + branchList.size)

            branchListString.add(doctor_branch)
            for (i in 0 until branchList.size) {
                val itemDetail = branchList.get(i)
                branchListString.add(itemDetail.branchName)
            }
            Log.d("tagrifat33333", "branchListString: $branchListString")

            // access the spinner
            if (spinner_branch_form != null) {
                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        R.layout.spinner_item, branchListString
                    )
                }
                spinner_branch_form.adapter = adapter

//                spinner_branch_form.setSelection(0, false)

                spinner_branch_form.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?, position: Int, id: Long
                    ) {
                        Toast.makeText(
                            context,
                            branchListString.toString(), Toast.LENGTH_SHORT
                        ).show()

                        (view as TextView?)?.setTextColor(Color.parseColor("#ffffff"))

                        branch_name = branchListString.get(position)

                        if (!(position == 0)) {
//                            searchDoctorByBranch(branch_name)
                        }

                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }

                }
            }
        })
    }



    private fun datepicker() {
        val mcurrentTime = Calendar.getInstance()
        val year = mcurrentTime.get(Calendar.YEAR)
        val month = mcurrentTime.get(Calendar.MONTH)
        val day = mcurrentTime.get(Calendar.DAY_OF_MONTH)

        Log.d(
            "tag5555",
            "days2TakeAppoint : $days2TakeAppoint"
        )




        val datePicker = context?.let {
            DatePickerDialog(it, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {


                    val year: Int = year
                    val month: Int = month
                    val day: Int = dayOfMonth

                    val calendar = Calendar.getInstance()
//                    calendar[year, month] = day
                    calendar.set(year, month, day)

                    val format = SimpleDateFormat("yyyy-MM-dd")
                    strDate = format.format(calendar.time)

                    tv_date_search_form.setText(strDate)

//                    searchDoctorByDate(strDate)


                    val format_day = SimpleDateFormat("EEE")
                    strDate_of_week = format_day.format(calendar.time)
                    Log.d(
                        "tag5555",
                        "strDate_of_week : $strDate_of_week"
                    )

                    checkChamberAvailabilityApiCall()

                    when (strDate_of_week) {
//                        "Sat" -> searchDoctorBySatDay("00:00:00")
//                        "Sun" -> searchDoctorBySunDay("00:00:00")
//                        "Mon" -> searchDoctorByMonDay("00:00:00")
//                        "Tue" -> searchDoctorByTueDay("00:00:00")
//                        "Wed" -> searchDoctorByWedDay("00:00:00")
//                        "Thu" -> searchDoctorByThuDay("00:00:00")
//                        "Fri" -> searchDoctorByFriDay("00:00:00")

//                        checkChamberAvailabilityApiCall()

                        else -> {

                        }
                    }

                }
            }, year, month, day)
        }


        datePicker!!.getDatePicker().setMinDate(System.currentTimeMillis() - 1000)

        val int_days2TakeAppoint = days2TakeAppoint.toIntOrNull()
        if (int_days2TakeAppoint != null) {
            datePicker!!.getDatePicker().maxDate = System.currentTimeMillis() + (int_days2TakeAppoint * 24 * 60 * 60 * 1000) //86,400,000
        } else {

        }



        tv_date_search_form.setOnClickListener({ v ->
            datePicker?.show()
        })

    }



    private fun checkChamberAvailabilityApiCall() {

        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.checkAvailableChamber(doctork_chamber_id, strDate)

        call.enqueue(object : Callback<ChamberAvailableModel> {
            override fun onResponse(
                call: Call<ChamberAvailableModel>,
                response: Response<ChamberAvailableModel>
            ) {
                if (response.isSuccessful) {

//                    val last_update = response.body()!!.last_update
                    val allow_apt = response.body()!!.allow_apt
                    val message = response.body()!!.message
                    Log.d("tagRifat33333444", "last_update response is: " + response.body())

                    if (allow_apt){
                        linear_form.visibility = View.VISIBLE

                    }else{

                        linear_form.visibility = View.GONE

                        val dialogBuilder = AlertDialog.Builder(context!!)

                        dialogBuilder.setMessage(message)
                            .setCancelable(false)
//                            .setPositiveButton("Proceed", DialogInterface.OnClickListener {
////                                    dialog, id -> finish()
//                            })
                            .setNegativeButton("Ok", DialogInterface.OnClickListener {
                                    dialog, id -> dialog.cancel()
                            })

                        val alert = dialogBuilder.create()
                        alert.setTitle("Appointment Message")
                        alert.show()
                    }

//                    Toast.makeText(
//                        this@SplashActivity,
//                        "response last_update : " + last_update.toString(),
//                        Toast.LENGTH_LONG
//                    ).show()

                }
            }

            override fun onFailure(call: Call<ChamberAvailableModel>, t: Throwable) {
                Log.d("tagRifat33333444", "last_update response is: " + t.message)
            }

        })
    }






}