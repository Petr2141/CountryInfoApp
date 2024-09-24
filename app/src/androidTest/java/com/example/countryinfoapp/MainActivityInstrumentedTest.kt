package com.example.countryinfoapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.countryinfoapp.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityInstrumentedTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testLoadingIndicator() {
        onView(withId(R.id.ac_main_pb_loading)).check(matches(isDisplayed()))
    }
}