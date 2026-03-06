package com.jovani.sicenet.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SicenetApiService {

    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/accesoLogin"
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun login(@Body xmlLogin: String): Response<String>

    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/getAlumnoAcademicoWithLineamiento"
    )
    @POST("ws/wsalumnos.asmx")
    suspend fun getPerfil(@Body xmlPerfil: String): Response<String>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://tempuri.org/getCargaAcademicaByAlumno")
    @POST("ws/wsalumnos.asmx")
    suspend fun getCargaAcademica(@Body xml: String): Response<String>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://tempuri.org/getAllKardexConPromedioByAlumno")
    @POST("ws/wsalumnos.asmx")
    suspend fun getKardex(@Body xml: String): Response<String>
}