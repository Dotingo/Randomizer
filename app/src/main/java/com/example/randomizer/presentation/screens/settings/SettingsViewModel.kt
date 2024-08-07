package com.example.randomizer.presentation.screens.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.example.randomizer.data.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel: ViewModel() {

    fun changeTheme(currentTheme: AppTheme, appTheme: AppTheme): AppTheme{
        if (currentTheme != appTheme){
            return appTheme
        }
        return appTheme
    }

    private val _selectedLang = MutableStateFlow("")
    val selectedLang: StateFlow<String> = _selectedLang

    fun initializeLanguage(initialLang: String) {
        if (_selectedLang.value.isBlank()) {
            _selectedLang.value = initialLang
        }
    }

    fun changeLanguage(lang: String, systemLocale: String) {
        _selectedLang.value = lang
        changeLocales(
            when (_selectedLang.value) {
                "English" -> "en"
                "Русский" -> "ru"
                else -> systemLocale
            }
        )
    }

    private fun changeLocales(localeString: String) {
        val locale = LocaleListCompat.forLanguageTags(localeString)
        AppCompatDelegate.setApplicationLocales(locale)
    }
}