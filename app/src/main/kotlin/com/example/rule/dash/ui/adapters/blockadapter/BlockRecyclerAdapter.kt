package com.example.rule.dash.ui.adapters.blockadapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.example.rule.dash.R
import com.example.rule.dash.data.model.Block
import com.example.rule.dash.ui.adapters.baseadapter.BaseAdapter
import com.example.rule.dash.utils.ConstFun.firebaseOptions
import com.example.rule.dash.utils.Consts.TAG
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.jakewharton.rxbinding2.view.RxView
import com.pawegio.kandroid.e
import com.pawegio.kandroid.inflateLayout

class BlockRecyclerAdapter(private val query: Query) :
    BaseAdapter<Block, BlockViewHolder>(firebaseOptions(query)) {

    private lateinit var interfaceBlockAdapter: InterfaceBlockAdapter

    fun setFilter(filter: String) {
        startFilter()
        if (filter == "") updateOptions(firebaseOptions(query))
        else updateOptions(firebaseOptions(query, filter, "number"))
    }

    override fun startFilter() {
        interfaceBlockAdapter.successResult(result = false, filter = true)
    }

    override fun onDataChanged() {
        if (getSnapshots().size == 0) interfaceBlockAdapter.successResult(false)
        else interfaceBlockAdapter.successResult(true)
    }

    override fun onError(e: DatabaseError) {
        interfaceBlockAdapter.failedResult(e)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockViewHolder =
        BlockViewHolder(
            parent.context.inflateLayout(R.layout.item_block, parent, false),
            parent.context
        )

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: BlockViewHolder, position: Int, model: Block) {
        holder.bind(getItem(position))

        val key = getRef(position).key

        holder.isSelectedItem(key!!)

        RxView.clicks(holder.itemClick).subscribe({
            interfaceBlockAdapter.onItemClick(key, position)
        }, { e(TAG, it.message.toString()) })

        RxView.longClicks(holder.itemClick).subscribe({
            interfaceBlockAdapter.onItemLongClick(key, position)
        }, { e(TAG, it.message.toString()) })

    }

    fun onRecyclerAdapterListener(interfaceBlockAdapter: InterfaceBlockAdapter) {
        this.interfaceBlockAdapter = interfaceBlockAdapter
    }

}