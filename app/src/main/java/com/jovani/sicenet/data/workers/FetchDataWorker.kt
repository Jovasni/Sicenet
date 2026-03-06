package com.jovani.sicenet.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.jovani.sicenet.data.remote.RetrofitClient

class FetchDataWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val response = RetrofitClient.apiService.getPerfil("TU_XML_AQUÍ")

            if (response.isSuccessful) {
                val xmlResult = response.body() ?: ""
                Result.success(workDataOf("xml_data" to xmlResult))
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
}