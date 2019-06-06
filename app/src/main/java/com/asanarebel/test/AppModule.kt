package com.asanarebel.test

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MainActivity) {
    @Provides
    @Singleton
    fun provideApp() = app
}