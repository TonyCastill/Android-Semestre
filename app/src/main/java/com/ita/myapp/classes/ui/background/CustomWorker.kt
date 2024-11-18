package com.ita.myapp.classes.ui.background

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

/**
 * Work Manager
 *
 * An Android Library that provides an
 * efficient way to manage and execute
 * background tasks
 *
 *  This manager garanties that tasks are
 *  executed even if the application exits or
 *  the system rebots, so it provides a persistance
 *
 *  It is designed to handle deferrable and
 *  asynchronous tasks such as network request,
 *  databaase opeartions or File Manipulation
 *
 *  Work Manager 3 different types of persistent
 *  work
 *  -Immediate: Complete soon
 *  -Long Running:
 *  -Deferrable: Schedule task to run them later
 */

/**
 * A background task that waits
 * 10 seconds, logs a sucess message and
 * finish its task
 */
class CustomWorker constructor(
    context: Context,
    workerParameters: WorkerParameters,
    ):CoroutineWorker(context,workerParameters){
        override suspend fun doWork(): Result{
            println("Hello from worker!")
            return Result.success()
        }
}