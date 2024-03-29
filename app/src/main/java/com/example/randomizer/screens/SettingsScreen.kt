package com.example.randomizer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.data.AppTheme
import com.example.randomizer.screens.common.TextedRadioButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    appTheme: AppTheme,
    onAppThemeChanged: (AppTheme) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.settings),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        )
    },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 10.dp)
            ) {
                ThemeSettings(appTheme = appTheme, onAppThemeChanged = onAppThemeChanged)
                HorizontalDivider(
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    thickness = 1.dp
                )
                LanguageSettings()
                HorizontalDivider(
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    thickness = 1.dp
                )
            }
        })
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ThemeSettings(appTheme: AppTheme, onAppThemeChanged: (AppTheme) -> Unit) {

    val themes = arrayOf(
        stringResource(id = R.string.system_theme),
        stringResource(id = R.string.dark_theme),
        stringResource(id = R.string.light_theme)
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = stringResource(id = R.string.theme))
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextedRadioButton(
                selected = appTheme == AppTheme.System,
                onClick = {
                    if (appTheme != AppTheme.System)
                        onAppThemeChanged(AppTheme.System)
                },
                text = themes[0]
            )
            TextedRadioButton(
                selected = appTheme == AppTheme.Light,
                onClick = {
                    if (appTheme != AppTheme.Light)
                        onAppThemeChanged(AppTheme.Light)

                },
                text = themes[2]
            )
            TextedRadioButton(
                selected = appTheme == AppTheme.Dark,
                onClick = {
                    if (appTheme != AppTheme.Dark)
                        onAppThemeChanged(AppTheme.Dark)
                },
                text = themes[1]
            )

        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LanguageSettings() {
    val languages = arrayOf(
        stringResource(id = R.string.system_theme),
        "English",
        "Русский"
    )
    var selectedLang by remember { mutableStateOf(languages[0]) }

    Text(text = stringResource(id = R.string.language))
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        languages.forEach { lang ->
            TextedRadioButton(
                selected = (lang == selectedLang),
                onClick = { selectedLang = lang },
                text = lang
            )
        }
    }
}