package com.lekciya.flowcorutines.DB
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,  // id  автоинкрементируемый
    val name: String,
    val description: String
)