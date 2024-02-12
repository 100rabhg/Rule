package com.example.rule.dash.di.module;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\u0006H\u0007J\b\u0010\n\u001a\u00020\u000bH\u0007J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000bH\u0007\u00a8\u0006\u0016"}, d2 = {"Lcom/example/rule/dash/di/module/FirebaseModule;", "", "()V", "provideDatabaseReference", "Lcom/google/firebase/database/DatabaseReference;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "provideFirebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "provideFirebaseDatabase", "provideFirebaseStorage", "Lcom/google/firebase/storage/FirebaseStorage;", "provideInterfaceFirebase", "Lcom/example/rule/dash/data/rxFirebase/InterfaceFirebase;", "context", "Landroid/content/Context;", "auth", "dataRef", "stoRef", "Lcom/google/firebase/storage/StorageReference;", "provideStorageReference", "storage", "app_debug"})
public final class FirebaseModule {
    
    public FirebaseModule() {
        super();
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.rule.dash.data.rxFirebase.InterfaceFirebase provideInterfaceFirebase(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.database.DatabaseReference dataRef, @org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.StorageReference stoRef) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.auth.FirebaseAuth provideFirebaseAuth() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.database.FirebaseDatabase provideFirebaseDatabase() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.storage.FirebaseStorage provideFirebaseStorage() {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.database.DatabaseReference provideDatabaseReference(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.storage.StorageReference provideStorageReference(@org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.FirebaseStorage storage) {
        return null;
    }
}