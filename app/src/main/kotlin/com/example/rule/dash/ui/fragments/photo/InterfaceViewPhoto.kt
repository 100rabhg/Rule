package com.example.rule.dash.ui.fragments.photo

import com.example.rule.dash.ui.activities.base.InterfaceView
import com.example.rule.dash.ui.adapters.photoadapter.PhotoRecyclerAdapter
import com.google.firebase.database.DataSnapshot

interface InterfaceViewPhoto : InterfaceView{

    fun setRecyclerAdapter(recyclerAdapter: PhotoRecyclerAdapter)

    fun setValueState(dataSnapshot: DataSnapshot)
    fun setValuePermission(dataSnapshot: DataSnapshot)

}