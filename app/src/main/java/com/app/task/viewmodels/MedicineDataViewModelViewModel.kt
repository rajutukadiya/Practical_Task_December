package com.app.task.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.task.models.MedicineData
import com.app.task.repository.MedicineDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineDataViewModelViewModel @Inject constructor(private val repository: MedicineDataRepository) : ViewModel() {

    val medicineDataListLiveData : MutableLiveData<List<MedicineData>?>
    get() = repository.medicineDataList

    private val _selectedMedicine: MutableLiveData<MedicineData> = MutableLiveData()
    val selectedMedicine: LiveData<MedicineData> get() = _selectedMedicine

    init {
            viewModelScope.launch {
                repository.getMedicineData()
        }
    }
    fun setSelectedMedicine(medicineData: MedicineData) {
        _selectedMedicine.value = medicineData
    }


}
