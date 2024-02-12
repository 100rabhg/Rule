package com.example.rule.dash.ui.fragments.calls

import com.example.rule.dash.ui.activities.base.InterfaceView
import com.example.rule.dash.ui.adapters.callsadapter.CallsRecyclerAdapter
interface InterfaceViewCalls : InterfaceView {

    fun setRecyclerAdapter(recyclerAdapter: CallsRecyclerAdapter)

}