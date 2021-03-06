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
package com.example.androiddevchallenge.puppylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.PuppyRepository
import com.example.androiddevchallenge.model.PuppyRepositoryImpl

class PuppiesListViewModel(puppyRepository: PuppyRepository = PuppyRepositoryImpl()) : ViewModel() {

    private val _puppies = MutableLiveData(puppyRepository.getPuppies())
    val puppies: LiveData<List<Puppy>> = _puppies

    private val _navigation: MutableLiveData<NavigateToDetails> = MutableLiveData()
    val navigation: LiveData<NavigateToDetails> = _navigation

    private val _puppy: MutableLiveData<Puppy> = MutableLiveData()
    val puppy: LiveData<Puppy> = _puppy

    fun onPuppyClicked(puppy: Puppy) {
        _navigation.value = NavigateToDetails(puppy = puppy)
        _puppy.value = puppy
    }
}
