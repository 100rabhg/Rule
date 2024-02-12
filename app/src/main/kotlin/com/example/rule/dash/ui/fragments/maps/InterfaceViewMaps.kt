package com.example.rule.dash.ui.fragments.maps

import com.example.rule.dash.data.model.Location
import com.example.rule.dash.ui.activities.base.InterfaceView
import com.google.firebase.database.DataSnapshot

interface InterfaceViewMaps : InterfaceView {
    fun setLocation(location: Location)
    fun setValueState(dataSnapshot: DataSnapshot)
    fun setValuePermission(dataSnapshot: DataSnapshot)
}