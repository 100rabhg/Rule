package com.example.rule.dash.utils.checkForegroundApp

import android.content.Context
import com.pawegio.kandroid.runOnUiThread

class CheckApp(private val context: Context, private val action: (app: String?) -> Unit) {

    private val detector: CheckDetector = CheckForegroundApp()

    private var timeout:Long = 1000

    private val thread: Thread = Thread {
        while (true) {
            try {
                Thread.sleep(timeout)
                startHandler()
            } catch (e: InterruptedException) {
                break
            }
        }
    }

    fun setTimeout(time: Long): CheckApp {
        this.timeout = time
        return this
    }

    fun start(): CheckApp {
        thread.start()
        return this
    }

    fun stop() {
        thread.interrupt()
    }

    private fun startHandler() {
        runOnUiThread {
            action(detector.getForegroundPostLollipop(context))
        }
    }

}