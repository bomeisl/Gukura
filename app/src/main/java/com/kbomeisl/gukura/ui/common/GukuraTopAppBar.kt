package com.kbomeisl.gukura.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.kbomeisl.gukura.ui.models.GukuraTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GukuraTopAppBar(
    gukuraTopAppBar: GukuraTopAppBar
) {
    Surface {
        TopAppBar(
            title = {Text(gukuraTopAppBar.title)},
            modifier = gukuraTopAppBar.modifier,
            navigationIcon = {
                for (i in gukuraTopAppBar.navIcons) {
                    Image(painter = painterResource(i.id),i.textDescription)
                }
            },
            colors = gukuraTopAppBar.colors
        )
    }
}