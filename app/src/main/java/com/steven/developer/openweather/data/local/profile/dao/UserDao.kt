package com.steven.developer.openweather.data.local.profile.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.steven.developer.openweather.data.local.profile.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId: Int): UserEntity?

    @Query("SELECT * FROM users WHERE userName = :userName AND userPassword = :password")
    suspend fun login(userName: String, password: String): UserEntity?

    @Update
    suspend fun updateUser(user: UserEntity)
}