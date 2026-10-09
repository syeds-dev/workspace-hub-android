package com.syedsubahani.workspacehub.ui.roomscreen


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RoomsListTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SplashScreen::class.java)

    @Test
    fun roomsListTest() {
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

        val textView = onView(
            allOf(
                withId(R.id.textView), withText("MAX OCCUPANCY"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout2),
                        withParent(withContentDescription("This is the Rooms list screen. There are two columns in the list. The first column is the Maximun occupancy and the second column is the room status. This colum says whether the room is occupied or not."))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("MAX OCCUPANCY")))

        val textView2 = onView(
            allOf(
                withId(R.id.textView2), withText("STATUS"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout2),
                        withParent(withContentDescription("This is the Rooms list screen. There are two columns in the list. The first column is the Maximun occupancy and the second column is the room status. This colum says whether the room is occupied or not."))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("STATUS")))

        val textView3 = onView(
            allOf(
                withId(R.id.roomCapacity), withText("53539"),
                withParent(withParent(withId(R.id.recyclerViewRooms))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("53539")))

        val textView4 = onView(
            allOf(
                withId(R.id.roomStatus), withText("Available"),
                withParent(withParent(withId(R.id.recyclerViewRooms))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Available")))

        val textView5 = onView(
            allOf(
                withId(R.id.roomStatus), withText("Available"),
                withParent(withParent(withId(R.id.recyclerViewRooms))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Available")))
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
