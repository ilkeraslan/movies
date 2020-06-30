package com.example.retrofitrxexample

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbEndpoints {

    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Observable<PopularMovies>

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") key: String): Observable<PopularMovies>

}