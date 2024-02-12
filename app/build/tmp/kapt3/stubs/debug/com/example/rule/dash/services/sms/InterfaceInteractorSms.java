package com.example.rule.dash.services.sms;

@com.example.rule.dash.di.PerService()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/example/rule/dash/services/sms/InterfaceInteractorSms;", "S", "Lcom/example/rule/dash/services/sms/InterfaceServiceSms;", "Lcom/example/rule/dash/services/base/InterfaceInteractorService;", "setPushSms", "", "smsAddress", "", "smsBody", "type", "", "app_debug"})
public abstract interface InterfaceInteractorSms<S extends com.example.rule.dash.services.sms.InterfaceServiceSms> extends com.example.rule.dash.services.base.InterfaceInteractorService<S> {
    
    public abstract void setPushSms(@org.jetbrains.annotations.NotNull()
    java.lang.String smsAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String smsBody, int type);
}