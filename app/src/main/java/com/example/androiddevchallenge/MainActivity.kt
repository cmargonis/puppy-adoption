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
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.details.PuppyDetails
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.puppylist.PuppiesListViewModel
import com.example.androiddevchallenge.puppylist.PuppyListContent
import com.example.androiddevchallenge.ui.theme.MyTheme

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
    val navController = rememberNavController()
    val puppies by puppiesListViewModel.puppies.observeAsState(emptyList())
    val navigation by puppiesListViewModel.navigation.observeAsState()

    navigation?.let {

        navController.navigate("profile/${it.puppy.name}/${it.puppy.profilePicture}")
    }
    NavHost(navController, startDestination = "puppyList") {
        composable("puppyList") {
            PuppyListContent(puppies = puppies) {
                puppiesListViewModel.onPuppyClicked(it)
            }
        }
        composable(
            "profile/{name}/{picture}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("picture") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            PuppyDetails(
                backStackEntry.arguments?.getString("name")!!,
                backStackEntry.arguments?.getString("picture")!!
            )
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
