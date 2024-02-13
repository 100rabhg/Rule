package com.example.rule.dash.ui.adapters.smsadapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.example.rule.dash.R
import com.example.rule.dash.data.model.Sms
import com.example.rule.dash.ui.adapters.baseadapter.BaseAdapter
import com.example.rule.dash.utils.ConstFun.firebaseOptions
import com.example.rule.dash.utils.Consts.TAG
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.jakewharton.rxbinding2.view.RxView
import com.pawegio.kandroid.e
import com.pawegio.kandroid.inflateLayout

class SmsRecyclerAdapter(private val query: Query) : BaseAdapter<Sms, SmsViewHolder>(firebaseOptions(query)) {

    private lateinit var interfaceSmsAdapter: InterfaceSmsAdapter

    fun setFilter(filter:String){
        startFilter()
        if (filter=="") updateOptions(firebaseOptions(query))
        else updateOptions(firebaseOptions(query,filter,"smsAddress","smsBody"))
    }

    override fun startFilter() {
        interfaceSmsAdapter.successResult(result = false,filter = true)
    }

    override fun onDataChanged() {
        if (getSnapshots().size == 0) interfaceSmsAdapter.successResult(false)
        else interfaceSmsAdapter.successResult(true)
    }

    override fun onError(e: DatabaseError) {
        interfaceSmsAdapter.failedResult(e)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SmsViewHolder =
            SmsViewHolder(p0.context.inflateLayout(R.layout.item_sms))

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: SmsViewHolder, position: Int, model: Sms) {
        holder.bind(model)
        val key = getRef(position).key
        holder.isSelected(key!!, model.type!!)

        RxView.clicks(holder.itemClick).subscribe({
            interfaceSmsAdapter.onItemClick(key,position)
        },{ e(TAG, it.message.toString()) })

        RxView.longClicks(holder.itemClick).subscribe({
            interfaceSmsAdapter.onItemLongClick(key,position)
        }, { e(TAG, it.message.toString()) })
    }

    fun onRecyclerAdapterListener(interfaceSmsAdapter: InterfaceSmsAdapter) {
        this.interfaceSmsAdapter = interfaceSmsAdapter
    }

}