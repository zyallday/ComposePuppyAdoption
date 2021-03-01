/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Puppy(
    var name: String,
    var photo: String,
    var gender: Int,
    var age: String,
    var location: String
) : Parcelable {
    fun getGender(): String {
        return if (gender == 0)
            "Male"
        else
            "Female"
    }

    fun isMale(): Boolean {
        return this.gender == 0
    }

    companion object {

        private val photo = arrayListOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYbaMchO4o91Ab3h-6v3tFrIbaDrBemIVhDg&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBbJhPr13wFYqGejiSbN8V02QPfLUFb6Sh4g&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6pxksXYjR9hCpDNaCX0qTCgDUlbsEsioz0w&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD2b4lTR9Tr8urz1vKptQodL8jm8fRYvkSRw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQ0jF9PmLxsFIbO2jV2LLCQZhf7D-xhtlCTg&usqp=CAU"
        )

        private val name = arrayListOf("Jim", "Green", "Lily", "Five", "Box")
        private val ages = arrayListOf("Baby", "Adult")
        private val location = arrayListOf("SH", "BJ", "HZ", "SZ", "NJ")
        val puppyList = (1..100).map {
            Puppy(
                name = name[Random.nextInt(5)],
                photo = photo[Random.nextInt(5)],
                gender = Random.nextInt(2),
                age = ages[Random.nextInt(1)],
                location = location[Random.nextInt(5)]
            )
        }.toList()
    }
}
