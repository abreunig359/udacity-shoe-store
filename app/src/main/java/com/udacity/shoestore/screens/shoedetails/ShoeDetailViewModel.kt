package com.udacity.shoestore.screens.shoedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeDetailViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    var size = MutableLiveData<String>()
    var company = MutableLiveData<String>()
    var description = MutableLiveData<String>()
}