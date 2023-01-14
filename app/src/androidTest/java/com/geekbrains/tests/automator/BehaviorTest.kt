package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {

    private val uiDevice = UiDevice.getInstance(getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName

    @Before
    fun setup() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityIsStarted() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        Assert.assertNotNull(editText)
    }

    @Test
    fun test_SearchIsPositive() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"

        val searchButton = uiDevice.findObject(By.res(packageName, "searchButton"))
        searchButton.click()

        val changedText =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextView")), TIMEOUT)

        Assert.assertNotNull(changedText.text.toString())
    }

    @Test
    fun test_OpenDetailsScreen() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"

        val searchButton = uiDevice.findObject(By.res(packageName, "searchButton"))
        searchButton.click()

        val changedText =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextView")), TIMEOUT)
        Assert.assertEquals("Number of results: 0", changedText.text.toString())

        val toDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        toDetails.click()

        val changedTextOnDetails =
            uiDevice.wait(Until.findObject(By.res(packageName, "totalCountTextView")), TIMEOUT)
        Assert.assertEquals(changedText.text.toString(), changedTextOnDetails.text.toString())
    }

    @Test
    fun test_DetailsScreenIncrementButton() {

        val toDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        toDetails.click()

        val incrementButton = uiDevice.findObject(By.res(packageName, "incrementButton"))
        incrementButton.click()

        val changedText = uiDevice.findObject(By.res(packageName, "totalCountTextView"))


        Assert.assertEquals("Number of results: 1", changedText.text.toString())
    }

    @Test
    fun test_DetailsScreenDecrementButton() {

        val toDetails = uiDevice.findObject(By.res(packageName, "toDetailsActivityButton"))
        toDetails.click()

        val decrementButton = uiDevice.findObject(By.res(packageName, "decrementButton"))
        decrementButton.click()

        val changedText = uiDevice.findObject(By.res(packageName, "totalCountTextView"))


        Assert.assertEquals("Number of results: -1", changedText.text.toString())
    }

    companion object {
        private const val TIMEOUT = 5000L
    }

}