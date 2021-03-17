package com.example.ibnsinadoctorappointment.ui.fragments.form_patient

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Branch
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.data.models.chamber_availavle.ChamberAvailableModel
import com.example.ibnsinadoctorappointment.data.models.submit_appointment.SubmitAppointmentModel
import com.example.ibnsinadoctorappointment.retrofit.APIServices
import com.example.ibnsinadoctorappointment.retrofit.RetrofitClientInstance
import com.example.ibnsinadoctorappointment.ui.viewmodels.BranchViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorChamberBookViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_book_doctor.*
import kotlinx.android.synthetic.main.fragment_patient_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class PatientFormFragment : Fragment(R.layout.fragment_patient_form) {


    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var doctorChamberBookViewModel: DoctorChamberBookViewModel
    private lateinit var branchViewModel: BranchViewModel


    private var fromWhere: String = ""
    private var doctor_chamber_id: String = ""
    private var doctor_id: String = ""
    private var days2TakeAppoint: String = ""
    private var doctorName: String = ""
    private var doctorDeg: String = ""
    private var doctor_branch: String = ""
    private var doctor_branch_id: String = ""
    private var doctor_appointment_date: String = ""
    private var doctor_appointment_type: String = ""

    private var branch_id: String = ""
    private var pat_name: String = ""
    private var pat_gender: String = ""
    private var pat_phone: String = ""
    private var appointment_note: String = ""

    private lateinit var branchList: List<Branch>
    private var branchListString: MutableList<String> = ArrayList()
    private var branch_name: String = ""
    private var strDate_of_week: String = ""
    private var strDate: String = ""

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fromWhere = arguments?.getString("fromWhere").toString()
        doctor_chamber_id = arguments?.getString("doctor_chamber_id").toString()
        doctor_id = arguments?.getString("doctor_id").toString()
        doctor_branch_id = arguments?.getString("branch_id").toString()
        days2TakeAppoint = arguments?.getString("days2TakeAppoint").toString()
        doctorName = arguments?.getString("doctor_name").toString()
        doctorDeg = arguments?.getString("doctor_qualification").toString()
        doctor_branch = arguments?.getString("doctor_branch").toString()
        doctor_appointment_date = arguments?.getString("doctor_appointment_date").toString()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        setText()

        branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)
        loadSpinner1()
        datepicker()

