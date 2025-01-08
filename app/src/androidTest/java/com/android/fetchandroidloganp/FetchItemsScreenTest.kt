package com.android.fetchandroidloganp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FetchItemsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testListHeadersAndItems() {
        // Make sure we have lists
        composeTestRule.onNodeWithText("List 1", substring = true)
            .assertIsDisplayed()

        // Tap on List 1 header
        composeTestRule.onNodeWithText("List 1", substring = true)
            .performClick()

        // Check if an item within List 1 is displayed
        composeTestRule.onNodeWithText("Id: 0", substring = true)
            .assertIsDisplayed()
    }
}