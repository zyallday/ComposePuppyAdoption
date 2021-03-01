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
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.screen.DetailScreen
import com.example.androiddevchallenge.ui.theme.MyTheme

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val KEY_PARAM_PUPPY = "com.example.androiddevchallenge.DetailActivity.PUPPY"

        fun navigateToDetail(context: Context, puppy: Puppy) {
            Intent(context, DetailActivity::class.java).run {
                putExtra(KEY_PARAM_PUPPY, puppy)
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                DetailScreen(
                    intent.getParcelableExtra<Puppy>(KEY_PARAM_PUPPY)!!,
                    onBackPress = { onBackPressed() }
                ) {
                }
            }
        }
    }
}
