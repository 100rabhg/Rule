package com.example.rule.dash.ui.fragments.block

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor

@PerActivity
interface InterfaceInteractorBlock<V : InterfaceViewBlock> : InterfaceInteractor<V> {
    fun blockNumber(number: String)
}