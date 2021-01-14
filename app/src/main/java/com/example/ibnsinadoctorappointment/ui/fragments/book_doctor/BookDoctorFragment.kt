package com.example.ibnsinadoctorappointment.ui.fragments.book_doctor

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters.DoctorChamberBookListAdapter
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorChamberBookViewModel
import kotlinx.android.synthetic.main.fragment_book_doctor.*


class BookDoctorFragment : Fragment(R.layout.fragment_book_doctor) {


    private lateinit var doctorList: List<DoctorChamberBook>
    private lateinit var doctorViewModel: DoctorChamberBookViewModel
    private lateinit var adapter: DoctorChamberBookListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DoctorChamberBookListAdapter()
        rv_doctor_book.adapter = adapter
        rv_doctor_book.layoutManager = LinearLayoutManager(context)

        //Instantiate and create viewmodel observers
        viewModels()

        searchByName()

    }

    private fun searchByName() {
        tv_search_by_name.setOnClickListener {
            searchDoctorByName(et_search.text.toString())

            view?.let { it1 -> context?.hideKeyboard(it1) }
        }

       tv_search_by_branch.setOnClickListener {
            searchDoctorByBranch(et_search.text.toString())

           view?.let { it1 -> context?.hideKeyboard(it1) }
        }

       tv_search_by_dept.setOnClickListener {
            searchDoctorByDept(et_search.text.toString())

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
        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)

        doctorViewModel.getAllDoctorChamberBooks.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }
        })
    }


    private fun searchDoctorByBranch(title: String) {
        var searchText = title
        searchText = "%$title%"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByBranch(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            Log.d("tag5555", "it: $it")

            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }

            //            recyclerView.apply {
//                adapter = RecyclerAdapterMain(it, this@MainActivity)
        })

    }


    private fun searchDoctorByDept(title: String) {
        var searchText = title
        searchText = "%$title%"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByDept(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            Log.d("tag5555", "it: $it")

            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }

            //            recyclerView.apply {
//                adapter = RecyclerAdapterMain(it, this@MainActivity)
        })

    }


    private fun searchDoctorByName(title: String) {
        var searchText = title
        searchText = "%$title%"

        doctorViewModel = ViewModelProvider(this).get(DoctorChamberBookViewModel::class.java)
        doctorViewModel.searchByName(searchText).observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            doctorList = it

            Log.d("tag5555", "it: $it")

            if (it.isEmpty()) {
                rv_doctor_book.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_doctor_book.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }

            //            recyclerView.apply {
//                adapter = RecyclerAdapterMain(it, this@MainActivity)
        })

    }


}
