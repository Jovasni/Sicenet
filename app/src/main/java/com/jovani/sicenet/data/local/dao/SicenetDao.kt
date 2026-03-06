package com.jovani.sicenet.data.local.dao

import androidx.room.*
import com.jovani.sicenet.data.local.entities.PerfilEntity

@Dao
interface SicenetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerfil(perfil: PerfilEntity)

    @Query("SELECT * FROM perfil_alumno LIMIT 1")
    suspend fun getPerfil(): PerfilEntity?
}