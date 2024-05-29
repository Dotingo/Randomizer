package com.example.randomizer.presentation.screens.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.presentation.util.Lists

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomListScreen(paddingValues: PaddingValues) {
    val focusManager = LocalFocusManager.current
    var lists by remember {
        mutableStateOf(Lists.originList)
    }
    var query by remember {
        mutableStateOf("")
    }
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Filled.Add, "Add button.") },
            text = { Text(text = "Add list") },
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                query = query,
                onQueryChange = {
                    query = it
                    lists = Lists.search(query)
                },
                onSearch = {
                    focusManager.clearFocus()
                },
                placeholder = { Text(text = stringResource(R.string.search_bar_placeholder)) },
                active = false,
                onActiveChange = {},
                trailingIcon = {
                    if (query.isNotBlank()) {
                        IconButton(onClick = { query = ""
                            lists = Lists.search(query)}) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")
                        }
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .padding(it)
            ) {
                items(lists.size) { index ->
                    Lists(name = lists[index])
                }
            }
        }
    }
}

@Composable
fun Lists(name: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, style = MaterialTheme.typography.titleLarge)
            Text(
                text = "Количество элементов: ${(1..20).random()}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}
