package com.advaitvedant.data

import android.content.Context
import kotlinx.coroutines.test.runTest
import com.advaitvedant.data.repository.FirstAuthRepository
import com.advaitvedant.network.interceptor.AuthInterceptor
import com.advaitvedant.network.retrofit.RetrofitOpNetwork
import com.advaitvedant.sharedpref.OpSharedPreferencesFactory
import com.advaitvedant.sharedpref.SessionManagerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    @Mock
    private lateinit var mockContext: Context
    private lateinit var subject: FirstAuthRepository
    @Before
    fun setup(){
        mockContext = mock<Context>()
        val session = SessionManagerImpl(OpSharedPreferencesFactory.sessionPreferences(mockContext))
        val auth = AuthInterceptor(session)
        val network = RetrofitOpNetwork(auth)
        subject = FirstAuthRepository(
            source = network,
            sessionManager = session
        )
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun logi() = runTest {
        launch { subject.login("random")}
        advanceUntilIdle()
        launch { assertEquals(true, subject.isLoggedIn.first()) }
    }
}