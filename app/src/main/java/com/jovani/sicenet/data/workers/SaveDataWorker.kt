package com.jovani.sicenet.data.workers

import android.content.Context
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jovani.sicenet.data.local.SicenetDatabase
import com.jovani.sicenet.data.local.entities.PerfilEntity

class SaveDataWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val xmlRecibido = inputData.getString("xml_data") ?: return Result.failure()

        return try {
            val db = Room.databaseBuilder(
                applicationContext,
                SicenetDatabase::class.java,
                "sicenet_db"
            ).build()

            val perfilParaGuardar = PerfilEntity(
                matricula = "USUARIO_ACTUAL",
                nombre = "Nombre extraído del XML",
                carrera = "Carrera extraída del XML",
                xmlCompleto = xmlRecibido,
                fechaActualizacion = System.currentTimeMillis()
            )

            db.sicenetDao().insertPerfil(perfilParaGuardar)

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}