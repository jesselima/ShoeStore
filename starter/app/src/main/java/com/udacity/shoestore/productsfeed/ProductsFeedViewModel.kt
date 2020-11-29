package com.udacity.shoestore.productsfeed

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
class ProductsFeedViewModel(
    private val shoesLocalRepository: ShoesLocalRepository
): ViewModel() {

    private val _isLoadingMutableLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoadingMutableLiveData

    private val _allShoesMutableLiveData = MutableLiveData<List<Shoe>>()
    val allShoesLiveData: LiveData<List<Shoe>> = _allShoesMutableLiveData

    private val _hotSellingShoesMutableLiveData = MutableLiveData<List<Shoe>>()
    val hotSellingShoesLiveData: LiveData<List<Shoe>> = _hotSellingShoesMutableLiveData

    init {
        _isLoadingMutableLiveData.value = true
        getAllShoes()
        getHotSellingShoes()
    }

    private fun getAllShoes() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.getAllShoesExceptHotSelling()
            }
            _isLoadingMutableLiveData.value = false
            _allShoesMutableLiveData.value = result
            Timber.d(result.toString())
        }
    }

    private fun getHotSellingShoes() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.getHotSellingShoes()
            }
            _hotSellingShoesMutableLiveData.value = result
            Timber.d(result.toString())
        }
    }
}