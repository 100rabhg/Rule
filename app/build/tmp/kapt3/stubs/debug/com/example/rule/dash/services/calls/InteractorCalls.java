package com.example.rule.dash.services.calls;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0017\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u001a\u0010\u0017\u001a\u00020\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/example/rule/dash/services/calls/InteractorCalls;", "S", "Lcom/example/rule/dash/services/calls/InterfaceServiceCalls;", "Lcom/example/rule/dash/services/base/BaseInteractorService;", "Lcom/example/rule/dash/services/calls/InterfaceInteractorCalls;", "context", "Landroid/content/Context;", "firebase", "Lcom/example/rule/dash/data/rxFirebase/InterfaceFirebase;", "(Landroid/content/Context;Lcom/example/rule/dash/data/rxFirebase/InterfaceFirebase;)V", "contact", "", "dateTime", "fileName", "phoneNumber", "recorder", "Lcom/example/rule/dash/utils/MediaRecorderUtils;", "type", "", "deleteFile", "", "sendFileCall", "setPushName", "startRecording", "stopRecording", "app_debug"})
public final class InteractorCalls<S extends com.example.rule.dash.services.calls.InterfaceServiceCalls> extends com.example.rule.dash.services.base.BaseInteractorService<S> implements com.example.rule.dash.services.calls.InterfaceInteractorCalls<S> {
    @org.jetbrains.annotations.NotNull()
    private com.example.rule.dash.utils.MediaRecorderUtils recorder;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String fileName;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String contact;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String phoneNumber;
    private int type = 0;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String dateTime;
    
    @javax.inject.Inject()
    public InteractorCalls(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.example.rule.dash.data.rxFirebase.InterfaceFirebase firebase) {
        super(null, null);
    }
    
    @java.lang.Override()
    public void startRecording(@org.jetbrains.annotations.Nullable()
    java.lang.String phoneNumber, int type) {
    }
    
    @java.lang.Override()
    public void stopRecording() {
    }
    
    private final void deleteFile() {
    }
    
    private final void sendFileCall() {
    }
    
    private final void setPushName() {
    }
}