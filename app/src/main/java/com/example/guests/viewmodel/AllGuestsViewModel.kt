package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application){

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val allGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = allGuests

    fun getAll(){
        allGuests.value = repository.getAll()
    }
}