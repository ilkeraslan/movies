package com.example.retrofitrxexample

import io.reactivex.Observable
import retrofit2.http.GET

interface KtorEndpoints {

    @GET("/users")
    fun getUsers(): Observable<Users>
}
