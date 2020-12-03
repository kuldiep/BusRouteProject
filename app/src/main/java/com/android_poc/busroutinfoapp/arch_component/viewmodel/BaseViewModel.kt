package com.android_poc.busroutinfoapp.arch_component.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.android_poc.busroutinfoapp.arch_component.BusRoutInfoRepository

abstract  class BaseViewModel : ViewModel() {
    private var repoObj : BusRoutInfoRepository = BusRoutInfoRepository.getBusRouteInfoRepositoryInstance()

    open fun getBusRouteInfoRepository(): BusRoutInfoRepository {
        return repoObj
    }

}