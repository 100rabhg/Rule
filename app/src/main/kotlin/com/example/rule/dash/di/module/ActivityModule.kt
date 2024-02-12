package com.example.rule.dash.di.module

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.login.InteractorLogin
import com.example.rule.dash.ui.activities.login.InterfaceInteractorLogin
import com.example.rule.dash.ui.activities.login.InterfaceViewLogin
import com.example.rule.dash.ui.activities.mainparent.InteractorMainParent
import com.example.rule.dash.ui.activities.mainparent.InterfaceInteractorMainParent
import com.example.rule.dash.ui.activities.mainparent.InterfaceViewMainParent
import com.example.rule.dash.ui.activities.register.InteractorRegister
import com.example.rule.dash.ui.activities.register.InterfaceInteractorRegister
import com.example.rule.dash.ui.activities.register.InterfaceViewRegister
import com.example.rule.dash.ui.fragments.calls.InteractorCalls
import com.example.rule.dash.ui.fragments.calls.InterfaceInteractorCalls
import com.example.rule.dash.ui.fragments.calls.InterfaceViewCalls
import com.example.rule.dash.ui.fragments.photo.InteractorPhoto
import com.example.rule.dash.ui.fragments.photo.InterfaceInteractorPhoto
import com.example.rule.dash.ui.fragments.photo.InterfaceViewPhoto
import com.example.rule.dash.ui.fragments.keylog.InteractorKeys
import com.example.rule.dash.ui.fragments.keylog.InterfaceInteractorKeys
import com.example.rule.dash.ui.fragments.keylog.InterfaceViewKeys
import com.example.rule.dash.ui.fragments.maps.InteractorMaps
import com.example.rule.dash.ui.fragments.maps.InterfaceInteractorMaps
import com.example.rule.dash.ui.fragments.maps.InterfaceViewMaps
import com.example.rule.dash.ui.fragments.message.InteractorMessage
import com.example.rule.dash.ui.fragments.message.InterfaceInteractorMessage
import com.example.rule.dash.ui.fragments.message.InterfaceViewMessage
import com.example.rule.dash.ui.fragments.notifications.InteractorNotifyMessage
import com.example.rule.dash.ui.fragments.notifications.InterfaceInteractorNotifyMessage
import com.example.rule.dash.ui.fragments.notifications.InterfaceViewNotifyMessage
import com.example.rule.dash.ui.fragments.recording.InteractorRecording
import com.example.rule.dash.ui.fragments.recording.InterfaceInteractorRecording
import com.example.rule.dash.ui.fragments.recording.InterfaceViewRecording
import com.example.rule.dash.ui.fragments.social.InteractorSocial
import com.example.rule.dash.ui.fragments.social.InterfaceInteractorSocial
import com.example.rule.dash.ui.fragments.social.InterfaceViewSocial
import com.google.android.gms.maps.SupportMapFragment
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideContext(): Context = activity.applicationContext

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    fun provideSupportFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    fun provideSupportMapFragment(): SupportMapFragment = SupportMapFragment.newInstance()

    @Provides
    fun provideLayoutManager(context: Context): LinearLayoutManager = LinearLayoutManager(context)

    @Provides
    @PerActivity
    fun provideInterfaceInteractorMain(interactorParent: InteractorMainParent<InterfaceViewMainParent>): InterfaceInteractorMainParent<InterfaceViewMainParent> = interactorParent

    @Provides
    @PerActivity
    fun provideInterfaceInteractorLogin(interactor: InteractorLogin<InterfaceViewLogin>): InterfaceInteractorLogin<InterfaceViewLogin> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorRegister(interactor: InteractorRegister<InterfaceViewRegister>): InterfaceInteractorRegister<InterfaceViewRegister> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorMaps(interactor: InteractorMaps<InterfaceViewMaps>): InterfaceInteractorMaps<InterfaceViewMaps> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorCalls(interactor: InteractorCalls<InterfaceViewCalls>): InterfaceInteractorCalls<InterfaceViewCalls> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorKeys(interactor: InteractorKeys<InterfaceViewKeys>): InterfaceInteractorKeys<InterfaceViewKeys> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorMessage(interactor: InteractorMessage<InterfaceViewMessage>): InterfaceInteractorMessage<InterfaceViewMessage> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorPhoto(interactor: InteractorPhoto<InterfaceViewPhoto>): InterfaceInteractorPhoto<InterfaceViewPhoto> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorSocial(interactor: InteractorSocial<InterfaceViewSocial>): InterfaceInteractorSocial<InterfaceViewSocial> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorRecording(interactor: InteractorRecording<InterfaceViewRecording>): InterfaceInteractorRecording<InterfaceViewRecording> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorNotify(interactor: InteractorNotifyMessage<InterfaceViewNotifyMessage>): InterfaceInteractorNotifyMessage<InterfaceViewNotifyMessage> = interactor


}