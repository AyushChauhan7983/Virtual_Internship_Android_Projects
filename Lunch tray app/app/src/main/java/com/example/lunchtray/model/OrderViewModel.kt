package com.example.lunchtray.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.DataSource
import java.text.NumberFormat

const val TAG = "OrderViewModel"
private const val TAX_RATE = 0.08
class OrderViewModel : ViewModel() {

    // Map of menu items
    val menuItems = DataSource.menuItems

    // Default values for items prices
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    // Default tax rate
    private val taxRate = 0.08

    // Entree for the order
    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    // Side for the order
    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    // Accompaniment for the order
    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    // Subtotal cost for the order
    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Total cost of the order
    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Tax for the order
    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax) {
        NumberFormat.getCurrencyInstance().format(it)
    }


    /**
     * Set the entree for the order
     * */
    fun setEntree(entree: MenuItem) {
        // if _entree.value is not null, set the previous entree price to the current entree price.
        if (_entree.value != null) {
            previousEntreePrice = _entree.value?.price!!
            //_entree.value!!.price = previousEntreePrice
        }

        // if _subtotal.value is not null subtract the previous entree price from the current
        //  subtotal value. This ensures that we only charge for the currently selected entree.
        if (_subtotal.value != null) {
            _subtotal.value = _subtotal.value?.minus(previousEntreePrice)
        }
        // set the current entree value to the menu item corresponding to the passed in string
        _entree.value = entree
        Log.d(TAG, "Name : ${_entree.value}")

        // update the subtotal to reflect the price of the selected accompaniment.
        //_entree.value?.price?.let { updateSubtotal(it) }
        // Update total subtotal
        updateSubtotal(_entree.value?.price!!)
    }

    /**
     * Set the side for the order*/
    fun setSide(side: MenuItem) {
        // if _entree.value is not null, set the previous entree price to the current entree price.
        if (_side.value != null) {
            previousSidePrice = _side.value?.price!!
        }

        // if _subtotal.value is not null subtract the previous entree price from the current
        //  subtotal value. This ensures that we only charge for the currently selected entree.
        if (_subtotal.value != null) {
            _subtotal.value = _subtotal.value?.minus(previousSidePrice)
        }
        // set the current entree value to the menu item corresponding to the passed in string
        _side.value = side
        Log.d(TAG, "Name : $side")

        // update the subtotal to reflect the price of the selected accompaniment.
        //_entree.value?.price?.let { updateSubtotal(it) }
        // Update total subtotal
        updateSubtotal(_side.value?.price!!)
    }

    /**
     * Set the accompaniment for the order
     * */
    fun setAccompaniment(accompaniment: MenuItem) {
        // if _entree.value is not null, set the previous entree price to the current entree price.
        if (_accompaniment.value != null) {
            previousAccompanimentPrice = _accompaniment.value?.price!!
        }

        // if _subtotal.value is not null subtract the previous entree price from the current
        //  subtotal value. This ensures that we only charge for the currently selected entree.
        if (_subtotal.value != null) {
            _subtotal.value = _subtotal.value?.minus(previousAccompanimentPrice)
        }
        // set the current entree value to the menu item corresponding to the passed in string
        _accompaniment.value = accompaniment
        Log.d(TAG, "Name : ${_accompaniment.value}")

        // update the subtotal to reflect the price of the selected accompaniment.
        //_entree.value?.price?.let { updateSubtotal(it) }
        // Update total subtotal
        updateSubtotal(_accompaniment.value?.price!!)
    }

    /**
     * Update subtotal value
     * */
    private fun updateSubtotal(itemPrice: Double) {
        if (_subtotal.value != null)
            _subtotal.value = _subtotal.value?.plus(itemPrice)
        else
            _subtotal.value = _subtotal.value?.minus(itemPrice)
        calculateTaxAndTotal()
    }

    /** Calculate the text and the total of the order*/
    private fun calculateTaxAndTotal() {
        _tax.value = TAX_RATE
        _total.value = _subtotal.value?.plus(TAX_RATE)
    }

    /**
     * Reset all values pretraining to the order
     * */

    fun resetOrder() {
        _subtotal.value = 0.0
        _total.value = 0.0
        _entree.value = null
        _side.value = null
        _accompaniment.value = null
    }
}

