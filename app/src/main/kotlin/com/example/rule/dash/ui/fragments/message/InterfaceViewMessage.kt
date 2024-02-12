package com.example.rule.dash.ui.fragments.message

import com.example.rule.dash.ui.activities.base.InterfaceView
import com.example.rule.dash.ui.adapters.smsadapter.SmsRecyclerAdapter

interface InterfaceViewMessage : InterfaceView {

    fun setRecyclerAdapter(smsRecyclerAdapter: SmsRecyclerAdapter)

}