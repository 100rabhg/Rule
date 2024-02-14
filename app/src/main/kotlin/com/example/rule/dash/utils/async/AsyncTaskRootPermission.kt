package com.example.rule.dash.utils.async

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.rule.app.Dash
import com.example.rule.dash.R
import com.example.rule.dash.utils.ConstFun.alertDialog
@SuppressLint("StaticFieldLeak")
class AsyncTaskRootPermission(private val context: Context,private val result:(result:Boolean)->Unit) : AsyncTask<String, Boolean, Boolean>() {

    private var dialog: SweetAlertDialog? = null

    @Deprecated("Deprecated in Java")
    override fun onPreExecute() {
        super.onPreExecute()
        dialog = context.alertDialog(
            SweetAlertDialog.PROGRESS_TYPE,
            R.string.check_root_access,
            null,
            0
        ) {
            show()
        }
    }

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("Dash.root.obtainPermission()", "com.example.rule.app.Dash")
    )
    override fun doInBackground(vararg params: String?): Boolean = Dash.root.obtainPermission()

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        if (dialog != null) dialog!!.dismiss()
        result(result)
    }


}