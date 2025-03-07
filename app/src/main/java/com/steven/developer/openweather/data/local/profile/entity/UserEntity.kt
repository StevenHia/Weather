package com.steven.developer.openweather.data.local.profile.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,
    var userName: String,
    var userPassword: String
)
