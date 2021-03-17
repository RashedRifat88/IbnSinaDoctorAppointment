package com.example.ibnsinadoctorappointment.retrofit

import com.example.ibnsinadoctorappointment.data.models.branch.BranchModel
import com.example.ibnsinadoctorappointment.data.models.chamber_availavle.ChamberAvailableModel
import com.example.ibnsinadoctorappointment.data.models.department.DepartmentModel
import com.example.ibnsinadoctorappointment.data.models.doctor.DoctorModel
import com.example.ibnsinadoctorappointment.data.models.doctor_chamber_book.DoctorChamberBookModel
import com.example.ibnsinadoctorappointment.data.models.health_package.HealthPackageModel
import com.example.ibnsinadoctorappointment.data.models.investigation.InvestigationModel
import com.example.ibnsinadoctorappointment.data.models.last_update.LastUpdateModel
import com.example.ibnsinadoctorappointment.data.models.submit_appointment.SubmitAppointmentModel
import retrofit2.Call
import retrofit2.http.*

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


    @FormUrlEncoded
    @POST("index.php?appt")
    fun submitAppointment(
        @Field("appointment_note") apptappointment_note: String,
        @Field("branch_id") branch_id: String,
        @Field("chamber_id") chamber_id: String,
        @Field("doctor_id") doctor_id: String,
        @Field("pat_contact") pat_contact: String,
        @Field("doctor_name") doctor_name: String,
        @Field("appointment_date") appointment_date: String,
        @Field("appointment_type") appointment_type: String,
        @Field("gender") gender: String,
        @Field("pat_name") pat_name: String,
        @Field("average_time") average_time: String,
        @Field("start_time") start_time: String,
        @Field("contact2") contact2: String
    ): Call<SubmitAppointmentModel>


//    @GET("rifat/doctor.json")
//    fun getAllDoctors(): Call<MutableList<DoctorModel>>

}