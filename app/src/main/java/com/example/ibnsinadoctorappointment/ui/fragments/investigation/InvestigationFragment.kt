package com.example.ibnsinadoctorappointment.ui.fragments.investigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.ColumnInfo
import com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters.InvestigationListAdapter
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Investigation
import com.example.ibnsinadoctorappointment.ui.viewmodels.InvestigationViewModel
import kotlinx.android.synthetic.main.fragment_investigation.*


class InvestigationFragment : Fragment(R.layout.fragment_investigation) {

    private lateinit var investigationList: List<Investigation>
    private lateinit var investigationViewModel: InvestigationViewModel
    private lateinit var adapter: InvestigationListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = InvestigationListAdapter()
        rv_investigation.adapter = adapter
        rv_investigation.layoutManager = LinearLayoutManager(context)

        //Instantiate and create viewmodel observers
        viewModels()

    }


    private fun viewModels() {
        investigationViewModel = ViewModelProvider(this).get(InvestigationViewModel::class.java)

        investigationViewModel.getAllInvestigations.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            investigationList = it

            if (it.isEmpty()) {
                rv_investigation.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_investigation.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }
        })
    }



}

