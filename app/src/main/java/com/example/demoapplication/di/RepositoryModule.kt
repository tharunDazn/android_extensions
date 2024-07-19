package com.example.demoapplication.di

import org.koin.dsl.module


val repositoryModule = module {
    factory {  Repository(get()) }
}