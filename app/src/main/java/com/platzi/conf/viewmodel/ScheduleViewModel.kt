package com.platzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import com.platzi.conf.model.Conferences
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel {
    val firestoreService = FirestoreService()
    var listSchedule = MutableLiveData<List<Conferences>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }
    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object: Callback<List<Conferences>> {
            override fun onSuccess(result: List<Conferences>?) {
                listSchedule.postValue(result)
                processFinished()
        }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }
    fun processFinished(){
        isLoading.value=true
    }
}