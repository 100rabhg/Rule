package com.example.rule.dash.services.calls

import com.example.rule.dash.di.PerService
import com.example.rule.dash.services.base.InterfaceInteractorService

@PerService
interface InterfaceInteractorCalls<S : InterfaceServiceCalls> : InterfaceInteractorService<S> {

    fun startRecording(phoneNumber: String?, type: Int)
    fun stopRecording()

}