package com.app.task.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.task.models.MedicineData

@Dao
interface MedicineDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedicineData(products: List<MedicineData>)


    @Query("SELECT * FROM MedicineData")
    suspend fun getMedicineData() :  List<MedicineData>


}