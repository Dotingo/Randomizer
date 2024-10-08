package com.dotingo.randomizer.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dotingo.randomizer.data.local.entities.NameEntity

@Dao
interface NamesDao {
    @Query("SELECT * FROM names WHERE language = :lang")
    suspend fun getAllNames(lang: String): List<NameEntity>

    @Query("SELECT * FROM names WHERE gender = :gen AND language = :lang")
    suspend fun getNamesByGender(gen: String, lang: String): List<NameEntity>

    @Query("SELECT * FROM names WHERE region = :reg AND language = :lang")
    suspend fun getAllNamesByRegion(reg: String, lang: String): List<NameEntity>

    @Query("SELECT * FROM names WHERE gender = :gen AND region = :reg AND language = :lang")
    suspend fun getAllNamesByAll(gen: String, reg: String, lang: String): List<NameEntity>
}