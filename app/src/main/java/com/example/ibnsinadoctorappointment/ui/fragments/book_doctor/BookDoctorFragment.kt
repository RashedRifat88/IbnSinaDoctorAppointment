package com.example.ibnsinadoctorappointment.ui.fragments.book_doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters.DoctorChamberBookListAdapter
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorChamberBookViewModel
import kotlinx.android.synthetic.main.fragment_book_doctor.*
import kotlinx.android.synthetic.main.fragment_doctor_list.tv_emptyView


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


}