package com.udacity.shoestore.producteditor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.shareddata.datasorce.local.repository.ShoesLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.jesselima.local.sqlite.data.shoes.models.Shoe
import timber.log.Timber

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */

private const val UPDATE_RESULT_OK = 0

class ProductEditorViewModel(
    private val shoesLocalRepository: ShoesLocalRepository
): ViewModel() {

    private val _shoeMutableLiveData = MutableLiveData<Shoe>()
    val shoe: MutableLiveData<Shoe> = _shoeMutableLiveData

    private val _isShoeUpdated = MutableLiveData<Boolean>()
    val isShoeUpdated: LiveData<Boolean> = _isShoeUpdated

    private val _newShoeId = MutableLiveData<Long>()
    val newShoeId: LiveData<Long> = _newShoeId

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun saveNewShoe(shoe: Shoe) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.insertShoe(shoe = shoe)
            }
            _isLoading.value = false
            _newShoeId.value = result
        }
    }

    fun updateCurrentShoe(shoe: Shoe) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.updateShoe(shoe = shoe)
            }
            _isLoading.value = false
            _isShoeUpdated.value = (result > UPDATE_RESULT_OK)
        }
    }
}