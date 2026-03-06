package com.jovani.sicenet.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perfil_alumno")
data class PerfilEntity(
    @PrimaryKey val matricula: String,
    val nombre: String,
    val carrera: String,
    val xmlCompleto: String,
    val fechaActualizacion: Long
)