//        checkChamberAvailabilityApiCall()
        setView()
    }

    private fun init(view: View) {
//        alertDialog = AlertDialog()

//        var id: Int = rg_gender.checkedRadioButtonId
//        if (id != -1) {
//            val radio: RadioButton = view.findViewById(id)
//            Toast.makeText(
//                context, "On button click : ${radio.text}",
//                Toast.LENGTH_SHORT
//            ).show()
//            pat_gender = radio.text.toString()
//        }
//
//        var id2: Int = rg_appointment_type.checkedRadioButtonId
//        if (id2 != -1) {
//            val radio: RadioButton = view.findViewById(id2)
//            Toast.makeText(
//                context, "On button click : ${radio.text}",
//                Toast.LENGTH_SHORT
//            ).show()
//            doctor_appointment_type = radio.text.toString()
//        }


        btn_submit.setOnClickListener(View.OnClickListener { submitInfo(view) })
    }


    private fun submitInfo(view: View) {

//        appointment_note (optional) -> input
//        branch_id     (chamber)
//        chamber_id   (chamber)
//        doctor_id     (chamber)
//        pat_contact =>Input
//        doctor_name
//        appointment_date => input (yyyy-mm-dd)
//        appointment_type  => Input (New, Report, Followup)
//        gender  => Input (Male, Female, Other)
//        pat_name => Input
//        average_time (doctor)
//        start_time  (chamber)
//        contact2  (branch)

        var id: Int = rg_gender.checkedRadioButtonId
        if (id != -1) {
            val radio: RadioButton = view.findViewById(id)
            pat_gender = radio.text.toString()
        }

        var id2: Int = rg_appointment_type.checkedRadioButtonId
        if (id2 != -1) {
            val radio: RadioButton = view.findViewById(id2)
            doctor_appointment_type = radio.text.toString()
        }


        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        doctorChamberBookViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)


        var branch: DoctorChamberBook =
            doctorChamberBookViewModel.getBranchByDoctorId(doctor_id.toInt())
        var doctorBranch = branch.branchName?.toString()
        Log.d("tagRifat33333444444", "doctorBranch is: " + doctorBranch)


        var average_time_obj: Doctor =
            doctorViewModel.getDoctorByDoctorId(doctor_id.toInt())
        var average_time = average_time_obj.averageTime
        Log.d("tagRifat33333444444", "average_time is: " + average_time)


        var contact2_obj: Branch =
            branchViewModel.getBranchObjByBranchName(doctorBranch)
        var contact2_branch = contact2_obj.contact2
        Log.d("tagRifat33333444444", "contact2_branch is: " + contact2_branch)



        val appointment_note: String = "" // optionnal
        val branch_id: String = doctor_branch_id
        val chamber_id: String = doctor_chamber_id
        val doctor_id: String = doctor_id
        val pat_name: String = et_pat_name.text.toString()
        val pat_contact: String = et_pat_mobile.text.toString()
        val gender: String = pat_gender
        val doctor_name: String = doctorName
        val appointment_date: String = doctor_appointment_date
        val appointment_type: String = doctor_appointment_type
        val start_time: String = "problem"


        if (TextUtils.isEmpty(branch_id)) {
            Toast.makeText(context, "Plz write your branch_id", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(chamber_id)) {
            Toast.makeText(context, "Plz write your chamber_id", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(doctor_id)) {
            Toast.makeText(context, "Plz write your doctor_id", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(pat_name)) {
            Toast.makeText(context, "Plz write your pat_name", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(pat_contact)) {
            Toast.makeText(context, "Plz write your pat_contact", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(gender)) {
            Toast.makeText(context, "Plz write your gender", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(doctor_name)) {
            Toast.makeText(context, "Plz write your doctor_name", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(appointment_date)) {
            Toast.makeText(context, "Plz write your appointment_date", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(appointment_type)) {
            Toast.makeText(context, "Plz write your appointment_type", Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(start_time)) {
            Toast.makeText(context, "Plz write your start_time", Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(contact2_branch)) {
            Toast.makeText(context, "Plz write your contact2_branch", Toast.LENGTH_SHORT).show()
        } else {
            alertDialog?.setTitle("Login Account")
            alertDialog?.setMessage("Plz wait, while we are checking the credentials...")
            alertDialog?.setCanceledOnTouchOutside(false)
            alertDialog?.show()

            submitPatInfoApiCall(
                appointment_note,
                branch_id,
                chamber_id,
                doctor_id,
                pat_contact,
                doctor_name,
                appointment_date,
                appointment_type,
                gender,
                pat_name,
                average_time,
                start_time,
                contact2_branch
            )
        }
    }


    private fun submitPatInfoApiCall(
        apptappointment_note: String,
        branch_id: String,
        chamber_id: String,
        doctor_id: String,
        pat_contact: String,
        doctor_name: String,
        appointment_date: String,
        appointment_type: String,
        gender: String,
        pat_name: String,
        average_time: String,
        start_time: String,
        contact2: String
    ) {

        Log.d("tagRifat33333444444", "apptappointment_note is: " + apptappointment_note)
        Log.d("tagRifat33333444444", "branch_id is: " + branch_id)
        Log.d("tagRifat33333444444", "chamber_id is: " + chamber_id)
        Log.d("tagRifat33333444444", "doctor_id is: " + doctor_id)
        Log.d("tagRifat33333444444", "pat_contact is: " + pat_contact)
        Log.d("tagRifat33333444444", "doctor_name is: " + doctor_name)
        Log.d("tagRifat33333444444", "appointment_date is: " + appointment_date)
        Log.d("tagRifat33333444444", "appointment_type is: " + appointment_type)
        Log.d("tagRifat33333444444", "gender is: " + gender)
        Log.d("tagRifat33333444444", "pat_name is: " + pat_name)
        Log.d("tagRifat33333444444", "average_time is: " + average_time)
        Log.d("tagRifat33333444444", "start_time is: " + start_time)
        Log.d("tagRifat33333444444", "apptappointment_note is: " + apptappointment_note)


        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.submitAppointment(apptappointment_note, branch_id, chamber_id, doctor_id,
                                                pat_contact, doctor_name, appointment_date, appointment_type, gender, pat_name,
                                                average_time, start_time, contact2)

        call.enqueue(object : Callback<SubmitAppointmentModel> {
            override fun onResponse(
                call: Call<SubmitAppointmentModel>,
                response: Response<SubmitAppointmentModel>
            ) {
                if (response.isSuccessful) {

//                    val last_update = response.body()!!.last_update
                    val status = response.body()!!.status
                    val message = response.body()!!.message
                    Log.d("tagRifat33333444", "last_update response is: " + response.body())

                    if (status == 200) {

                        val dialogBuilder = AlertDialog.Builder(context!!)

                        dialogBuilder.setMessage(message)
                            .setCancelable(false)
//                            .setPositiveButton("Proceed", DialogInterface.OnClickListener {
////                                    dialog, id -> finish()
//                            })
                            .setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })

                        val alert = dialogBuilder.create()
                        alert.setTitle("Appointment Message")
                        alert.show()

                    } else {

                        val dialogBuilder = AlertDialog.Builder(context!!)

                        dialogBuilder.setMessage(message)
                            .setCancelable(false)
//                            .setPositiveButton("Proceed", DialogInterface.OnClickListener {
////                                    dialog, id -> finish()
//                            })
                            .setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })

                        val alert = dialogBuilder.create()
                        alert.setTitle("Appointment Message")
                        alert.show()
                    }

                }
            }

            override fun onFailure(call: Call<SubmitAppointmentModel>, t: Throwable) {
                Log.d("tagRifat33333444", "last_update response is: " + t.message)
            }

        })

    }


    private fun setView() {

        if (fromWhere.equals("DoctorChamberBook")) {
            checkChamberAvailabilityApiCall()

            spinRelative1_form.visibility = View.GONE
            tv_branch_form.visibility = View.VISIBLE
            tv_branch_form.text = "Branch: " + doctor_branch
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
            datePicker!!.getDatePicker().maxDate =
                System.currentTimeMillis() + (int_days2TakeAppoint * 24 * 60 * 60 * 1000) //86,400,000
        } else {

        }



        tv_date_search_form.setOnClickListener({ v ->
            datePicker?.show()
        })

    }


    private fun checkChamberAvailabilityApiCall() {

        val request = RetrofitClientInstance.buildService(APIServices::class.java)
        val call = request.checkAvailableChamber(doctor_chamber_id, strDate)

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

                    if (allow_apt) {
                        linear_form.visibility = View.VISIBLE

                    } else {

                        linear_form.visibility = View.GONE

                        val dialogBuilder = AlertDialog.Builder(context!!)

                        dialogBuilder.setMessage(message)
                            .setCancelable(false)
//                            .setPositiveButton("Proceed", DialogInterface.OnClickListener {
////                                    dialog, id -> finish()
//                            })
                            .setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
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