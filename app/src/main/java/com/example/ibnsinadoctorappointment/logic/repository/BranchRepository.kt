package com.example.ibnsinabranchappointment.logic.repository

import androidx.lifecycle.LiveData
import com.example.ibnsinadoctorappointment.data.models.Branch
import com.example.ibnsinadoctorappointment.logic.dao.BranchDao

class BranchRepository (private val branchDao: BranchDao) {
    val getAllBranches: LiveData<List<Branch>> = branchDao.getAllBranchs()

    suspend fun addBranch(branch: Branch) {
        branchDao.addBranch(branch)
    }

    suspend fun updateBranch(branch: Branch) {
        branchDao.updateBranch(branch)
    }

    suspend fun deleteBranch(branch: Branch) {
        branchDao.deleteBranch(branch)
    }

    suspend fun deleteAllBranches() {
        branchDao.deleteAll()
    }




//    fun searchById(name : String): LiveData<List<Branch>>{
//        return branchDao.searchById(name)
//    }


//    fun searchByName(name : String): LiveData<List<Branch>>{
//        return branchDao.searchByName(name)
//    }
//
//
//
//    fun searchByBranch(name : String): LiveData<List<Branch>>{
//        return branchDao.searchByBranch(name)
//    }
//
//
//
//    fun searchByDept(name : String): LiveData<List<Branch>>{
//        return branchDao.searchByDept(name)
//    }


}