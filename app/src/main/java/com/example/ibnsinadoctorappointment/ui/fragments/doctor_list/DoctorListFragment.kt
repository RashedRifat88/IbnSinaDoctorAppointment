package com.example.ibnsinadoctorappointment.ui.fragments.doctor_list

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters.DoctorListAdapter
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Branch
import com.example.ibnsinadoctorappointment.data.models.Department
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.ui.viewmodels.BranchViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DepartmentViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_book_doctor.*
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import java.util.*
import kotlin.collections.ArrayList


class DoctorListFragment : Fragment(R.layout.fragment_doctor_list) {

    private lateinit var branchViewModel: BranchViewModel
    private lateinit var departmentViewModel: DepartmentViewModel


    var dataAdapter: ArrayAdapter<String>? = null
    var sp1SelectedValue = ""
    var postalCodes: List<String>? = null
    var areaPostalCodeMap: HashMap<String, String>? = null
    var postalCode: String? = null


    private lateinit var doctorList: List<Doctor>
    private lateinit  var branchList: List<Branch>
    private lateinit  var deptList: List<Department>
    private var  branchListString: MutableList<String> = ArrayList()
    private var  deptListString: MutableList<String> = ArrayList()
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var adapter: DoctorListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DoctorListAdapter()
        rv_doctor.adapter = adapter
        rv_doctor.layoutManager = LinearLayoutManager(context)

        //Instantiate and create viewmodel observers
        viewModels()
        search()

        branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)
        departmentViewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)

        loadSpinner1()
        loadSpinner2()
    }


    fun loadSpinner1() {
        branchViewModel.getAllBranchs.observe(viewLifecycleOwner, Observer {
            branchList = it

            Log.d("tagrifat33333", "branch list: $it")
            Log.d("tagrifat33333", "branchList.size:" + branchList.size)

            for (i in 0 until branchList.size) {
                val itemDetail = branchList.get(i)
                branchListString.add(itemDetail.branchName)

            }
            Log.d("tagrifat33333", "branchListString: $branchListString")

            // access the spinner
            if (spinner1 != null) {
                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.simple_spinner_item, branchListString)
                }
                spinner1.adapter = adapter

                spinner1.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>,
                                                view: View, position: Int, id: Long) {
                        Toast.makeText(context,
                            branchListString.toString(), Toast.LENGTH_SHORT).show()
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

            for (i in 0 until deptList.size) {
                val itemDetail = deptList.get(i)
                deptListString.add(itemDetail.name)

            }
            Log.d("tagrifat33333", "deptListString: $deptListString")

            // access the spinner
            if (spinner2 != null) {
                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.simple_spinner_item, deptListString)
                }
                spinner2.adapter = adapter

                spinner2.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>,
                                                view: View, position: Int, id: Long) {
                        Toast.makeText(context,
                            deptListString.toString(), Toast.LENGTH_SHORT).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                }
            }
        })
    }






    private fun search() {
        tv_search_by_name1.setOnClickListener {
            searchDoctorByName(et_search1.text.toString())

            view?.let { it1 -> context?.hideKeyboard(it1) }
        }

//        tv_search_by_branch1.setOnClickListener {
//            searchDoctorByBranch(et_search1.text.toString())
//
//            view?.let { it1 -> context?.hideKeyboard(it1) }
//        }
//
//        tv_search_by_dept1.setOnClickListener {
//            searchDoctorByDept(et_search1.text.toString())
//
//            view?.let { it1 -> context?.hideKeyboard(it1) }
//        }


        et_search1.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchDoctorByName(et_search1.text.toString())
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
        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)

        doctorViewModel.getAllDoctors.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            Log.d("tagrifat33333", "doctor list: $it")

            if (it.isEmpty()) {
                rv_doctor.visibility = View.GONE
                tv_emptyView1.visibility = View.VISIBLE
            } else {
                rv_doctor.visibility = View.VISIBLE
                tv_emptyView1.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorByBranch(title: String) {
        var searchText = title
        searchText = "%$title%"

        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        doctorViewModel.searchByBranch(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            Log.d("tag5555", "it: $it")

            if (it.isEmpty()) {
                rv_doctor.visibility = View.GONE
                tv_emptyView1.visibility = View.VISIBLE
            } else {
                rv_doctor.visibility = View.VISIBLE
                tv_emptyView1.visibility = View.GONE
            }

            //            recyclerView.apply {
//                adapter = RecyclerAdapterMain(it, this@MainActivity)
        })

    }


    private fun searchDoctorByDept(title: String) {
        var searchText = title
        searchText = "%$title%"

        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        doctorViewModel.searchByDept(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            Log.d("tag5555", "it: $it")

            if (it.isEmpty()) {
                rv_doctor.visibility = View.GONE
                tv_emptyView1.visibility = View.VISIBLE
            } else {
                rv_doctor.visibility = View.VISIBLE
                tv_emptyView1.visibility = View.GONE
            }

            //            recyclerView.apply {
//                adapter = RecyclerAdapterMain(it, this@MainActivity)
        })

    }


    private fun searchDoctorByName(title: String) {
        var searchText = title
        searchText = "%$title%"

        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        doctorViewModel.searchByName(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            Log.d("tag5555", "it: $it")

            if (it.isEmpty()) {
                rv_doctor.visibility = View.GONE
                tv_emptyView1.visibility = View.VISIBLE
            } else {
                rv_doctor.visibility = View.VISIBLE
                tv_emptyView1.visibility = View.GONE
            }

            //            recyclerView.apply {
//                adapter = RecyclerAdapterMain(it, this@MainActivity)
        })

    }


}