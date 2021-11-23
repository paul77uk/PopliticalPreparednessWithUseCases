package com.example.popliticalpreparednesswithusecases.data.db

import androidx.room.*
import com.example.popliticalpreparednesswithusecases.data.model.Election
import kotlinx.coroutines.flow.Flow

@Dao
interface ElectionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(election: Election)

    @Query("SELECT * FROM elections")
    fun getAllElections(): Flow<List<Election>>

    @Delete
    suspend fun deleteElection(election: Election)

}