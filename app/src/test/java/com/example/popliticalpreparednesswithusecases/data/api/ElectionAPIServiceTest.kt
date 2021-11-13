package com.example.popliticalpreparednesswithusecases.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ElectionAPIServiceTest {

    private lateinit var service: APIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getElections_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("electionresponse.json")
            val responseBody = service.getElections().body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/elections?key=AIzaSyDkg-YrAFUANAgMZKUX3GBIqBG4KOHj-nw")
        }
    }

    @Test
    fun getElections_receivedResponse_correctNumberOfElections() {
        runBlocking {
            enqueueMockResponse("electionresponse.json")
            val responseBody = service.getElections().body()
            val electionList = responseBody!!.elections
            assertThat(electionList.size).isEqualTo(2)
        }
    }

    @Test
    fun getElections_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("electionresponse.json")
            val responseBody = service.getElections().body()
            val electionList = responseBody!!.elections
            val election = electionList[0]
            assertThat(election.name).isEqualTo("VIP Test Election")
            assertThat(election.electionDay).isEqualTo("2025-06-06")
        }
    }

//    "id": "2000",
//    "name": "VIP Test Election",
//    "electionDay": "2025-06-06",
//    "ocdDivisionId": "ocd-division/country:us"


    @After
    fun tearDown() {
        server.shutdown()
    }

}