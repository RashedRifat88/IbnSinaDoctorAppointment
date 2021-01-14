package com.example.ibnsinadoctorappointment.ui.fragments.doctor_list

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters.DoctorListAdapter
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorChamberBookViewModel
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_book_doctor.*
import kotlinx.android.synthetic.main.fragment_doctor_list.*


class DoctorListFragment : Fragment(R.layout.fragment_doctor_list) {


    private lateinit var doctorList: List<Doctor>
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

    }

    private fun search() {
        tv_search_by_name1.setOnClickListener {
            searchDoctorByName(et_search1.text.toString())

            view?.let { it1 -> context?.hideKeyboard(it1) }
        }

        tv_search_by_branch1.setOnClickListener {
            searchDoctorByBranch(et_search1.text.toString())

            view?.let { it1 -> context?.hideKeyboard(it1) }
        }

        tv_search_by_dept1.setOnClickListener {
            searchDoctorByDept(et_search1.text.toString())

            view?.let { it1 -> context?.hideKeyboard(it1) }
        }




//        et_search.addTextChangedListener(object : TextWatcher {
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                searchDoctorByName(et_search.text.toString())
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//
//
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//        })

    }


    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
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