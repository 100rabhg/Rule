package com.example.rule.dash.di.component;

@javax.inject.Singleton()
@dagger.Component(modules = {com.example.rule.dash.di.module.AppModule.class, com.example.rule.dash.di.module.FirebaseModule.class})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&\u00a8\u0006\f"}, d2 = {"Lcom/example/rule/dash/di/component/AppComponent;", "", "getInterfaceFirebase", "Lcom/example/rule/dash/data/rxFirebase/InterfaceFirebase;", "inject", "", "app", "Lcom/example/rule/app/Dash;", "accessibilityDataService", "Lcom/example/rule/dash/services/accessibilityData/AccessibilityDataService;", "notificationService", "Lcom/example/rule/dash/services/notificationService/NotificationService;", "app_debug"})
public abstract interface AppComponent {
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.app.Dash app);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.services.accessibilityData.AccessibilityDataService accessibilityDataService);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    com.example.rule.dash.services.notificationService.NotificationService notificationService);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.rule.dash.data.rxFirebase.InterfaceFirebase getInterfaceFirebase();
}