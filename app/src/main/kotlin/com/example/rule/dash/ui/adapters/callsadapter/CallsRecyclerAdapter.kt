package com.example.rule.dash.ui.adapters.callsadapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.example.rule.dash.R
import com.example.rule.dash.data.model.Calls
import com.example.rule.dash.ui.adapters.baseadapter.BaseAdapter
import com.example.rule.dash.utils.ConstFun.firebaseOptions
import com.example.rule.dash.utils.Consts.TAG
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.jakewharton.rxbinding2.view.RxView
import com.pawegio.kandroid.e
import com.pawegio.kandroid.inflateLayout
class CallsRecyclerAdapter(private val query: Query) : BaseAdapter<Calls, CallsViewHolder>(firebaseOptions(query)) {

    private lateinit var interfaceCallsAdapter: InterfaceCallsAdapter

    fun setFilter(filter:String){
        startFilter()
        if (filter=="") updateOptions(firebaseOptions(query))
        else updateOptions(firebaseOptions(query,filter,"contact","phoneNumber"))
    }

    override fun startFilter() {
        interfaceCallsAdapter.successResult(result = false, filter = true)
    }

    override fun onDataChanged() {
        if (getSnapshots().size == 0) interfaceCallsAdapter.successResult(false)
        else interfaceCallsAdapter.successResult(true)
    }

    override fun onError(e: DatabaseError) {
        interfaceCallsAdapter.failedResult(e)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsViewHolder =
            CallsViewHolder(parent.context.inflateLayout(R.layout.item_calls, parent, false), parent.context)

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CallsViewHolder, position: Int, model: Calls) {
        holder.bind(getItem(position))

        val key = getRef(position).key

        holder.isSelectedItem(key!!)

        RxView.longClicks(holder.itemClick).subscribe({
            interfaceCallsAdapter.onLongClick(key, "", "",position)
        }, { e(TAG, it.message.toString()) })

    }

    fun onRecyclerAdapterListener(interfaceCallsAdapter: InterfaceCallsAdapter) {
        this.interfaceCallsAdapter = interfaceCallsAdapter
    }

}