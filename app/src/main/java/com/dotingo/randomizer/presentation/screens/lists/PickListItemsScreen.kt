package com.dotingo.randomizer.presentation.screens.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dotingo.randomizer.R
import com.dotingo.randomizer.presentation.components.CustomSlider
import com.dotingo.randomizer.presentation.components.GenerateButton
import com.dotingo.randomizer.presentation.components.ResultSection

@Composable
fun PickListItemsScreen(
    paddingValues: PaddingValues,
    items: List<String>,
    listViewModel: ListViewModel
) {
    var randomItemsResult by rememberSaveable {
        mutableStateOf(listOf<String>())
    }

    val canDisplaySlider = items.size > 2
    var slideValue by rememberSaveable { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ResultSection(
            output = randomItemsResult,
            size = 0.7f,
            separator = ", ",
            clipboardText = randomItemsResult.joinToString(", ")
        )

        if (canDisplaySlider) {
            CustomSlider(valueRange = 1..items.size, currentValue = slideValue) { newValue ->
                slideValue = newValue
            }
        }

        GenerateButton(label = stringResource(id = R.string.pick_item)) {
            randomItemsResult = listViewModel.shuffleList(items, slideValue)
        }
    }
}