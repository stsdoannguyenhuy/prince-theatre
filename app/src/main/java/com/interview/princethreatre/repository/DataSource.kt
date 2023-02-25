package com.interview.princethreatre.repository

import com.google.gson.Gson
import com.interview.princethreatre.util.LogUtil
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DataSource() {
    public val filmRepository: FilmRepository

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(provideClient())
            .build()
        filmRepository = retrofit.create(FilmRepository::class.java)
    }

    companion object {
        public const val DEFAULT_TIMEOUT = 30L
        public const val DOMAIN = "https://challenge.lexicondigital.com.au";
        public const val KEY = "Yr2636E6BTD3UCdleMkf7UEdqKnd9n361TQL9An7"
    }

    fun provideClient(): OkHttpClient {
        val gson = Gson()

        val builder = OkHttpClient.Builder().apply {
            connectTimeout(DataSource.DEFAULT_TIMEOUT, TimeUnit.SECONDS)

            addInterceptor(autoAddApiKeyInterceptor())

            // add curl logging
            addInterceptor(CurlInterceptor(logger = object : Logger {
                override fun log(message: String) {
                    LogUtil.d(message)
                }
            }))
            addInterceptor(retryIfError())
        }

        return builder.build()
    }

    private fun autoAddApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder().apply {
                addHeader("x-api-key", KEY)
            }
            chain.proceed(request.build())
        }
    }

    private fun retryIfError(): Interceptor {
        return NetWorkErrorInterceptor()
    }
}

class NetWorkErrorInterceptor: Interceptor{
    var count =3
    override fun intercept(it: Interceptor.Chain): Response {
        val request = it.request()
        var response = it.proceed(request)
        while (!response.isSuccessful && count>0){
            count--
            LogUtil.e("ERROR occur: {${response.code}} START RETRY")
            response.close()
            response = it.call().clone().execute()
        }
        return response
//        if(response.isSuccessful){
//            return response
//        } else{
//
//            if(count>0){
//                count--
////                response.close()
//                it.call().clone().execute()
//            } else{
//                return response.newBuilder()
//                    .code(500)
//                    .protocol(Protocol.HTTP_2)
//                    .message("Network Error")
//                    .request(it.request())
//                    .build()
//            }
//        }


    }
}