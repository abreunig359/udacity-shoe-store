package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun addShoe(shoe: Shoe) {
        _shoes.value?.let { shoes ->
            _shoes.value = shoes + shoe
        } ?: run {
            _shoes.value = listOf(shoe)
        }
    }
}