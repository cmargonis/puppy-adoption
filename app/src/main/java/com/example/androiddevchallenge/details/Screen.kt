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
package com.example.androiddevchallenge.details

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.puppylist.PuppiesListViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyDetails(viewModel: PuppiesListViewModel) {
    val puppy by viewModel.puppy.observeAsState()

    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.about_me)) },
                    elevation = 8.dp
                )
            }
        ) {
            LazyColumn(
                Modifier.fillMaxWidth()
            ) {
                item {
                    Surface(
                        elevation = 4.dp,
                        shape = MaterialTheme.shapes.medium.copy(
                            bottomEnd = CornerSize(16.dp),
                            bottomStart = CornerSize(16.dp),
                            topEnd = CornerSize(0.dp),
                            topStart = CornerSize(0.dp)
                        )
                    ) {
                        CoilImage(
                            data = puppy!!.profilePicture,
                            contentDescription = "${puppy!!.name}'s picture",
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            contentScale = ContentScale.FillWidth,
                            loading = {
                                Box(Modifier.matchParentSize()) {
                                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                                }
                            },
                            error = {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_error),
                                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.design_default_color_error)),
                                    modifier = Modifier.size(16.dp),
                                    contentScale = ContentScale.None,
                                    contentDescription = null
                                )
                            },
                            onRequestCompleted = { state ->
                                Log.i("tag", "Load state: $state")
                            }
                        )
                    }
                    Text(
                        text = puppy!!.name,
                        modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = puppy!!.dogoBiography,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}
