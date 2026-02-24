package edu.ucne.dragonball.data.remote

import edu.ucne.dragonball.data.remote.dto.PlanetResponseDto
import edu.ucne.dragonball.data.remote.dto.planetDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallApi {

    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<PlanetResponseDto>

    @GET("planets")
    suspend fun getPlanetsByName(
        @Query("name") name: String
    ): Response<List<planetDto>>

    @GET("planets/{id}")
    suspend fun getPlanetDetail(
        @Path("id") id: Int
    ): Response<planetDto>
}