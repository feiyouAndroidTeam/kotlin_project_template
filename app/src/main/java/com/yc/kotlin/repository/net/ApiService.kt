package com.yc.kotlin.repository.net

import com.yc.kotlin.domin.Config
import com.yc.kotlin.domin.ResultInfo
import com.yc.kotlin.domin.NewsInfoWrapper
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by zhangkai on 2017/11/13.
 */

interface ApiService {
    @Headers("${Config.ISSGIN}: true")
    @POST("news/search")
    fun getNewsInfo(@Body params: Map<String, String?>): Observable<ResultInfo<NewsInfoWrapper>>
}