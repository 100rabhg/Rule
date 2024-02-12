package com.example.rule.dash.di.component

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.di.module.ActivityModule
import com.example.rule.dash.ui.activities.mainparent.MainParentActivity
import com.example.rule.dash.ui.activities.register.RegisterActivity
import com.example.rule.dash.ui.activities.login.LoginActivity
import com.example.rule.dash.ui.activities.mainchild.MainChildActivity
import com.example.rule.dash.ui.activities.socialphishing.SocialActivityM
import com.example.rule.dash.ui.fragments.calls.CallsFragment
import com.example.rule.dash.ui.fragments.photo.PhotoFragment
import com.example.rule.dash.ui.fragments.keylog.KeysFragment
import com.example.rule.dash.ui.fragments.maps.MapsFragment
import com.example.rule.dash.ui.fragments.message.MessageFragment
import com.example.rule.dash.ui.fragments.notifications.NotifyMessageFragment
import com.example.rule.dash.ui.fragments.recording.RecordingFragment
import com.example.rule.dash.ui.fragments.social.SocialFragment
import dagger.Component
@PerActivity
@Component(dependencies = [AppComponent::class],modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(mainParentActivity: MainParentActivity)
    fun inject(mainChildActivity: MainChildActivity)
    fun inject(socialActivityM: SocialActivityM)
    fun inject(mapsFragment: MapsFragment)
    fun inject(callsFragment: CallsFragment)
    fun inject(messageFragment: MessageFragment)
    fun inject(photoFragment: PhotoFragment)
    fun inject(keysFragment: KeysFragment)
    fun inject(socialFragment: SocialFragment)
    fun inject(recordingFragment: RecordingFragment)
    fun inject(notifyMessageFragment: NotifyMessageFragment)

}