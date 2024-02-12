package com.example.rule.dash.di.component;

@com.example.rule.dash.di.PerService()
@dagger.Component(dependencies = {com.example.rule.dash.di.component.AppComponent.class}, modules = {com.example.rule.dash.di.module.ServiceModule.class})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lcom/example/rule/dash/di/component/ServiceComponent;", "", "inject", "", "callsService", "Lcom/example/rule/dash/services/calls/CallsService;", "smsService", "Lcom/example/rule/dash/services/sms/SmsService;", "monitorService", "Lcom/example/rule/dash/services/social/MonitorService;", "app_debug"})
public abstract interface ServiceComponent {
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.services.calls.CallsService callsService);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.services.sms.SmsService smsService);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.services.social.MonitorService monitorService);
}