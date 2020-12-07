package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.core.KeyValues.KEY_IS_AUTHENTICATED
import com.udacity.shoestore.core.getDummyShoes
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

    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> = _loginError

    private val _isSavingSampleDataMutableLiveData = MutableLiveData<Boolean>()
    val isSavingSampleDataLiveData: LiveData<Boolean> = _isSavingSampleDataMutableLiveData


    init {
        hasSampleShoesOnDatabase()
    }

    fun authenticateUser(email: String, password: String) {
        // TODO: FAKE LOGIN FOR TESTING ONLY
        if (email == "test@test.com" && password == "1234") {
            _isLoadingMutableLiveData.value = true
            viewModelScope.launch {
                val result = withContext(Dispatchers.IO) {
                    delay(FAKE_LOGIN_DELAY)
                    true
                }
                Timber.d(result.toString())
                _isLoadingMutableLiveData.value = false
                _isLoggedInMutableLiveData.value = result
                sharedPrefUserStorage.saveValue(KEY_IS_AUTHENTICATED, true)
            }
        } else {
            _loginError.value = "Invalid email or password."
            return
        }
    }

    fun insertSampleShoes() {
        _isSavingSampleDataMutableLiveData.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(2000)
                getDummyShoes().forEach {
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
}