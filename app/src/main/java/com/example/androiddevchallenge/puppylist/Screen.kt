package com.example.androiddevchallenge.puppylist

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import dev.chrisbanes.accompanist.coil.CoilImage

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