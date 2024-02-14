package com.example.rule.dash.ui.adapters.callsadapter

import com.example.rule.dash.ui.adapters.baseadapter.InterfaceAdapter

interface InterfaceCallsAdapter : InterfaceAdapter {
    fun onLongClick(keyFileName: String, fileName: String, childName: String,position:Int)
}