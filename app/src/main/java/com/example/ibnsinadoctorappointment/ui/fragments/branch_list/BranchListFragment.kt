package com.example.ibnsinadoctorappointment.ui.fragments.branch_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyinvestigations.ui.fragments.investigation.investigation_list.adapters.BranchListAdapter
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Branch
import com.example.ibnsinadoctorappointment.ui.viewmodels.BranchViewModel
import kotlinx.android.synthetic.main.fragment_branch_list.*


class BranchListFragment : Fragment(R.layout.fragment_branch_list) {

    private lateinit var rv_branchList: List<Branch>
    private lateinit var rv_branchViewModel: BranchViewModel
    private lateinit var adapter: BranchListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BranchListAdapter()
        rv_branch.adapter = adapter
        rv_branch.layoutManager = LinearLayoutManager(context)

        //Instantiate and create viewmodel observers
        viewModels()

    }


    private fun viewModels() {
        rv_branchViewModel = ViewModelProvider(this).get(BranchViewModel::class.java)

        rv_branchViewModel.getAllBranchs.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            rv_branchList = it

//            if (it.isEmpty()) {
//                rv_branch.visibility = View.GONE
//                tv_emptyView_branch.visibility = View.VISIBLE
//            } else {
//                rv_branch.visibility = View.VISIBLE
//                tv_emptyView_branch.visibility = View.GONE
//            }
        })
    }



}

