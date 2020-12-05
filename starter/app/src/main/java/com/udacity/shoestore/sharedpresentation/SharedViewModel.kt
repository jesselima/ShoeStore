package com.udacity.shoestore.sharedpresentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by jesselima on 29/11/20.
 * This is a part of the project ShoeStore.
 */
class SharedViewModel: ViewModel() {
    val sharedShoeId = MutableLiveData<Long>()
}