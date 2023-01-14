package com.geekbrains.tests


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityEspressoRecordTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityEspressoRecordTest() {
        val searchEditText = onView(
            allOf(
                withId(R.id.searchEditText),
                isDisplayed()
            )
        )
        searchEditText.perform(replaceText("ik0n"), closeSoftKeyboard(), click())

        val searchButton = onView(
            allOf(
                withId(R.id.searchButton), withText("search"),
                isDisplayed()
            )
        )
        searchButton.perform(click())

        val mainTotalCountTextView = onView(
            allOf(
                withId(R.id.totalCountTextView), withText("Number of results: 5"),
                isDisplayed()
            )
        )
        mainTotalCountTextView.check(matches(withText("Number of results: 5")))

        val toDetailsActivityButton = onView(
            allOf(
                withId(R.id.toDetailsActivityButton), withText("to details"),
                isDisplayed()
            )
        )
        toDetailsActivityButton.perform(click())

        val detailsTotalCountTextView = onView(
            allOf(
                withId(R.id.totalCountTextView), withText("Number of results: 5"),
                isDisplayed()
            )
        )
        detailsTotalCountTextView.check(matches(withText("Number of results: 5")))

        val incrementButton = onView(
            allOf(
                withId(R.id.incrementButton), withText("+"),
                isDisplayed()
            )
        )
        incrementButton.perform(click())

        detailsTotalCountTextView.check(matches(withText("Number of results: 6")))

        val decrementButton = onView(
            allOf(
                withId(R.id.decrementButton), withText("-"),
                isDisplayed()
            )
        )
        decrementButton.perform(click(), click(), click())

        detailsTotalCountTextView.check(matches(withText("Number of results: 3")))
    }
}
