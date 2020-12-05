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
private const val KEY_IS_AUTHENTICATED = "KEY_IS_AUTHENTICATED"

class LoginViewModel(
    private val shoesLocalRepository: ShoesLocalRepository,
    private val sharedPrefUserStorage: SharedPrefUserStorage
): ViewModel() {

    private val _hasSampleDataMutableLiveData = MutableLiveData<Boolean>()
    val hasSampleData: LiveData<Boolean> = _hasSampleDataMutableLiveData

    private val _isLoggedInMutableLiveData = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean> = _isLoggedInMutableLiveData

    private val _isLoadingMutableLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoadingMutableLiveData

    private val _isSavingSampleDataMutableLiveData = MutableLiveData<Boolean>()
    val isSavingSampleDataLiveData: LiveData<Boolean> = _isSavingSampleDataMutableLiveData


    init {
        hasSampleShoesOnDatabase()
    }

    private fun isAuthenticated(): Boolean? {
        return sharedPrefUserStorage.getBooleanValue(KEY_IS_AUTHENTICATED) ?: false
    }

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
            _isSavingSampleDataMutableLiveData.value = false
            _hasSampleDataMutableLiveData.value = true
        }
    }

    private fun hasSampleShoesOnDatabase() {
        viewModelScope.launch {
            val allShoes = withContext(Dispatchers.IO) {
                shoesLocalRepository.getAllShoes()
            }
            _isSavingSampleDataMutableLiveData.value = false
            _hasSampleDataMutableLiveData.value = allShoes.isNotEmpty()
        }
    }

    // Sample Data for testes only
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
            name = "Altitude 5",
            brand = "Pulma",
            category = "casual",
            isHotSelling = false,
            price = 150.0f,
            quantitySold = 123,
            season = "Winter",
            stockQuantity = 88,
            year = 2020,
            image = "none"
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
            name = "Runner Rouge",
            brand = "Nike",
            category = "casual",
            isHotSelling = true,
            price = 120.0f,
            quantitySold = 56,
            season = "Spring",
            stockQuantity = 21,
            year = 2020,
            image = "none"
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