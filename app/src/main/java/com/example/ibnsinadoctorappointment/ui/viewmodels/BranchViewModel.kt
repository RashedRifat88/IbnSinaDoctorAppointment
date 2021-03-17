package com.example.ibnsinadoctorappointment.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ibnsinabranchappointment.logic.repository.BranchRepository
import com.example.ibnsinadoctorappointment.data.database.MyDatabase
import com.example.ibnsinadoctorappointment.data.models.Branch
import com.example.ibnsinadoctorappointment.data.models.DoctorChamberBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BranchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BranchRepository
    val getAllBranchs: LiveData<List<Branch>>


    init {
        val BranchDao = MyDatabase.getDatabase(application).branchDao()
        repository = BranchRepository(BranchDao)

        getAllBranchs = repository.getAllBranches
    }

    fun addBranch(Branch: Branch) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBranch(Branch)
        }
    }

    fun updateBranch(Branch: Branch) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBranch(Branch)
        }
    }

    fun deleteBranch(Branch: Branch) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBranch(Branch)
        }
    }

    fun deleteAllBranchs() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllBranches()
        }
    }

    fun getAllBranchs() {
        viewModelScope.launch(Dispatchers.IO) {
           repository.getAllBranches
        }
    }



    fun getBranchObjByBranchName(name: String): Branch {
        return repository.getBranchObjByBranchName(name)
    }




//    fun searchById(name: String): LiveData<List<Branch>> {
//        return repository.searchById(name)
//    }



//    fun searchByName(name: String): LiveData<List<Branch>> {
//        return repository.searchByName(name)
//    }
//
//
//    fun searchByBranch(name: String): LiveData<List<Branch>> {
//        return repository.searchByBranch(name)
//    }
//
//
//    fun searchByDept(name: String): LiveData<List<Branch>> {
//        return repository.searchByDept(name)
//    }


}