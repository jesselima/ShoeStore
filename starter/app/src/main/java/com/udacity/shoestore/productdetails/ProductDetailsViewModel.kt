package com.udacity.shoestore.productdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.shareddata.datasorce.local.repository.ShoesLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.jesselima.local.sqlite.data.shoes.models.Shoe

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */

private const val DELETE_RESULT_OK = 1

class ProductDetailsViewModel(
    private val shoesLocalRepository: ShoesLocalRepository
): ViewModel() {

    private val _shoeMutableLiveData = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe> = _shoeMutableLiveData

    private val _isShoeDeleted = MutableLiveData<Boolean>()
    val isShoeDeleted: LiveData<Boolean> = _isShoeDeleted

    fun getShoeById(id: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.getShoeById(id = id)
            }
            _shoeMutableLiveData.value = result
        }
    }

    fun deleteShoe() {
        viewModelScope.launch {
            shoe.value?.let {
                val result = withContext(Dispatchers.IO) {
                    shoesLocalRepository.deleteShoe(shoe = it)
                }
                _isShoeDeleted.value = result >= DELETE_RESULT_OK
            }
        }
    }
}