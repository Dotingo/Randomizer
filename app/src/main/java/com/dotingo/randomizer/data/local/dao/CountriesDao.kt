package com.dotingo.randomizer.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dotingo.randomizer.data.local.entities.CountriesEntity

@Dao
interface CountriesDao {
    @Query("SELECT * FROM countries WHERE language = :lang ")
    suspend fun getAllCountries(lang: String): List<CountriesEntity>
}