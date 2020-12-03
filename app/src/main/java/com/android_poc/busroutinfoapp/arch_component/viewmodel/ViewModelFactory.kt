package com.android_poc.busroutinfoapp.arch_component.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory:ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BusInfoViewModel::class.java)){
            return BusInfoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class");
    }

}