package com.mikhailovalx.daggerexample

import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Module
interface DaggerModule {
    @Binds
    fun bindDriver(driver: Mikhailov): Driver
}