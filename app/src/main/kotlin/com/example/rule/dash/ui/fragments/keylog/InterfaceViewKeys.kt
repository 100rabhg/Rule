package com.example.rule.dash.ui.fragments.keylog

import com.example.rule.dash.ui.activities.base.InterfaceView
import com.example.rule.dash.ui.adapters.keysadapter.KeysRecyclerAdapter
import com.google.firebase.database.DataSnapshot

interface InterfaceViewKeys : InterfaceView {

    fun setValueState(dataSnapshot: DataSnapshot)
    fun setRecyclerAdapter(recyclerAdapter: KeysRecyclerAdapter)

}