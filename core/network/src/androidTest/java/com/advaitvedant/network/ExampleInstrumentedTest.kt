package com.advaitvedant.network

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.advaitvedant.network.interceptor.AuthInterceptor
import com.advaitvedant.network.retrofit.RetrofitOpNetwork
import com.advaitvedant.sharedpref.OpSharedPreferencesFactory
import com.advaitvedant.sharedpref.SessionManagerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import com.advaitvedant.network.request.AuthRequest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var subject: RetrofitOpNetwork

    @Before
    fun setup(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val session = SessionManagerImpl(OpSharedPreferencesFactory.sessionPreferences(appContext))
        val auth = AuthInterceptor(session)
        subject = RetrofitOpNetwork(auth)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun login() = runTest {
        launch { val data = subject.login(AuthRequest("random"))
//            assertEquals("", data)
        }
        advanceUntilIdle()
//        launch { assertEquals(true, subject.isLoggedIn.first()) }
    }
}