package com.app.task.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.task.models.MedicineData


@Database(entities = [MedicineData::class], version = 13, exportSchema = false)
abstract class MedicineDataDB : RoomDatabase() {
    abstract fun getMedicineDataDAO() : MedicineDAO
}