package com.openweather

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class MainActivityTest {

    private lateinit var activity: MainActivity

    @get:Rule
    var activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        activity = activityTestRule.activity

    }

    @Test
    fun buttonNextVerifyIdTest() {
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun onCreate() {
    }
}