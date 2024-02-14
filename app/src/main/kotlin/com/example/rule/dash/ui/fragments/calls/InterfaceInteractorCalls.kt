package com.example.rule.dash.ui.fragments.calls

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor

@PerActivity
interface InterfaceInteractorCalls<V : InterfaceViewCalls> : InterfaceInteractor<V>