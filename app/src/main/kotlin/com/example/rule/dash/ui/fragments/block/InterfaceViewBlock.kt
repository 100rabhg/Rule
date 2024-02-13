package com.example.rule.dash.ui.fragments.block

import com.example.rule.dash.ui.activities.base.InterfaceView
import com.example.rule.dash.ui.adapters.blockadapter.BlockRecyclerAdapter

interface InterfaceViewBlock : InterfaceView {
    fun setRecyclerAdapter(recyclerAdapter: BlockRecyclerAdapter)

}