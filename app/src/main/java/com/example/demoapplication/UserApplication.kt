package com.example.demoapplication

import android.app.Application
import com.example.demoapplication.di.networkModule
import com.example.demoapplication.di.remoteDataSourceModule
import com.example.demoapplication.di.repositoryModule
import com.example.demoapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UserApplication)
            androidLogger()
            modules(networkModule, remoteDataSourceModule, repositoryModule, viewModelModule,
               // dataBaseModule
            )
        }
    }
}