package com.syedsubahani.workspacehub.ui.contactscreen


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.syedsubahani.workspacehub.R
import com.syedsubahani.workspacehub.ui.SplashScreen
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ContactDetailsTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SplashScreen::class.java)

    @Test
    fun contactDetailsTest() {
        val appCompatImageButton = onView(
            allOf(
                withId(R.id.buttonRooms), withContentDescription("Rooms"),
                childAtPosition(
                    allOf(
                        withId(R.id.roomsLinearLayout),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        pressBack()

        val appCompatImageButton2 = onView(
            allOf(
                withId(R.id.buttonContacts),
                withContentDescription("There are two buttons here. Above one is contacts"),
                childAtPosition(
                    allOf(
                        withId(R.id.contactsLinearLayout),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val linearLayout = onView(
            allOf(
                withId(R.id.mainContactLinearLayout),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerViewContacts),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        val linearLayout2 = onView(
            allOf(
                withId(R.id.profileLinearLayout),
                withContentDescription("Header having profile picture and name of the Colleague. Below there are more details available like Email ID, Job Title and Favourite color."),
                withParent(
                    allOf(
                        withId(R.id.mainLinearLayout),
                        withContentDescription("This is Details of the selected Contact"),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        linearLayout2.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withText("Email :"),
                withParent(
                    withParent(
                        allOf(
                            withId(R.id.mainLinearLayout),
                            withContentDescription("This is Details of the selected Contact")
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Email :")))

        val textView2 = onView(
            allOf(
                withId(R.id.emailId), withText("Hettie31@gmail.com"),
                withParent(
                    withParent(
                        allOf(
                            withId(R.id.mainLinearLayout),
                            withContentDescription("This is Details of the selected Contact")
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Hettie31@gmail.com")))

        val textView3 = onView(
            allOf(
                withText("Job Title :"),
                withParent(
                    withParent(
                        allOf(
                            withId(R.id.mainLinearLayout),
                            withContentDescription("This is Details of the selected Contact")
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Job Title :")))

        val textView4 = onView(
            allOf(
                withId(R.id.jobTitle), withText("Future Interactions Supervisor"),
                withParent(
                    withParent(
                        allOf(
                            withId(R.id.mainLinearLayout),
                            withContentDescription("This is Details of the selected Contact")
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Future Interactions Supervisor")))

        val textView5 = onView(
            allOf(
                withText("Favorite Color :"),
                withParent(
                    withParent(
                        allOf(
                            withId(R.id.mainLinearLayout),
                            withContentDescription("This is Details of the selected Contact")
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Favorite Color :")))

        val textView6 = onView(
            allOf(
                withId(R.id.favouriteColor), withText("cyan"),
                withParent(
                    withParent(
                        allOf(
                            withId(R.id.mainLinearLayout),
                            withContentDescription("This is Details of the selected Contact")
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("cyan")))

        val textView7 = onView(
            allOf(
                withId(R.id.favouriteColor), withText("cyan"),
                withParent(
                    withParent(
                        allOf(
                            withId(R.id.mainLinearLayout),
                            withContentDescription("This is Details of the selected Contact")
                        )
                    )
                ),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("cyan")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
