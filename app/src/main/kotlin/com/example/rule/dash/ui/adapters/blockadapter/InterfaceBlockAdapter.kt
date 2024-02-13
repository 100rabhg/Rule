package com.example.rule.dash.ui.adapters.blockadapter

import com.example.rule.dash.ui.adapters.baseadapter.InterfaceAdapter

interface InterfaceBlockAdapter : InterfaceAdapter {
    fun onItemClick(key: String, position: Int)
    fun onItemLongClick(key: String, position: Int)
}