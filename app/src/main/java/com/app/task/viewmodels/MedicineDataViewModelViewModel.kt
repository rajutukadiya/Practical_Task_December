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
    get() = repository.postList

    private val _selectedPost: MutableLiveData<MedicineData> = MutableLiveData()
    val selectedPost: LiveData<MedicineData> get() = _selectedPost

    init {
            viewModelScope.launch {
                repository.getMedicineData()
        }
    }
    fun setSelectedPost(post: MedicineData) {
        _selectedPost.value = post
    }


}
