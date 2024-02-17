package com.example.rule.dash.ui.adapters.keysadapter

import com.example.rule.dash.ui.adapters.baseadapter.InterfaceAdapter

interface InterfaceKeysAdapter : InterfaceAdapter{
    fun onItemLongClick(key:String, position:Int)
}