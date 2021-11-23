package com.example.popliticalpreparednesswithusecases.data.repository.datasourceimpl

import com.example.popliticalpreparednesswithusecases.data.db.ElectionDAO
import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.ElectionLocalDataSource
import kotlinx.coroutines.flow.Flow

class ElectionLocalDataSourceImpl(
    private val electionDAO: ElectionDAO
): ElectionLocalDataSource {

    override suspend fun saveElectionToDB(election: Election) {
        electionDAO.insert(election)
    }

    override fun getSavedElections(): Flow<List<Election>> {
        return electionDAO.getAllElections()
    }

    override suspend fun deleteElectionsFromDB(election: Election) {
        return electionDAO.deleteElection(election)
    }

}