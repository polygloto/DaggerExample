package com.mikhailovalx.daggerexample

import javax.inject.Inject

class Fuel @Inject constructor() {
    val fuelType = if (BuildConfig.DEBUG) {
        "benzine"
    } else {
        "diesel"
    }
}