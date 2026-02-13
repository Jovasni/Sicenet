package com.jovani.sicenet.data.repository

import com.jovani.sicenet.data.remote.RetrofitClient

class SicenetRepository {
    private val api = RetrofitClient.apiService

    // Función para Autenticación
    suspend fun login(matricula: String, contrasenia: String): String? {
        val xmlBody = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <accesoLogin xmlns="http://tempuri.org/">
                  <strMatricula>$matricula</strMatricula>
                  <strContrasenia>$contrasenia</strContrasenia>
                  <tipoUsuario>ALUMNO</tipoUsuario>
                </accesoLogin>
              </soap:Body>
            </soap:Envelope>
        """.trimIndent()

        val response = api.login(xmlBody)
        return if (response.isSuccessful) response.body() else null
    }

    // Función para Consultar Perfil con la Cookie ya guardada
    suspend fun getPerfil(): String? {
        val xmlBody = """
            <?xml version="1.0" encoding="utf-8"?>
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <getAlumnoAcademicoWithLineamiento xmlns="http://tempuri.org/" />
              </soap:Body>
            </soap:Envelope>
        """.trimIndent()

        val response = api.getPerfil(xmlBody)
        return if (response.isSuccessful) response.body() else null
    }
}