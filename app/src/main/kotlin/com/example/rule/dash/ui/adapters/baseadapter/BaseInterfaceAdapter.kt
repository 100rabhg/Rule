package com.example.rule.dash.ui.adapters.baseadapter

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleObserver
import com.firebase.ui.database.ChangeEventListener
import com.firebase.ui.database.ObservableSnapshotArray
import com.google.firebase.database.DatabaseReference

interface BaseInterfaceAdapter<T> : ChangeEventListener, LifecycleObserver {

    fun startListening()
    fun stopListening()
    fun startFilter()
    fun getSnapshots(): ObservableSnapshotArray<T>
    @NonNull
    fun getItem(position: Int): T
    fun getRef(position: Int): DatabaseReference

}