package com.example.rule.dash.ui.fragments.notifications

import com.example.rule.dash.ui.activities.base.InterfaceView
import com.example.rule.dash.ui.adapters.notifyadapter.NotifyMessageRecyclerAdapter
import com.google.firebase.database.DataSnapshot

interface InterfaceViewNotifyMessage : InterfaceView {

    fun setRecyclerAdapter(recyclerAdapter: NotifyMessageRecyclerAdapter)
    fun setValueState(dataSnapshot: DataSnapshot)

}