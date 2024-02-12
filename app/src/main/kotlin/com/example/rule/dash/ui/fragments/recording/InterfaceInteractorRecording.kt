package com.example.rule.dash.ui.fragments.recording

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor

@PerActivity
interface InterfaceInteractorRecording <V : InterfaceViewRecording> : InterfaceInteractor<V> {
    fun stopAudioRecordHolder()
    fun getRecordAudio(time:Long)
    fun valueEventEnableRecording()
    fun valueEventTimerRecording()
}