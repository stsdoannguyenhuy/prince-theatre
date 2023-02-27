package com.interview.princethreatre.repository

import com.interview.princethreatre.util.LogUtil
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DataSource() {
    val filmRepository: FilmRepository
    private val retrofit:Retrofit

    init {
        this.retrofit = Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(provideClient())
            .build()
        filmRepository = retrofit.create(FilmRepository::class.java)
    }

    companion object {
        private const val DEFAULT_TIMEOUT = 30L
        private const val DOMAIN = "https://challenge.lexicondigital.com.au";
        private const val KEY = "Yr2636E6BTD3UCdleMkf7UEdqKnd9n361TQL9An7"
    }

    private fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            .addInterceptor(autoAddApiKeyInterceptor())
            .addInterceptor(CurlInterceptor(logger = object : Logger {
                override fun log(message: String) {
                    LogUtil.d(message)
                }
            }))
            .addInterceptor(retryIfError())
            .build()
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

class NetWorkErrorInterceptor : Interceptor {
    private var count = 3
    override fun intercept(it: Interceptor.Chain): Response {
        val request = it.request()
        var response = it.proceed(request)
        LogUtil.i("COUNT: $count")
        while (!response.isSuccessful && count > 0) {
            count--
            LogUtil.e("ERROR occur: {${response.code}} START RETRY")
            response.close()
            response = it.call().clone().execute()
        }
        return response


    }
}