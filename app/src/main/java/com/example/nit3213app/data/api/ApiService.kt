package com.example.nit3213app.data.api

import com.example.nit3213app.data.model.DashboardResponse
import com.example.nit3213app.data.model.LoginRequest
import com.example.nit3213app.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Retrofit API service interface defining all the network endpoints
 * 
 * This interface declares the HTTP operations that can be performed.
 * Retrofit will generate the implementation of this interface.
 */
interface ApiService {
    
    /**
     * Authenticate user with username and password
     * 
     * @param loginRequest Contains username and password
     * @return LoginResponse containing the keypass
     */
    @POST("sydney/auth")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    
    /**
     * Fetch dashboard data using keypass
     * 
     * @param keypass The authentication key returned from login
     * @return DashboardResponse containing list of entities and total count
     */
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): Response<DashboardResponse>
} 