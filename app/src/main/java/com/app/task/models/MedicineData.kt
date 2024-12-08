package com.app.task.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MedicineData(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val dose: String,
    val strength: String,
)
