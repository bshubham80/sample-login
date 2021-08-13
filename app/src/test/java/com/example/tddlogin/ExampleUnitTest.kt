package com.example.tddlogin

import android.widget.Button
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    private var activity: MainActivity? = null
    private var loginButton: Button? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        loginButton = activity?.login
    }

    @Test
    fun setUserName() {
        activity?.setUsername("")

        assertTrue("Button is disable when username is empty", !loginButton?.isEnabled!!)
    }

    @Test
    fun setPassword() {
        activity?.setPassword("")

        assertTrue("Button is disable when password is empty", !loginButton?.isEnabled!!)
    }
}