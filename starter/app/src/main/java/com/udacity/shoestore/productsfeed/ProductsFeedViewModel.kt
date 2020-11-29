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

    init {
        getHotSellingShoes()
        getAllShoesExceptHotSelling()
    }

    private val _allShoesMutableLiveData = MutableLiveData<List<Shoe>>()
    val allShoesExceptHotSellingLiveData: LiveData<List<Shoe>> = _allShoesMutableLiveData

    private val _hotSellingShoesMutableLiveData = MutableLiveData<List<Shoe>>()
    val hotSellingShoesLiveData: LiveData<List<Shoe>> = _hotSellingShoesMutableLiveData

//    private val _shoesByCategoryMutableLiveData = MutableLiveData<List<Shoe>>()
//    val shoesByCategoryLiveData: LiveData<List<Shoe>> = _shoesByCategoryMutableLiveData

    private fun getAllShoesExceptHotSelling() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                shoesLocalRepository.getAllShoesExceptHotSelling()
            }
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

//    fun getShoesByCategory(category: String) {
//        viewModelScope.launch {
//            val result = withContext(Dispatchers.IO) {
//                shoesLocalRepository.getShoesByCategory(category = category)
//            }
//            _shoesByCategoryMutableLiveData.value = result
//            Timber.d(result.toString())
//        }
//    }
}