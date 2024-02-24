package com.advaitvedant.network

import com.advaitvedant.network.request.AuthRequest
import com.advaitvedant.network.retrofit.RetrofitOpNetwork
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ExampleUnitTest {
    private lateinit var subject: RetrofitOpNetwork
    @Before
    fun setUp() {
        subject = RetrofitOpNetwork()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun auth() = runTest {
        val request = AuthRequest("whott")
        launch {
            val login = subject.login(request)
            assertEquals(login, "signup")
        }
    }
}