package com.lekciya.flowcorutines.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface DAO {
    @Query("SELECT * FROM persons")
    fun getAllPersons(): List<Person>
    // Возвращаем список книг как Flow, для получения данных в реальном времени
    @Query("SELECT * FROM persons")
    fun getAllPersonsFlow(): Flow<List<Person>>

    @Insert
    fun insertPerson(person: Person)
}