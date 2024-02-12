package com.example.rule.dash.ui.fragments.social

import com.example.rule.dash.ui.activities.base.InterfaceView
import com.google.firebase.database.DataSnapshot

interface InterfaceViewSocial : InterfaceView {

    fun setValuePermission(dataSnapshot: DataSnapshot)
    fun successResult(dataSnapshot: DataSnapshot)

}