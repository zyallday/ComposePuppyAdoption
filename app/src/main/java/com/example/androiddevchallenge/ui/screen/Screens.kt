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
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.Male
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MainScreen(puppyList: List<Puppy>, onClick: (Puppy) -> Unit) {
    MyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(LocalContext.current.getString(R.string.app_name))
                    }
                )
            },
            content = {
                MyApp(
                    puppyList,
                    onClick = onClick
                )
            }
        )
    }
}

// Start building your app here!
@Composable
fun MyApp(lists: List<Puppy>, onClick: ((Puppy) -> Unit)? = null) {
    Surface(color = MaterialTheme.colors.background) {
        val state = rememberLazyListState()
        LazyColumn(state = state) {
            items(items = lists) { item ->
                PuppyListItem(puppy = item, onClick = onClick)
            }
        }
    }
}

@Composable
fun PuppyListItem(puppy: Puppy, modifier: Modifier = Modifier, onClick: ((Puppy) -> Unit)? = null) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.background)
            .clickable(onClick = { onClick?.invoke(puppy) })
            .padding(16.dp),
    ) {

        Box {
            Surface(Modifier.size(50.dp), shape = CircleShape) {
                CoilImage(
                    data = puppy.photo,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Column(modifier = Modifier.padding(34.dp, 34.dp, 0.dp, 0.dp)) {
                Image(
                    if (puppy.isMale()) Icons.Outlined.Male else Icons.Outlined.Female,
                    contentDescription = "",
                    modifier = Modifier.requiredSize(16.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(puppy.name, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(puppy.location, style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun DetailScreen(puppy: Puppy, onBackPress: () -> Unit = { }, onAdoptionClick: () -> Unit = {}) {
    MyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(LocalContext.current.getString(R.string.app_name))
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackPress) {
                            Icon(Icons.Outlined.ArrowBack, contentDescription = null)
                        }
                    }
                )
            },
            content = {
                AdoptionPage(puppy, onAdoptionClick)
            }
        )
    }
}

@Composable
fun AdoptionPage(puppy: Puppy, onAdoptionClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .requiredSize(200.dp)
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
                .shadow(2.dp, RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp),
        ) {
            CoilImage(
                data = puppy.photo,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.requiredHeight(12.dp))
        LabelItem("Name", puppy.name)
        LabelItem(labelDesc = "Gender", labelContent = puppy.getGender())
        LabelItem(labelDesc = "Age", labelContent = puppy.age)
        LabelItem(labelDesc = "Location", labelContent = puppy.location)
        Spacer(modifier = Modifier.requiredHeight(20.dp))
        Button(onClick = onAdoptionClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Adoption")
        }
    }
}

@Composable
fun LabelItem(labelDesc: String, labelContent: String) {
    Row() {
        Text(
            text = labelDesc,
            modifier = Modifier
                .requiredHeight(40.dp)
                .fillMaxWidth(0.5f)
                .padding(0.dp, 0.dp, 10.dp, 0.dp),
            textAlign = TextAlign.End
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = labelContent,
                modifier = Modifier
                    .requiredHeight(40.dp)
                    .fillMaxWidth(0.5f),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start
            )
        }
    }
}
