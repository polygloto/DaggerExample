package com.mikhailovalx.daggerexample

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object DaggerModuleObject {
    @Provides
    @JvmStatic
    fun getBoschCylinder(): Cylinder = BoschCylinder()

    @Provides
    @JvmStatic
    fun getColor(): Color = Color("")

    @Provides
    @Named("blueColor")
    @JvmStatic
    fun getColorBlue(): Color {
        return Color("blue")
    }

    @JvmStatic
    @Named("redColor")
    @Provides
    fun getColorRed(): Color = Color("red")
}