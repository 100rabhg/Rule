package com.example.rule.dash.ui.fragments.message

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor
@PerActivity
interface InterfaceInteractorMessage<V : InterfaceViewMessage> : InterfaceInteractor<V>