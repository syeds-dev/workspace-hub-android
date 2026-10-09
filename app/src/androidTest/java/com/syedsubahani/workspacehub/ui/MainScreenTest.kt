package com.syedsubahani.workspacehub.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.syedsubahani.workspacehub.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActionBarTest() {
        val textView = onView(
            allOf(
                withText("Workspace Hub"),
                withParent(
                    allOf(
                        withId(com.bumptech.glide.R.id.action_bar),
                        withParent(withId(com.bumptech.glide.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
    }

    @Test
    fun mainContactsLayoutTest() {
        onView(withId(R.id.contactsLinearLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun mainRoomsLayoutTest() {
        onView(withId(R.id.roomsLinearLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun mainContactsTextTest() {
        val textViewContacts = onView(withId(R.id.contactsTextView)).check(matches(isDisplayed()));
        textViewContacts.check(matches(withText("CONTACTS")))
    }

    @Test
    fun mainRoomsTextTest() {
        val textViewrRooms = onView(withId(R.id.roomsTextView)).check(matches(isDisplayed()));
        textViewrRooms.check(matches(withText("ROOMS")))
    }

    @Test
    fun mainContactsButtonTest() {

        onView(withId(R.id.buttonContacts)).check(matches(isEnabled()))
    }

    @Test
    fun mainRoomsButtonTest() {

        onView(withId(R.id.buttonRooms)).check(matches(isEnabled()))
    }
}
