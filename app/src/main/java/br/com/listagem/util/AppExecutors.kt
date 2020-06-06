package br.com.listagem.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor


class AppExecutors(diskIO: Executor, networkIO: Executor, mainThread: Executor) {
    private val diskIO: Executor
    private val networkIO: Executor
    private val mainThread: Executor
    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    class MainThreadExecutor : Executor {
        private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        private const val THREAD_COUNT = 3
    }

    init {
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }
}
