package com.example.ibnsinadoctorappointment.retrofit

import com.example.ibnsinadoctorappointment.data.models.branch.BranchModel
import com.example.ibnsinadoctorappointment.data.models.chamber_availavle.ChamberAvailableModel
import com.example.ibnsinadoctorappointment.data.models.department.DepartmentModel
import com.example.ibnsinadoctorappointment.data.models.doctor.DoctorModel
import com.example.ibnsinadoctorappointment.data.models.doctor_chamber_book.DoctorChamberBookModel
import com.example.ibnsinadoctorappointment.data.models.health_package.HealthPackageModel
import com.example.ibnsinadoctorappointment.data.models.investigation.InvestigationModel
import com.example.ibnsinadoctorappointment.data.models.last_update.LastUpdateModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

//    @GET("/3/movie/popular")
//    fun getMovies(@Query("api_key") key: String): Call<Doctor>

//    @GET("rifat/doctor.json")
//    fun getAllDoctors(): Call<MutableList<Doctor>>


    @GET("doctor.json")
    fun getAllDoctors(): Call<DoctorModel>

    @GET("chamber.json")
    fun getChamberDoctors(): Call<DoctorChamberBookModel>

    @GET("investigation.json")
    fun getInvestigations(): Call<InvestigationModel>

    @GET("last_update.json")
    fun getLastUpdates(): Call<LastUpdateModel>

    @GET("department.json")
    fun getDepartments(): Call<DepartmentModel>

    @GET("package.json")
    fun getPackages(): Call<HealthPackageModel>

    @GET("branch.json")
    fun getBranches(): Call<BranchModel>

    @GET("index.php")
    fun checkAvailableChamber(
        @Query("chamber") chamber: String,
        @Query("date") date: String
    ): Call<ChamberAvailableModel>


//    @GET("rifat/doctor.json")
//    fun getAllDoctors(): Call<MutableList<DoctorModel>>

}