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

import java.io.Serializable

data class Puppy(
    val id: Int,
    val name: String,
    val profilePicture: String,
    val dogoBiography: String
) :
    Serializable {

    companion object {

        private val biography = """
            Doggo ipsum ur givin me a spook long water shoob floofs bork boof long bois I am bekom fat noodle horse pats, 
            puggorino doggorino wrinkler heckin good boys boof heckin. 
            Much ruin diet smol you are doing me a frighten big ol heck adorable doggo, mlem pupper long water shoob. 
            Woofer blep long water shoob borking doggo doggo smol borking doggo with a long snoot for pats, long woofer he made many woofs pupper.
            Heckin angery woofer h*ck smol borking doggo with a long snoot for pats wow very biscit many pats, 
            boof blep doggo long woofer clouds, porgo borkdrive heckin angery woofer. 
            Puggo big ol pupper many pats aqua doggo, floofs. big ol pupper. blep noodle horse. 
            Tungg very good spot many pats snoot fat boi, heck pupperino fluffer long bois, fat boi floofs mlem. 
            Tungg super chub you are doing me the shock boof shoob, shoober puggorino mlem porgo, 
            the neighborhood pupper most angery pupper I have ever seen woofer. fat boi very jealous pupper shoob. 
            Smol big ol doge clouds heckin angery woofer, borkf you are doing me the shock noodle horse, 
            heckin good boys and girls very good spot boofers. He made many woofs heckin vvv big ol pupper 
            doge blop floofs, shibe borking doggo mlem borking doggo super chub.
        """.trimIndent()

        fun puppyFixtures() = listOf(
            Puppy(
                id = 1,
                name = "Ivan",
                profilePicture = "https://images.dog.ceo/breeds/mastiff-bull/n02108422_754.jpg",
                dogoBiography = biography
            ),
            Puppy(
                id = 2,
                name = "Rocky",
                profilePicture = "https://images.dog.ceo/breeds/akita/An_Akita_Inu_resting.jpg",
                dogoBiography = biography
            ),
            Puppy(
                id = 3,
                name = "Rex",
                profilePicture = "https://images.dog.ceo/breeds/malinois/n02105162_2714.jpg",
                dogoBiography = biography
            ),
            Puppy(
                id = 4,
                name = "Jax",
                profilePicture = "https://images.dog.ceo/breeds/spaniel-cocker/bella2.jpeg",
                dogoBiography = biography
            )
        )
    }
}
