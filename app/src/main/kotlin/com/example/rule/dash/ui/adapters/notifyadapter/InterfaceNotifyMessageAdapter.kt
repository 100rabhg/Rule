package com.example.rule.dash.ui.adapters.notifyadapter

import com.example.rule.dash.ui.adapters.baseadapter.InterfaceAdapter

interface InterfaceNotifyMessageAdapter : InterfaceAdapter{
    fun onItemClick(key: String?, child: String,position:Int)
    fun onItemLongClick(key: String?, child: String,position:Int)
}