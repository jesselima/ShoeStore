package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.shareddata.datasorce.local.repository.ShoesLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.jesselima.local.sharedpref.data.SharedPrefUserStorage
import tech.jesselima.local.sqlite.data.shoes.models.Shoe
import timber.log.Timber

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
private const val FAKE_LOGIN_DELAY = 5000L
private const val KEY_HAS_SAMPLE_DATA = "HAS_SAMPLE_DATA"

class LoginViewModel(
    private val shoesLocalRepository: ShoesLocalRepository,
    private val sharedPrefUserStorage: SharedPrefUserStorage
): ViewModel() {

    private val _hasSampleDataMutableLiveData = MutableLiveData<Boolean>()
    val hasSampleAlreadyDataLiveData: LiveData<Boolean> = _hasSampleDataMutableLiveData

    init {
        _hasSampleDataMutableLiveData.value = checkIfHasSampleData()
    }

    private fun checkIfHasSampleData(): Boolean? {
        return sharedPrefUserStorage.getBooleanValue(KEY_HAS_SAMPLE_DATA) ?: false
    }

    private val _isLoggedInMutableLiveData = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean> = _isLoggedInMutableLiveData

    private val _isLoadingMutableLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoadingMutableLiveData

    private val _isSavingSampleDataMutableLiveData = MutableLiveData<Boolean>()
    val isSavingSampleDataLiveData: LiveData<Boolean> = _isSavingSampleDataMutableLiveData

    fun authenticateUser(email: String, password: String) {
        _isLoadingMutableLiveData.value = true
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                delay(FAKE_LOGIN_DELAY)
                true
            }
            Timber.d(result.toString())
            _isLoadingMutableLiveData.value = false
            _hasSampleDataMutableLiveData.value = true
            _isLoggedInMutableLiveData.value = result
        }
    }

    fun insertSampleShoes() {
        _isSavingSampleDataMutableLiveData.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(2000)
                shoes.forEach {
                    shoesLocalRepository.insertShoe(it)
                }
            }
            sharedPrefUserStorage.saveValue(KEY_HAS_SAMPLE_DATA, value = true)
            _isSavingSampleDataMutableLiveData.value = false
        }
    }

    private fun logAllShoes() {
        viewModelScope.launch {
            val allShoes = withContext(Dispatchers.IO) {
                shoesLocalRepository.getAllShoes()
            }
            allShoes.forEach {
                Timber.i("\n$it)")
            }
        }
    }

    private val shoes = listOf(
        Shoe(
            name = "Boot Extreme",
            brand = "Adidas",
            category = "adventure",
            isHotSelling = true,
            price = 40.0f,
            quantitySold = 50,
            season = "Winter",
            stockQuantity = 15,
            year = 2019,
            image = "model_shoe_05"
        ),
        Shoe(
            name = "Climber X",
            brand = "X&Z",
            category = "social",
            isHotSelling = true,
            price = 120.0f,
            quantitySold = 48,
            season = "Summer",
            stockQuantity = 15,
            year = 2020,
            image = "model_shoe_01"
        ),
        Shoe(
            name = "Urban Low",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 55.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 51,
            year = 2019,
            image = "model_shoe_08"
        ),
        Shoe(
            name = "Adventura Mantis",
            brand = "Victor Hugo",
            category = "social",
            isHotSelling = true,
            price = 56.0f,
            quantitySold = 150,
            season = "Summer",
            stockQuantity = 15,
            year = 2019,
            image = "model_shoe_04"
        ),
        Shoe(
            name = "Boot Master X 1",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 35.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 77,
            year = 2020,
            image = "model_shoe_03"
        ),
        Shoe(
            name = "Sport Clutch",
            brand = "Pulma",
            category = "social",
            isHotSelling = true,
            price = 45.0f,
            quantitySold = 50,
            season = "Summer",
            stockQuantity = 15,
            year = 2020,
            image = "model_shoe_02"
        ),
        Shoe(
            name = "Boot Master X 1",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 93.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 90,
            year = 2019,
            image = "model_shoe_01"
        ),
        Shoe(
            name = "Adistar Pop",
            brand = "Adidas",
            category = "sport",
            isHotSelling = false,
            price = 70.0f,
            quantitySold = 48,
            season = "Summer",
            stockQuantity = 60,
            year = 2020,
            image = "model_shoe_09"
        ),
        Shoe(
            name = "Adventura Mantis",
            brand = "Victor Hugo",
            category = "social",
            isHotSelling = true,
            price = 56.0f,
            quantitySold = 150,
            season = "Summer",
            stockQuantity = 15,
            year = 2019,
            image = "model_shoe_04"
        ),
        Shoe(
            name = "Boot Master X 1",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 35.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 77,
            year = 2020,
            image = "model_shoe_03"
        ),
        Shoe(
            name = "Sport Clutch",
            brand = "Pulma",
            category = "social",
            isHotSelling = true,
            price = 45.0f,
            quantitySold = 50,
            season = "Summer",
            stockQuantity = 15,
            year = 2020,
            image = "model_shoe_02"
        ),
        Shoe(
            name = "Boot Master X 1",
            brand = "Nike",
            category = "casual",
            isHotSelling = false,
            price = 93.0f,
            quantitySold = 48,
            season = "Winter",
            stockQuantity = 90,
            year = 2019,
            image = "model_shoe_01"
        ),
        Shoe(
            name = "Adistar Pop",
            brand = "Adidas",
            category = "sport",
            isHotSelling = false,
            price = 70.0f,
            quantitySold = 48,
            season = "Summer",
            stockQuantity = 60,
            year = 2020,
            image = "model_shoe_09"
        )
    )
}