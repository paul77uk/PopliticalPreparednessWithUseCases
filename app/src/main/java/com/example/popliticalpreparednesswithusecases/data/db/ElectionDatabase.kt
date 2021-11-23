package com.example.popliticalpreparednesswithusecases.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.popliticalpreparednesswithusecases.data.model.Election

@Database(
    entities = [Election::class],
    version = 1,
    exportSchema = false
)
abstract class ElectionDatabase: RoomDatabase() {

    abstract  fun getElectionsDAO(): ElectionDAO

}