package com.example.rule.dash.ui.adapters.smsadapter

import com.example.rule.dash.ui.adapters.basedapter.InterfaceAdapter

interface InterfaceSmsAdapter : InterfaceAdapter{
    fun onItemClick(keySms:String,position:Int)
    fun onItemLongClick(keySms:String,position:Int)
}