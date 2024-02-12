package com.example.rule.dash.utils.checkForegroundApp

import android.content.Context

interface CheckDetector {

    fun getForegroundPostLollipop(context: Context): String?
    fun getForegroundPreLollipop(context: Context): String?

}