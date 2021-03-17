package com.example.ibnsinadoctorappointment.ui.fragments.book_doctor

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters.DoctorChamberBookListAdapter
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Branch
import com.example.ibnsinadoctorappointment.data.models.Department
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.ui.viewmodels.BranchViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DepartmentViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorChamberBookViewModel
import kotlinx.android.synthetic.main.fragment_book_doctor.*
import java.text.SimpleDateFormat
import java.util.*


class BookDoctorFragment : Fragment(R.layout.fragment_book_doctor) {

    private lateinit var doctorList: List<DoctorChamberBook>
    private lateinit var doctorViewModel: DoctorChamberBookViewModel
    private lateinit var adapter: DoctorChamberBookListAdapter

    private lateinit var branchViewModel: BranchViewModel
    private lateinit var departmentViewModel: DepartmentViewModel

    private lateinit var branchList: List<Branch>
    private lateinit var deptList: List<Department>
    private var branchListString: MutableList<String> = ArrayList()
    private var deptListString: MutableList<String> = ArrayList()

    private var strDate_of_week: String = ""
    private var strDate: String = ""
    private var branch_name: String = ""
    private var dept_name: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)

        adapter = DoctorChamberBookListAdapter()
        rv_doctor_book.adapter = adapter
        rv_doctor_book.layoutManager = LinearLayoutManager(context)

        //Instantiate and create viewmodel observers
        viewModels()


        currentDateSet()
        datepicker()

        branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)
        departmentViewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)

        loadSpinner1()
        loadSpinner2()


        searchByName()

    }

    private fun currentDateSet() {
//        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        strDate = currentDate
        tv_date_search.setText(strDate)


        val format_day = SimpleDateFormat("EEE")
        strDate_of_week = format_day.format(Date())
        Log.d(
            "tag5555",
            "strDate_of_week : $strDate_of_week"
        )

        when (strDate_of_week) {
            "Sat" -> searchDoctorBySatDay("00:00:00")
            "Sun" -> searchDoctorBySunDay("00:00:00")
            "Mon" -> searchDoctorByMonDay("00:00:00")
            "Tue" -> searchDoctorByTueDay("00:00:00")
            "Wed" -> searchDoctorByWedDay("00:00:00")
            "Thu" -> searchDoctorByThuDay("00:00:00")
            "Fri" -> searchDoctorByFriDay("00:00:00")

            else -> {

            }
        }

    }


    fun loadSpinner1() {
        branchViewModel.getAllBranchs.observe(viewLifecycleOwner, Observer {
            branchList = it

            Log.d("tagrifat33333", "branch list: $it")
            Log.d("tagrifat33333", "branchList.size:" + branchList.size)

            branchListString.add("Select Branch")
            for (i in 0 until branchList.size) {
                val itemDetail = branchList.get(i)
                branchListString.add(itemDetail.branchName)
            }
            Log.d("tagrifat33333", "branchListString: $branchListString")

            // access the spinner
            if (spinner_branch_book != null) {
                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        R.layout.spinner_item, branchListString
                    )
                }
                spinner_branch_book.adapter = adapter

//                spinner_branch_book.setSelection(0, false)

                spinner_branch_book.onItemSelectedListener = object :
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

                        var dateColumnValue: String = ""
                        when (strDate_of_week) {
                            "Sat" -> dateColumnValue = "column_satStart"
                            "Sun" -> dateColumnValue = "column_sunStart"
                            "Mon" -> dateColumnValue = "column_monStart"
                            "Tue" -> dateColumnValue = "column_tueStart"
                            "Wed" -> dateColumnValue = "column_wedStart"
                            "Thu" -> dateColumnValue = "column_thuStart"
                            "Fri" -> dateColumnValue = "column_friStart"

                            else -> {

                            }
                        }


                        if (!(position == 0)) {
//                            searchDoctorByBranch(branch_name)


                            Log.d("tagrifat33333", "position: $position")

                            if (!dept_name.equals("Select Department") && !et_search.text.isEmpty()) {

                                Log.d(
                                    "tagrifat33333",
                                    "searchDoctorByNameAndDeptAndBranchAndDate is called"
                                )
                                searchDoctorByNameAndDeptAndBranchAndDate(
                                    et_search.text.toString(),
                                    dept_name,
                                    branch_name,
                                    dateColumnValue
                                )
                            } else if (!dept_name.equals("Select Department") && et_search.text.isEmpty()) {

                                Log.d(
                                    "tagrifat33333",
                                    "searchDoctorByDeptAndBranchAndDate is called"
                                )
                                searchDoctorByDeptAndBranchAndDate(
                                    dept_name,
                                    branch_name,
                                    dateColumnValue
                                )

                            } else if (dept_name.equals("Select Department") && et_search.text.isEmpty()) {

                                Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
                                Log.d(
                                    "tagrifat33333",
                                    "branch_name: $branch_name, dateColumnValue: $dateColumnValue"
                                )
                                searchDoctorByBranchAndDate(branch_name, dateColumnValue)
                            } else if (dept_name.equals("Select Department") && !et_search.text.isEmpty()) {

                                Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
                                searchDoctorByNameAndBranchAndDate(et_search.text.toString(), branch_name, dateColumnValue)
                            }

                        } else {
//                            searchDoctorByDate(dateColumnValue)

                            if (!dept_name.equals("Select Department") && !et_search.text.isEmpty()) {
                                searchDoctorByNameAndDeptAndDate(
                                    et_search.text.toString(),
                                    dept_name,
                                    dateColumnValue
                                )
                            } else if (!dept_name.equals("Select Department") && et_search.text.isEmpty()) {
                                searchDoctorByDeptAndDate(
                                    dept_name,
                                    dateColumnValue
                                )
                            } else if (dept_name.equals("Select Dept") && !et_search.text.isEmpty()) {
                                searchDoctorByNameAndDate(
                                    et_search.text.toString(),
                                    dateColumnValue
                                )
                            } else {
                                searchDoctorByDate(dateColumnValue)
                            }

                        }

                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }

                }
            }
        })
    }


    fun loadSpinner2() {
        departmentViewModel.getAllDepartments.observe(viewLifecycleOwner, Observer {
            deptList = it

            Log.d("tagrifat33333", "dept list: $it")
            Log.d("tagrifat33333", "deptList.size:" + deptList.size)

            deptListString.add("Select Department")
            for (i in 0 until deptList.size) {
                val itemDetail = deptList.get(i)
                deptListString.add(itemDetail.name)

            }
            Log.d("tagrifat33333", "deptListString: $deptListString")

            // access the spinner
            if (spinner_dept_book != null) {
                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        R.layout.spinner_item, deptListString
                    )
                }
                spinner_dept_book.adapter = adapter

                spinner_dept_book.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?, position: Int, id: Long
                    ) {
                        Toast.makeText(
                            context,
                            deptListString.get(position), Toast.LENGTH_SHORT
                        ).show()

                        (view as TextView?)?.setTextColor(Color.parseColor("#ffffff"))

                        dept_name = deptListString.get(position)

                        var dateColumnValue: String = ""
                        when (strDate_of_week) {
                            "Sat" -> dateColumnValue = "column_satStart"
                            "Sun" -> dateColumnValue = "column_sunStart"
                            "Mon" -> dateColumnValue = "column_monStart"
                            "Tue" -> dateColumnValue = "column_tueStart"
                            "Wed" -> dateColumnValue = "column_wedStart"
                            "Thu" -> dateColumnValue = "column_thuStart"
                            "Fri" -> dateColumnValue = "column_friStart"

                            else -> {

                            }
                        }

                        if (!(position == 0)) {
//                            searchDoctorByDept(dept_name)

                            Log.d("tagrifat33333", "position: $position")

                            if (!branch_name.equals("Select Branch") && !et_search.text.isEmpty()) {

                                Log.d(
                                    "tagrifat33333",
                                    "searchDoctorByNameAndDeptAndBranchAndDate is called"
                                )
                                searchDoctorByNameAndDeptAndBranchAndDate(
                                    et_search.text.toString(),
                                    dept_name,
                                    branch_name,
                                    dateColumnValue
                                )
                            } else if (!branch_name.equals("Select Branch") && et_search.text.isEmpty()) {

                                Log.d(
                                    "tagrifat33333",
                                    "searchDoctorByDeptAndBranchAndDate is called"
                                )
                                searchDoctorByDeptAndBranchAndDate(
                                    dept_name,
                                    branch_name,
                                    dateColumnValue
                                )

                            } else if (branch_name.equals("Select Branch") && et_search.text.isEmpty()) {

                                Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
                                Log.d(
                                    "tagrifat33333",
                                    "branch_name: $branch_name, dateColumnValue: $dateColumnValue"
                                )
//                                searchDoctorByBranchAndDate(branch_name, dateColumnValue)
                                searchDoctorByDeptAndDate(dept_name, dateColumnValue)
                            } else if (branch_name.equals("Select Branch") && !et_search.text.isEmpty()) {

                                Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
                                Log.d(
                                    "tagrifat33333",
                                    "branch_name: $branch_name, dateColumnValue: $dateColumnValue"
                                )
//                                searchDoctorByBranchAndDate(branch_name, dateColumnValue)
                                searchDoctorByNameAndDeptAndDate(
                                    et_search.text.toString(),
                                    dept_name,
                                    dateColumnValue
                                )
                            }

                        } else {
//                            searchDoctorByDate(dateColumnValue)

                            if (!branch_name.equals("Select Branch") && !et_search.text.isEmpty()) {
                                searchDoctorByNameAndBranchAndDate(
                                    et_search.text.toString(),
                                    branch_name,
                                    dateColumnValue
                                )
                            } else if (!branch_name.equals("Select Branch") && et_search.text.isEmpty()) {
                                searchDoctorByBranchAndDate(
                                    branch_name,
                                    dateColumnValue
                                )
                            } else if (branch_name.equals("Select Branch") && !et_search.text.isEmpty()) {
                                searchDoctorByNameAndDate(
                                    et_search.text.toString(),
                                    dateColumnValue
                                )
                            } else {
                                searchDoctorByDate(dateColumnValue)
                            }

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

                    tv_date_search.setText(strDate)

//                    searchDoctorByDate(strDate)


                    val format_day = SimpleDateFormat("EEE")
                    strDate_of_week = format_day.format(calendar.time)
                    Log.d(
                        "tag5555",
                        "strDate_of_week : $strDate_of_week"
                    )

//                    when (strDate_of_week) {
//                        "Sat" -> searchDoctorBySatDay("00:00:00")
//                        "Sun" -> searchDoctorBySunDay("00:00:00")
//                        "Mon" -> searchDoctorByMonDay("00:00:00")
//                        "Tue" -> searchDoctorByTueDay("00:00:00")
//                        "Wed" -> searchDoctorByWedDay("00:00:00")
//                        "Thu" -> searchDoctorByThuDay("00:00:00")
//                        "Fri" -> searchDoctorByFriDay("00:00:00")
//
//                        else -> {
//
//                        }
//                    }


                    var dateColumnValue: String = ""
                    when (strDate_of_week) {
                        "Sat" -> dateColumnValue = "column_satStart"
                        "Sun" -> dateColumnValue = "column_sunStart"
                        "Mon" -> dateColumnValue = "column_monStart"
                        "Tue" -> dateColumnValue = "column_tueStart"
                        "Wed" -> dateColumnValue = "column_wedStart"
                        "Thu" -> dateColumnValue = "column_thuStart"
                        "Fri" -> dateColumnValue = "column_friStart"

                        else -> {

                        }
                    }


                    Log.d(
                        "tag55551",
                        "dept_name : $dept_name, branch_name : $branch_name , strDate : $strDate , et_search.text : $et_search.text "
                    )



                    if (!branch_name.equals("Select Branch") && !dept_name.equals("Select Department") && !et_search.text.isEmpty()
                    ) {

                        searchDoctorByNameAndDeptAndBranchAndDate(
                            et_search.text.toString(),
                            dept_name,
                            branch_name,
                            dateColumnValue
                        )

                    } else if (!branch_name.equals("Select Branch") && !dept_name.equals("Select Department")) {

                        searchDoctorByDeptAndBranchAndDate(dept_name, branch_name, dateColumnValue)

                    } else if (!branch_name.equals("Select Branch") && dept_name.equals("Select Department")) {
                        searchDoctorByBranchAndDate(
                            branch_name,
                            dateColumnValue
                        )
                    } else if (branch_name.equals("Select Branch") && !dept_name.equals("Select Department")) {
                        searchDoctorByDeptAndDate(
                            dept_name,
                            dateColumnValue
                        )
                    } else {
                        searchDoctorByDate(dateColumnValue)
                    }


                }
            }, year, month, day)
        };


        tv_date_search.setOnClickListener({ v ->
            datePicker?.show()
        })

    }

    private fun searchByName() {
        tv_refresh_search.setOnClickListener {
            viewModels()
            currentDateSet()
            loadSpinner1()
            loadSpinner2()
            et_search.setText("")
        }


        tv_search.setOnClickListener {

            Log.d(
                "tag5555",
                "dept_name : $dept_name, branch_name : $branch_name , strDate : $strDate "
            )



            if (dept_name.equals("Select Department") && branch_name.equals("Select Branch") && strDate.isEmpty()) {
                Toast.makeText(
                    context,
                    "You should select at least one item from department, branch or date for search",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!dept_name.equals("Select Department") && !branch_name.equals("Select Branch") && !strDate.isEmpty()) {
//                searchDoctorByDeptAndBranchAndDate(dept_name, branch_name, strDate)
            } else if (!dept_name.equals("Select Department") && !branch_name.equals("Select Branch") && strDate.isEmpty()) {
                searchDoctorByDeptAndBranch(dept_name, branch_name)
            } else if (!dept_name.equals("Select Department") && branch_name.equals("Select Branch") && !strDate.isEmpty()) {
//                searchDoctorByDeptAndDate(dept_name, strDate)
            } else if (dept_name.equals("Select Department") && !branch_name.equals("Select Branch") && !strDate.isEmpty()) {
//                searchDoctorByBranchAndDate(branch_name, strDate)
            } else if (dept_name.equals("Select Department") && branch_name.equals("Select Branch") && !strDate.isEmpty()) {
                searchDoctorByDate(strDate)
            } else if (!dept_name.equals("Select Department") && branch_name.equals("Select Branch") && strDate.isEmpty()) {
                searchDoctorByDept(dept_name)
            } else if (dept_name.equals("Select Department") && !branch_name.equals("Select Branch") && strDate.isEmpty()) {
                searchDoctorByBranch(branch_name)
            }


        }


//       tv_search_by_branch.setOnClickListener {
//            searchDoctorByBranch(et_search.text.toString())
//
//           view?.let { it1 -> context?.hideKeyboard(it1) }
//        }
//
//       tv_search_by_dept.setOnClickListener {
//            searchDoctorByDept(et_search.text.toString())
//
//           view?.let { it1 -> context?.hideKeyboard(it1) }
//        }


        var dateColumnValue: String = ""
        when (strDate_of_week) {
            "Sat" -> dateColumnValue = "column_satStart"
            "Sun" -> dateColumnValue = "column_sunStart"
            "Mon" -> dateColumnValue = "column_monStart"
            "Tue" -> dateColumnValue = "column_tueStart"
            "Wed" -> dateColumnValue = "column_wedStart"
            "Thu" -> dateColumnValue = "column_thuStart"
            "Fri" -> dateColumnValue = "column_friStart"

            else -> {

            }
        }

        et_search.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                searchDoctorByName(et_search.text.toString())

//                searchDoctorByNameAndDate(et_search.text.toString(), dateColumnValue)


                if (!branch_name.equals("Select Branch") && !dept_name.equals("Select Department")) {

                    Log.d(
                        "tagrifat33333",
                        "searchDoctorByNameAndDeptAndBranchAndDate is called"
                    )
                    searchDoctorByNameAndDeptAndBranchAndDate(
                        et_search.text.toString(),
                        dept_name,
                        branch_name,
                        dateColumnValue
                    )
                } else if (!branch_name.equals("Select Branch") && dept_name.equals("Select Department")) {
                    searchDoctorByNameAndBranchAndDate(
                        et_search.text.toString(),
                        branch_name,
                        dateColumnValue
                    )
                } else if (branch_name.equals("Select Branch") && !dept_name.equals("Select Department")) {
                    searchDoctorByNameAndDeptAndDate(
                        et_search.text.toString(),
                        dept_name,
                        dateColumnValue
                    )
                } else {
                    searchDoctorByNameAndDate(et_search.text.toString(), dateColumnValue)
                }


//                if (!dept_name.equals("Select Department") && et_search.text.isEmpty()) {
//
//                    Log.d(
//                        "tagrifat33333",
//                        "searchDoctorByDeptAndBranchAndDate is called"
//                    )
//                    searchDoctorByDeptAndBranchAndDate(
//                        dept_name,
//                        branch_name,
//                        dateColumnValue
//                    )
//
//                }
//                if (dept_name.equals("Select Department") && et_search.text.isEmpty()) {
//
//                    Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
//                    Log.d(
//                        "tagrifat33333",
//                        "branch_name: $branch_name, dateColumnValue: $dateColumnValue"
//                    )
//                    searchDoctorByBranchAndDate(branch_name, dateColumnValue)
//                }
//                if (!dept_name.equals("Select Department") && !et_search.text.isEmpty()) {
//
//                    Log.d(
//                        "tagrifat33333",
//                        "searchDoctorByNameAndDeptAndBranchAndDate is called"
//                    )
//                    searchDoctorByNameAndDeptAndBranchAndDate(
//                        et_search.text.toString(),
//                        dept_name,
//                        branch_name,
//                        dateColumnValue
//                    )
//                }
//                if (!dept_name.equals("Select Department") && et_search.text.isEmpty()) {
//
//                    Log.d(
//                        "tagrifat33333",
//                        "searchDoctorByDeptAndBranchAndDate is called"
//                    )
//                    searchDoctorByDeptAndBranchAndDate(
//                        dept_name,
//                        branch_name,
//                        dateColumnValue
//                    )
//
//                }
//                if (dept_name.equals("Select Department") && et_search.text.isEmpty()) {
//
//                    Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
//                    Log.d(
//                        "tagrifat33333",
//                        "branch_name: $branch_name, dateColumnValue: $dateColumnValue"
//                    )
//                    searchDoctorByBranchAndDate(branch_name, dateColumnValue)
//                }
//                if (!et_search.text.isEmpty()) {
//
//                    Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
//                    Log.d(
//                        "tagrifat33333",
//                        "branch_name: $branch_name, dateColumnValue: $dateColumnValue"
//                    )
//                    searchDoctorByNameAndDate(et_search.text.toString(), dateColumnValue)
//                }


            }

            override fun afterTextChanged(p0: Editable?) {


            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }


    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun viewModels() {

        var dateColumnValue: String = ""
        when (strDate_of_week) {
            "Sat" -> dateColumnValue = "column_satStart"
            "Sun" -> dateColumnValue = "column_sunStart"
            "Mon" -> dateColumnValue = "column_monStart"
            "Tue" -> dateColumnValue = "column_tueStart"
            "Wed" -> dateColumnValue = "column_wedStart"
            "Thu" -> dateColumnValue = "column_thuStart"
            "Fri" -> dateColumnValue = "column_friStart"

            else -> {

            }
        }

//        searchDoctorByNameAndDate(et_search.text.toString(), dateColumnValue)

        searchDoctorByDate(dateColumnValue)


//        if (!dept_name.equals("Select Branch") && !dept_name.equals("Select Department") && !et_search.text.isEmpty()) {
//
//            Log.d(
//                "tagrifat33333",
//                "searchDoctorByNameAndDeptAndBranchAndDate is called"
//            )
//            searchDoctorByNameAndDeptAndBranchAndDate(
//                et_search.text.toString(),
//                dept_name,
//                branch_name,
//                dateColumnValue
//            )
//        } else if (!dept_name.equals("Select Department") && et_search.text.isEmpty()) {
//
//            Log.d(
//                "tagrifat33333",
//                "searchDoctorByDeptAndBranchAndDate is called"
//            )
//            searchDoctorByDeptAndBranchAndDate(
//                dept_name,
//                branch_name,
//                dateColumnValue
//            )
//
//        } else if (dept_name.equals("Select Department") && et_search.text.isEmpty()) {
//
//            Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
//            Log.d(
//                "tagrifat33333",
//                "branch_name: $branch_name, dateColumnValue: $dateColumnValue"
//            )
//            searchDoctorByBranchAndDate(branch_name, dateColumnValue)
//        } else if (!dept_name.equals("Select Department") && !et_search.text.isEmpty()) {
//
//            Log.d(
//                "tagrifat33333",
//                "searchDoctorByNameAndDeptAndBranchAndDate is called"
//            )
//            searchDoctorByNameAndDeptAndBranchAndDate(
//                et_search.text.toString(),
//                dept_name,
//                branch_name,
//                dateColumnValue
//            )
//        } else if (!dept_name.equals("Select Department") && et_search.text.isEmpty()) {
//
//            Log.d(
//                "tagrifat33333",
//                "searchDoctorByDeptAndBranchAndDate is called"
//            )
//            searchDoctorByDeptAndBranchAndDate(
//                dept_name,
//                branch_name,
//                dateColumnValue
//            )
//
//        } else if (dept_name.equals("Select Department") && et_search.text.isEmpty()) {
//
//            Log.d("tagrifat33333", "searchDoctorByBranchAndDate is called")
//            Log.d(
//                "tagrifat33333",
//                "branch_name: $branch_name, dateColumnValue: $dateColumnValue"
//            )
//            searchDoctorByBranchAndDate(branch_name, dateColumnValue)
//        }


//        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
//
//        doctorViewModel.getAllDoctorChamberBooks.observe(viewLifecycleOwner, Observer {
//            adapter.setData(it, strDate)
//            doctorList = it
//
//            Log.d("tag5555", "it viewModels: $it")
//
//iv_loader.visibility = View.VISIBLE
        //            if (it.isEmpty()) {
//                rv_doctor_book.visibility = View.GONE
//                tv_emptyView.visibility = View.VISIBLE
//////                iv_loader.visibility = View.VISIBLE
//            } else {
//                rv_doctor_book.visibility = View.VISIBLE
//                tv_emptyView.visibility = View.GONE
//                iv_loader.visibility = View.GONE
//            }
//        })


    }


    private fun searchDoctorByBranch(title: String) {
        var searchText = title
        searchText = "$title"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByBranch(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

            Log.d("tag5555", "it: $it")

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
            //            recyclerView.apply {
//                adapter = RecyclerAdapterMain(it, this@MainActivity)
        })

    }


    private fun searchDoctorByDept(title: String) {
        var searchText = title
        searchText = "$title"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByDept(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

            Log.d("tag5555", "it: $it")

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }

        })

    }

    private fun searchDoctorByNameAndDate(name: String, date: String) {
        var searchText1 = "%$name%"
        var searchText2 = "$date"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchDoctorByNameAndDate(searchText1, searchText2)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag5555", "it name with date: $it")

//                iv_loader.visibility = View.VISIBLE

                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


    private fun searchDoctorByDate(title: String) {
        var searchText = "$title"

        Log.d(
            "tag5555date",
            "Date title : $title"
        )


        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByDate(title).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

            Log.d(
                "tag5555date",
                "it in searchDoctorByDate : $it"
            )


//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorBySatDay(title: String) {
        var searchText = "$title"

        Log.d(
            "tag5555",
            "title : $title"
        )

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchBySatDay(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

            Log.d(
                "tag5555",
                "it : $it"
            )

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorBySunDay(title: String) {
        var searchText = "$title"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchBySunDay(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorByMonDay(title: String) {
        var searchText = "$title"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByMonDay(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorByTueDay(title: String) {
        var searchText = "$title"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByTueDay(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorByWedDay(title: String) {
        var searchText = "$title"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByWebDay(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorByThuDay(title: String) {
        var searchText = "$title"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByThuDay(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorByFriDay(title: String) {
        var searchText = "$title"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByFriDay(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it, strDate)
            doctorList = it

//            iv_loader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
////                iv_loader.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
                iv_loader.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorByName(name: String) {
        var searchText1 = name
        searchText1 = "$name"


        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByName(searchText1)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag5555", "it: $it")
//                iv_loader.visibility = View.VISIBLE
                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


    private fun searchDoctorByDeptAndBranch(dept: String, branch: String) {
        var searchText1 = dept
        searchText1 = "$dept"

        var searchText2 = branch
        searchText2 = "$branch"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByName2(searchText2, searchText1)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag5555", "it: $it")
//                iv_loader.visibility = View.VISIBLE
                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


    private fun searchDoctorByBranchAndDate(branch: String, date: String) {
        var searchText1 = date
        searchText1 = "$date"

        var searchText2 = branch
        searchText2 = "$branch"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByName4(searchText2, searchText1)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag5555", "it: $it")
//                iv_loader.visibility = View.VISIBLE
                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


    private fun searchDoctorByDeptAndDate(dept: String, date: String) {
        var searchText1 = date
        searchText1 = "$date"

        var searchText2 = dept
        searchText2 = "$dept"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByName3(searchText1, searchText2)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag5555", "it: $it")
//                iv_loader.visibility = View.VISIBLE
                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


    private fun searchDoctorByNameAndDeptAndBranchAndDate(
        name: String,
        dept: String,
        branch: String,
        date: String
    ) {

        var searchTextName = "%$name%"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByNameAndDeptAndBranchAndDate(searchTextName, date, branch, dept)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag55551", "it5: $it")
//                iv_loader.visibility = View.VISIBLE
                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


    private fun searchDoctorByNameAndBranchAndDate(
        name: String,
        branch: String,
        date: String
    ) {

        var searchTextName = "%$name%"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchDoctorByNameAndBranchAndDate(searchTextName, date, branch)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag5555", "it: $it")
//                iv_loader.visibility = View.VISIBLE
                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


    private fun searchDoctorByNameAndDeptAndDate(
        name: String,
        dept: String,
        date: String
    ) {

        var searchTextName = "%$name%"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByNameAndDeptAndDate(searchTextName, date, dept)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag5555", "it: $it")
//                iv_loader.visibility = View.VISIBLE
                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


    private fun searchDoctorByDeptAndBranchAndDate(dept: String, branch: String, date: String) {

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByName1(date, branch, dept)
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it, strDate)
                doctorList = it

                Log.d("tag5555", "it: $it")
//                iv_loader.visibility = View.VISIBLE
                if (it.isEmpty()) {
                    rv_doctor_book.visibility = View.GONE
                    tv_emptyView.visibility = View.VISIBLE
////                    iv_loader.visibility = View.VISIBLE
                } else {
                    rv_doctor_book.visibility = View.VISIBLE
                    tv_emptyView.visibility = View.GONE
                    iv_loader.visibility = View.GONE
                }
            })
    }


//
//    private fun searchDoctorByName(name: String, branch_name: String, dept_name: String) {
//        var searchText1 = name
//        var searchText2 = branch_name
//        var searchText3 = dept_name
//        searchText1 = "$name"
//        searchText2 = "$branch_name"
//        searchText3 = "$dept_name"
//
//        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
//        doctorViewModel.searchByName(searchText1, searchText2, searchText3)
//            .observe(viewLifecycleOwner, Observer {
//                adapter.setData(it, strDate)
//                doctorList = it
//
//                Log.d("tag5555", "it: $it")
//iv_loader.visibility = View.VISIBLE
//                if (it.isEmpty()) {
//                    rv_doctor_book.visibility = View.GONE
//                    tv_emptyView.visibility = View.VISIBLE
//////                    iv_loader.visibility = View.VISIBLE
//                } else {
//                    rv_doctor_book.visibility = View.VISIBLE
//                    tv_emptyView.visibility = View.GONE
//                    iv_loader.visibility = View.GONE
//                }
//            })
//    }


}
