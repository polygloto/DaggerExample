package com.mikhailovalx.daggerexample

import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider


class Car @Inject constructor(private var engine: Provider<Engine>, private var door: Door, var driver: Driver){
    var key: Key? = null
        @Inject set

    @Inject
    @Named("redColor")
    lateinit var color: Color

    fun startCar(){
        engine.get().start()
    }

}

class Door @Inject constructor() {
    @Named("blueColor")
    @Inject
    lateinit var color: Color
}

class Key @Inject constructor()