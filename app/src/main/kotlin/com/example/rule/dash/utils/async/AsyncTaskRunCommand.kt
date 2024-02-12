package com.example.rule.dash.utils.async

import android.os.AsyncTask
import com.example.rule.app.Dash
class AsyncTaskRunCommand(private val onPreFunc: (() -> Unit)? = null,
                          private val onPostFunc:((result: Boolean) -> Unit)? = null) :  AsyncTask<String, Boolean, Boolean>() {

    @Deprecated("Deprecated in Java")
    override fun onPreExecute() {
        super.onPreExecute()
        onPreFunc?.invoke()
    }

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): Boolean {
        return Dash.root.runCommand(params[0]).result
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        onPostFunc?.invoke(result)
    }


}