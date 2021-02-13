package com.example.ibnsinadoctorappointment.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.example.ibnsinadoctorappointment.R
import com.example.ibnsinadoctorappointment.data.models.Doctor
import com.example.ibnsinadoctorappointment.data.models.doctor.Content
import com.example.ibnsinadoctorappointment.data.models.doctor.DoctorModel
import com.example.ibnsinadoctorappointment.retrofit.APIServices
import com.example.ibnsinadoctorappointment.retrofit.RetrofitClientInstance
import com.example.ibnsinadoctorappointment.ui.viewmodels.DoctorViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainActivity : AppCompatActivity() {

    private lateinit var doctorViewModel: DoctorViewModel

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        actionBarLoad()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

//        appBarConfiguration = AppBarConfiguration(
//            setOf(androidx.navigation.ui.R.id.homeFragment, androidx.navigation.ui.R.id.searchFragment, androidx.navigation.ui.R.id.settingsFragment),
//            drawer_layout
//        )
//
//        setSupportActionBar(toolbar)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        bottom_nav.setupWithNavController(navController)
//        nav_view.setupWithNavController(navController)

        setupActionBarWithNavController(navController)

//        doctorViewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
//        doctorApiCall()
    }

    private fun doctorApiCall() {

        val request = RetrofitClientInstance.buildService(APIServices::class.java)
//        val call = request.getMovies(getString(R.string.api_key))
        val call = request.getAllDoctors()

        call.enqueue(object : Callback<DoctorModel> {
            override fun onResponse(call: Call<DoctorModel>, response: Response<DoctorModel>) {
                if (response.isSuccessful) {
//                    progress_bar.visibility = View.GONE
//                    recyclerView.apply {
//                        setHasFixedSize(true)
//                        layoutManager = LinearLayoutManager(this@MainActivity)
//                        adapter = MoviesAdapter(response.body()!!.results)
//                    }

                    val contents: List<Content> = response.body()!!.content
//                    val response = response.body()!!.content
                    Toast.makeText(
                        this@MainActivity,
                        "response: " + contents.toString(),
                        Toast.LENGTH_LONG
                    ).show()

                    Log.d("tagRifat33333", "response is: " + contents)

                    for (i in 0 until contents.size) {
                        val itemDetail = contents.get(i)

                        val doctor = Doctor(
                            itemDetail.id,
                            itemDetail.nickName,
                            itemDetail.avatarPath,
                            itemDetail.doctorDept.name,
                            itemDetail.branchNo,
                            itemDetail.qualification,
                            itemDetail.designation

                        )
                        doctorViewModel.addDoctor(doctor)
                    }
                    ///


//                    val contents: List<Content> = response.body()!!.content

//                        val jsonText = resources.openRawResource(R.raw.doctor_response)
//                            .bufferedReader().use { it.readText() }

//                        val obj = JSONObject(jsonText)
//                        val obj = JSONObject(response)

//                        val obj = JSONObject(response)
//                        val itemArray = obj.getJSONArray("content")
//                        for (i in 0 until itemArray.length()) {
//                            val itemDetail = itemArray.getJSONObject(i)
//                            Log.d("tagRifat33333", "name is: " + itemDetail.getString("nickName"))
//                            val doctor = Doctor(
//                                itemDetail.getInt("id"),
//                                itemDetail.getString("nickName"),
//                                itemDetail.getString("qualification"),
//                                itemDetail.getString("designation")
//                            )
//                            doctorViewModel.addDoctor(doctor)
//                        }

                    ///

//                        Log.d("tagRifat1234", "json is: " + jsonText)

                }
            }

            override fun onFailure(call: Call<DoctorModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


    }




    private fun actionBarLoad() {
        val actionBar: android.app.ActionBar? = actionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setCustomView(R.layout.custom_main_actionbar)
//        (findViewById<View>(R.id.action_bar_title) as TextView).text =
//            "This is a long text title that will wrap to multiple lines, when necessary."
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if (item.itemId == androidx.navigation.ui.R.id.termsAndConditions) {
//            val action = NavGraphDirections.actionGlobalTermsFragment()
//            navController.navigate(action)
//            true
//        } else {
//            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//        }

        return if (item.itemId == R.id.callCenterNumber) {

            callAlert();

            true
        } else {
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }

    private fun callAlert() {
        val wrapInScrollView = true
        val dialog: MaterialDialog =
            MaterialDialog.Builder(this@MainActivity) //                    .title(getResources().getString(R.string.no_internet_text_title))
                .customView(
                    R.layout.material_dialog_call_view,
                    wrapInScrollView
                ) //                    .content(getResources().getString(R.string.no_internet_text_body))
                .build()

        val linear_call1 = dialog.customView!!.findViewById<LinearLayout>(R.id.linear_call1)
        val linear_call2 = dialog.customView!!.findViewById<LinearLayout>(R.id.linear_call2)

        linear_call1.setOnClickListener {
            callIntent("024545677")
            dialog.dismiss()
        }
        linear_call2.setOnClickListener {
            callIntent("0156997788")
            dialog.dismiss()
        }

        dialog.show()

//        dialog.setCancelable(false)
//        dialog.setCanceledOnTouchOutside(false)
    }

    private fun callIntent(s: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + s)
        startActivity(dialIntent)
    }


    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }


}