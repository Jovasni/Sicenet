package com.jovani.sicenet.data.remote

import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // Configuración del gestor de cookies para que guarde la sesión de SICENET
    private val cookieManager = CookieManager().apply {
        setCookiePolicy(CookiePolicy.ACCEPT_ALL)
    }

    // Creamos el cliente HTTP que usará las cookies
    private val okHttpClient = OkHttpClient.Builder()
        .cookieJar(JavaNetCookieJar(cookieManager))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val apiService: SicenetApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://sicenet.surguanajuato.tecnm.mx/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create()) // Para leer XML como String
            .build()
            .create(SicenetApiService::class.java)
    }
}