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

        val medicineJSON = intent.getStringExtra("MedicineDetail")

        val gson = Gson()

        val medicine = gson.fromJson(medicineJSON, MedicineData::class.java)

        medicineDataViewModel?.setSelectedMedicine(medicine)

        medicineDataViewModel?.selectedMedicine?.observe(this) { medicineData ->

            binding.txtName.text = getString(R.string.name)+medicineData.name
            binding.txtDose.text =  getString(R.string.Dose)+medicineData.dose
            binding.txtStrength.text =  getString(R.string.strength)+medicineData.strength

        }
    }



}

















