package com.example.rule.dash.services.base;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0017\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016J\u000f\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0016\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lcom/example/rule/dash/services/base/BaseInteractorService;", "S", "Lcom/example/rule/dash/services/base/InterfaceService;", "Lcom/example/rule/dash/services/base/InterfaceInteractorService;", "context", "Landroid/content/Context;", "firebase", "Lcom/example/rule/dash/data/rxFirebase/InterfaceFirebase;", "(Landroid/content/Context;Lcom/example/rule/dash/data/rxFirebase/InterfaceFirebase;)V", "service", "Lcom/example/rule/dash/services/base/InterfaceService;", "getContext", "getService", "()Lcom/example/rule/dash/services/base/InterfaceService;", "isNullService", "", "onAttach", "", "(Lcom/example/rule/dash/services/base/InterfaceService;)V", "onDetach", "user", "Lcom/google/firebase/auth/FirebaseUser;", "app_debug"})
public class BaseInteractorService<S extends com.example.rule.dash.services.base.InterfaceService> implements com.example.rule.dash.services.base.InterfaceInteractorService<S> {
    @org.jetbrains.annotations.NotNull()
    private android.content.Context context;
    @org.jetbrains.annotations.NotNull()
    private com.example.rule.dash.data.rxFirebase.InterfaceFirebase firebase;
    @org.jetbrains.annotations.Nullable()
    private S service;
    
    @javax.inject.Inject()
    public BaseInteractorService(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.example.rule.dash.data.rxFirebase.InterfaceFirebase firebase) {
        super();
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    S service) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public S getService() {
        return null;
    }
    
    @java.lang.Override()
    public boolean isNullService() {
        return false;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.content.Context getContext() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.rule.dash.data.rxFirebase.InterfaceFirebase firebase() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public com.google.firebase.auth.FirebaseUser user() {
        return null;
    }
}