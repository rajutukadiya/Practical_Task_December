package com.app.task.repository

import androidx.lifecycle.MutableLiveData
import com.app.task.application.MyApplication

import com.app.task.db.MedicineDataDB
import com.app.task.models.MedicineData
import com.app.task.retrofit.APIService
import com.app.task.utils.AppUtils
import extractData
import javax.inject.Inject

class MedicineDataRepository @Inject constructor(private val apiService: APIService, private val medicineDataDB: MedicineDataDB) {

    private val _medicineData = MutableLiveData<List<MedicineData>?>()
    val medicineDataList: MutableLiveData<List<MedicineData>?>
        get() = _medicineData

    /**
     *  Get the list of posts from API or database
     */
    suspend fun getMedicineData(){
        if(AppUtils.isInterConnectionIsAvailable(MyApplication.getAppContext())){
            val result = apiService.getMedicineData()
          val responseBody = result.body()?.asJsonObject
            if(result.isSuccessful && result.body() != null){
                extractData(responseBody)?.let { medicineDataDB.getMedicineDataDAO().addMedicineData(it) }
                _medicineData.postValue(  extractData(responseBody)) // Post each individual post
            }
        }else{
            _medicineData.postValue(medicineDataDB.getMedicineDataDAO().getMedicineData())
        }
    }
}
