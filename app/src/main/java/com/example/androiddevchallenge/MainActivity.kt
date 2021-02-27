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

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.PuppiesListViewModel
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : AppCompatActivity() {

    private val viewModel: PuppiesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(viewModel)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(puppiesListViewModel: PuppiesListViewModel) {
    val puppies by puppiesListViewModel.puppies.observeAsState(emptyList())

    PuppyListContent(puppies = puppies) {
        puppiesListViewModel.onPuppyClicked(it)
    }
}

@Composable
fun PuppyListContent(puppies: List<Puppy>, onPuppyClicked: (Puppy) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(Modifier.fillMaxWidth()) {
            items(puppies) { puppy ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onPuppyClicked(puppy) }
                ) {
                    CoilImage(
                        data = puppy.profilePicture,
                        contentDescription = "${puppy.name}'s picture",
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp),
                        contentScale = ContentScale.Fit,
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
                    Text(text = puppy.name)
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        PuppyListContent(puppies = Puppy.puppyFixture(), onPuppyClicked = { /*TODO*/ })
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        PuppyListContent(puppies = Puppy.puppyFixture(), onPuppyClicked = { /*TODO*/ })
    }
}
