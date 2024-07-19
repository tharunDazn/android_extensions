package com.example.demoapplication.di

import android.app.Application
import androidx.room.Dao
import androidx.room.Room
import org.koin.dsl.module
//
//fun provideDataBase(application: Application): TradeDataBase =
//     Room.databaseBuilder(
//        application,
//        TradeDataBase::class.java,
//        "table_post"
//    ).
//     fallbackToDestructiveMigration().build()
//
//fun provideDao(database: TradeDataBase): TradeDao = database.getTradeDao()
//
//
//val dataBaseModule= module {
//    single { provideDataBase(get()) }
//    single { provideDao(get()) }
//}


