package com.example.rule.dash.services.base

import com.example.rule.dash.di.component.ServiceComponent
import io.reactivex.disposables.Disposable

interface InterfaceService {

    fun getComponent(): ServiceComponent?

    fun addDisposable(disposable: Disposable)

    fun clearDisposable()

}