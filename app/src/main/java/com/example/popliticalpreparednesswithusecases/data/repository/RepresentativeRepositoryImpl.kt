package com.example.popliticalpreparednesswithusecases.data.repository

import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import com.example.popliticalpreparednesswithusecases.data.model.RepresentativeResponse
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.RepresentativeRemoteDataSource
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.repository.RepresentativeRepository
import retrofit2.Response

class RepresentativeRepositoryImpl(
    private val representativeRemoteDataSource: RepresentativeRemoteDataSource
) : RepresentativeRepository{

    private fun responseToResource(response: Response<RepresentativeResponse>):Resource<RepresentativeResponse>{
        if (response.isSuccessful) {
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedRepresentatives(searchQuery: String): Resource<RepresentativeResponse> {
        return responseToResource(
            representativeRemoteDataSource.getRepresentatives(searchQuery)
        )
    }

}