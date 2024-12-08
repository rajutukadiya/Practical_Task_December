package com.app.task.activity.detailscreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.app.task.R
import com.app.task.databinding.ActivityMedicineDetailBinding

import com.app.task.models.MedicineData
import com.app.task.viewmodels.MedicineDataViewModelViewModel
import com.google.gson.Gson
import com.practicaltask.customview.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsScreen: BaseActivity() {
    private lateinit var binding: ActivityMedicineDetailBinding
    private var medicineDataViewModel: MedicineDataViewModelViewModel? =null
    override fun MyView(): Int {
        return R.layout.activity_medicine_detail
    }

    override fun initialization() {
        binding = ActivityMedicineDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun prepareview() {
        loadDetaildata()

    }

    override fun listener() {
        binding.imgback.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    fun loadDetaildata()
    {

        medicineDataViewModel = ViewModelProvider(this).get(MedicineDataViewModelViewModel::class.java)

        val postJSON = intent.getStringExtra("MedicineDetail")

        val gson = Gson()

        val post = gson.fromJson(postJSON, MedicineData::class.java)

        medicineDataViewModel!!.setSelectedPost(post)

        medicineDataViewModel!!.selectedPost.observe(this) { selectedPost ->

            binding.txtName.text = getString(R.string.name)+selectedPost.name
            binding.txtDose.text =  getString(R.string.Dose)+selectedPost.dose
            binding.txtStrength.text =  getString(R.string.strength)+selectedPost.strength

        }
    }



}

















