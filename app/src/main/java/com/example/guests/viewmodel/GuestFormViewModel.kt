package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }

    fun update(guest: GuestModel) {
        repository.update(guest)
    }

    fun get(id: Int){
        repository.getFiltered(id)
    }


}