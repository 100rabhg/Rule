package com.example.rule.dash.ui.adapters.baseadapter

import com.google.firebase.database.DatabaseError

interface InterfaceAdapter {
    fun successResult(result: Boolean, filter:Boolean = false)
    fun failedResult(error: DatabaseError)
}