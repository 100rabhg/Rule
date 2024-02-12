package com.example.rule.dash.utils.hiddenCameraServiceUtils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/example/rule/dash/utils/hiddenCameraServiceUtils/CameraCallbacks;", "", "onCameraError", "", "errorCode", "", "onImageCapture", "imageFile", "Ljava/io/File;", "app_debug"})
public abstract interface CameraCallbacks {
    
    public abstract void onImageCapture(@org.jetbrains.annotations.NotNull()
    java.io.File imageFile);
    
    public abstract void onCameraError(@com.example.rule.dash.utils.hiddenCameraServiceUtils.CameraError.CameraErrorCodes()
    int errorCode);
}