package com.vijay.randomcity.workers

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class WelcomeWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val cityName = inputData.getString("city_name") ?: return Result.failure()
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, "Welcome to $cityName", Toast.LENGTH_SHORT).show()
        }
        return Result.success()
    }
}

fun scheduleWelcomeMessage(city: String, context: Context) {
    val data = workDataOf("city_name" to city)
    val request = OneTimeWorkRequestBuilder<WelcomeWorker>()
        .setInitialDelay(5, TimeUnit.SECONDS)
        .setInputData(data)
        .build()
    WorkManager.getInstance(context).enqueue(request)
}