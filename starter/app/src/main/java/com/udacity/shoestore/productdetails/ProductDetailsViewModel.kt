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
class ProductDetailsViewModel(
    private val shoesLocalRepository: ShoesLocalRepository
): ViewModel() {

    private val _shoeMutableLiveData = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe> = _shoeMutableLiveData

    fun getShoeById(id: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.getShoeById(id = id)
            }
            _shoeMutableLiveData.value = result
        }
    }
}