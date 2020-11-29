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

private const val INSERT_RESULT_OK = 1L
private const val UPDATE_RESULT_OK = 1

class ProductEditorViewModel(
    private val shoesLocalRepository: ShoesLocalRepository
): ViewModel() {

    private val _shoeMutableLiveData = MutableLiveData<Shoe>()
    val shoesLiveData: LiveData<Shoe> = _shoeMutableLiveData

    private val _isShoeSavedMutableLiveData = MutableLiveData<Boolean>()
    val isShoeSavedLiveData: LiveData<Boolean> = _isShoeSavedMutableLiveData

    private val _isShoeUpdatedMutableLiveData = MutableLiveData<Boolean>()
    val isShoeUpdatedLiveData: LiveData<Boolean> = _isShoeUpdatedMutableLiveData

    fun getShoeById(id: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.getShoeById(id)
            }
            _shoeMutableLiveData.value = result
            Timber.d(result.toString())
        }
    }

    fun saveNewShoe(shoe: Shoe) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.insertShoe(shoe = shoe)
            }
            _isShoeSavedMutableLiveData.value = (result == INSERT_RESULT_OK)
            Timber.d(result.toString())
        }
    }

    fun updateCurrentShoe(shoe: Shoe) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.updateShoe(shoe = shoe)
            }
            _isShoeUpdatedMutableLiveData.value = (result == UPDATE_RESULT_OK)
            Timber.d(result.toString())
        }
    }
}