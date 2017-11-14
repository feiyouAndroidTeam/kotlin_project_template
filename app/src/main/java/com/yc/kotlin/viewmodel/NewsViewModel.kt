package com.yc.kotlin.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.yc.kotlin.domin.Config
import com.yc.kotlin.domin.NewsInfoWrapper
import com.yc.kotlin.domin.appDatabase
import com.yc.kotlin.domin.retrofit
import com.yc.kotlin.repository.net.ApiService
import com.yc.kotlin.utils.rxrun
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by zhangkai on 2017/11/13.
 */
class NewsViewModel : ViewModel() {
    val newsInfoDao by lazy {
        appDatabase.newsInfoDao()
    }
    val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    fun getNewsInfo(params: Map<String, String?>) : LiveData<NewsInfoWrapper> {
        var data = MutableLiveData<NewsInfoWrapper>()
        apiService.getNewsInfo(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if(it?.code == Config.HTTP_STAUTS_OK){
                data.value = it.data
                rxrun {
                    newsInfoDao?.insert(it.data.info)
                }
            }
        }, {

        })
        return data
    }

}