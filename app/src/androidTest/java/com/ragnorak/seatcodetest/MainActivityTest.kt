package com.ragnorak.seatcodetest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ragnorak.seatcodetest.resources.CURRENT_POSITION_ID
import com.ragnorak.seatcodetest.resources.PLATEAU_SIZE_X_ID
import com.ragnorak.seatcodetest.resources.PLATEAU_SIZE_Y_ID
import com.ragnorak.seatcodetest.resources.POSITION_HISTORY_ID
import com.ragnorak.seatcodetest.resources.ROVER_DIRECTION_ID
import com.ragnorak.seatcodetest.resources.ROVER_MOVEMENTS_LEFT
import com.ragnorak.seatcodetest.resources.ROVER_MOVEMENTS_MOVE
import com.ragnorak.seatcodetest.resources.ROVER_MOVEMENTS_RIGHT
import com.ragnorak.seatcodetest.resources.ROVER_POSITION_X_ID
import com.ragnorak.seatcodetest.resources.ROVER_POSITION_Y_ID
import com.ragnorak.seatcodetest.resources.SEND_BUTTON_ID
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testInitialUIState() {
        composeTestRule.onNodeWithTag(PLATEAU_SIZE_X_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(PLATEAU_SIZE_Y_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ROVER_POSITION_X_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ROVER_POSITION_Y_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ROVER_DIRECTION_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_LEFT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_RIGHT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_MOVE).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SEND_BUTTON_ID).assertIsDisplayed()

        composeTestRule.onNodeWithTag(CURRENT_POSITION_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(POSITION_HISTORY_ID).assertIsDisplayed()
    }

    @Test
    fun testSendMovementUpdatesCurrentPosition() {

        composeTestRule.onNodeWithTag(PLATEAU_SIZE_X_ID).performTextInput("5")
        composeTestRule.onNodeWithTag(PLATEAU_SIZE_Y_ID).performTextInput("5")
        composeTestRule.onNodeWithTag(ROVER_POSITION_X_ID).performTextInput("1")
        composeTestRule.onNodeWithTag(ROVER_POSITION_Y_ID).performTextInput("2")
        composeTestRule.onNodeWithTag(ROVER_DIRECTION_ID).performTextInput("N")

        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_LEFT).performClick()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_MOVE).performClick()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_LEFT).performClick()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_MOVE).performClick()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_LEFT).performClick()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_MOVE).performClick()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_LEFT).performClick()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_MOVE).performClick()
        composeTestRule.onNodeWithTag(ROVER_MOVEMENTS_MOVE).performClick()

        composeTestRule.onNodeWithTag(SEND_BUTTON_ID).performClick()

        composeTestRule.onNodeWithTag(CURRENT_POSITION_ID)
            .assertIsDisplayed()
            .assertTextContains("Current position: 1 3 N", ignoreCase = true)
    }
}