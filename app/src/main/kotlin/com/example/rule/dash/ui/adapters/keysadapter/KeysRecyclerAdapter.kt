package com.example.rule.dash.ui.adapters.keysadapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.example.rule.dash.R
import com.example.rule.dash.data.model.KeyData
import com.example.rule.dash.ui.adapters.baseadapter.BaseAdapter
import com.example.rule.dash.utils.ConstFun.firebaseOptions
import com.example.rule.dash.utils.Consts.TAG
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.jakewharton.rxbinding2.view.RxView
import com.pawegio.kandroid.e
import com.pawegio.kandroid.inflateLayout

class KeysRecyclerAdapter(private val query: Query) : BaseAdapter<KeyData, KeysViewHolder>(firebaseOptions(query)) {

    private lateinit var interfaceKeysAdapter : InterfaceKeysAdapter

    fun setFilter(filter:String){
        startFilter()
        if (filter=="") updateOptions(firebaseOptions(query))
        else updateOptions(firebaseOptions(query,filter,"keyText"))
    }

    override fun startFilter() {
        interfaceKeysAdapter.successResult(result = false, filter = true)
    }

    override fun onDataChanged() {
        if (getSnapshots().size == 0) interfaceKeysAdapter.successResult(false)
        else interfaceKeysAdapter.successResult(true)
    }

    override fun onError(e: DatabaseError) {
        interfaceKeysAdapter.failedResult(e)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): KeysViewHolder =
            KeysViewHolder(p0.context.inflateLayout(R.layout.item_keylog, p0, false))


    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: KeysViewHolder, position: Int, model: KeyData) {
        holder.bind(model)

        val key = getRef(position).key

        holder.isSelected(key!!)

        RxView.longClicks(holder.itemClick).subscribe({
            interfaceKeysAdapter.onItemLongClick(key, position)
        }, { e(TAG, it.message.toString()) })

    }

    fun onRecyclerAdapterListener(interfaceKeysAdapter: InterfaceKeysAdapter){
        this.interfaceKeysAdapter = interfaceKeysAdapter
    }

}