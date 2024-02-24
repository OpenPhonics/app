package com.advaitvedant.data

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.advaitvedant.data.repository.FirstAuthRepository
import com.advaitvedant.network.interceptor.AuthInterceptor
import com.advaitvedant.network.retrofit.RetrofitOpNetwork
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.advaitvedant.sharedpref.OpSharedPreferencesFactory
import com.advaitvedant.sharedpref.SessionManagerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runTest


import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var subject: FirstAuthRepository
    @Before
    fun setup(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val session = SessionManagerImpl(OpSharedPreferencesFactory.sessionPreferences(appContext))
        val auth = AuthInterceptor(session)
        val network = RetrofitOpNetwork(auth)
        subject = FirstAuthRepository(
            source = network,
            sessionManager = session
        )
    }
    @Test
    fun login() = runTest {
        launch { val data = subject.login("random")
            assertEquals(true, data)
            subject.isLoggedIn.collectLatest {
                assertEquals(false, it)
            }
        }
    }
}