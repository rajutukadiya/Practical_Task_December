package com.app.task.di

import android.content.Context
import androidx.room.Room
import com.app.task.db.MedicineDataDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFakerDB(@ApplicationContext context : Context) : MedicineDataDB {
        return Room.databaseBuilder(context, MedicineDataDB::class.java, "MedicineDB").build()
    }
}