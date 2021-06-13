package com.mikhailovalx.daggerexample

import dagger.Component

@Component(modules = [DaggerModuleObject::class, DaggerModule::class])
interface DaggerComponent{
    fun getCar(): Car
    fun getEngine(): Engine
    fun getFuel(): Fuel

    fun inject(activity: MainActivity)
}