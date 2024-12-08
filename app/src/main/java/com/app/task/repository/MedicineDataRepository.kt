package com.app.task.repository

import androidx.lifecycle.MutableLiveData
import com.app.task.application.MyApplication

import com.app.task.db.MedicineDataDB
import com.app.task.models.MedicineData
import com.app.task.retrofit.PostAPI
import com.app.task.utils.AppUtils
import extractData
import javax.inject.Inject

class MedicineDataRepository @Inject constructor(private val PostAPI: PostAPI, private val PostDB: MedicineDataDB) {

    private val _medicineData = MutableLiveData<List<MedicineData>?>()
    val postList: MutableLiveData<List<MedicineData>?>
        get() = _medicineData

    /**
     *  Get the list of posts from API or database
     */
    suspend fun getMedicineData(){
        if(AppUtils.isInterConnectionIsAvailable(MyApplication.getAppContext())){
            val result = PostAPI.getPost()
          val responseBody = result.body()?.asJsonObject
            if(result.isSuccessful && result.body() != null){
                extractData(responseBody)?.let { PostDB.getMedicineDataDAO().addMedicineData(it) }
                _medicineData.postValue(  extractData(responseBody)) // Post each individual post
            }
        }else{
            _medicineData.postValue(PostDB.getMedicineDataDAO().getMedicineData())
        }
    }


}


/*
medicationClasses?.forEach { medicationClassElement ->
    val medicationClass = medicationClassElement.asJsonObject

    // Check for `className` and its content
    medicationClass["className"]?.asJsonArray?.forEach {
            classNameElement ->
        val classNameObject = classNameElement.asJsonObject

        // Perform specific checks on `className` content
        val associatedDrugArray = classNameObject["associatedDrug"]?.asJsonArray
        val firstDrug = associatedDrugArray?.get(0)?.asJsonObject

        if (firstDrug != null) {
            val drugName = firstDrug["name"]?.asString.orEmpty()
            val dose =firstDrug["dose"]?.asString.orEmpty()

            if (drugName.isNotEmpty()) {
                associatedDrugs.add(
                    Post(1,drugName,"fsdf")
                )
            }
        }


    }

}*/
