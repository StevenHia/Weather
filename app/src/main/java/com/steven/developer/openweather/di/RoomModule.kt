package com.steven.developer.openweather.di

import android.content.Context
import androidx.room.Room
import com.steven.developer.openweather.data.local.db.WeatherDatabase
import com.steven.developer.openweather.data.local.profile.dao.UserDao
import com.steven.developer.openweather.utils.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, Database.database_name)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: WeatherDatabase): UserDao {
        return appDatabase.userDao()
    }
}