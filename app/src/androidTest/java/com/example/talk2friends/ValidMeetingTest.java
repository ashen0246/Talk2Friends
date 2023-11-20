package com.example.talk2friends;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ValidMeetingTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void validMeetingCreateTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.enterEmail), withText("USC Email"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.enterEmail), withText("USC Email"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.enterEmail), withText("USC Email"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.enterEmail), withText("USC Email"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("alshen@usc.edu"));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.enterEmail), withText("alshen@usc.edu"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.enterPassword), withText("Password"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("123123"));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.enterPassword), withText("123123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText7.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.nextPageButton), withText("Continue"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction compatButton = onView(
                allOf(withId(R.id.createButton), withText("Create"),
                        isDisplayed()));
        compatButton.perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /////////////

        ViewInteraction compatButton2 = onView(
                allOf(withId(R.id.topicEditText), withText(""),
                        isDisplayed()));
        compatButton2.perform(click());

        ViewInteraction compatButton3 = onView(
                allOf(withId(R.id.topicEditText), withText(""),
                        isDisplayed()));
        compatButton3.perform(replaceText("USG Elections"));

        ViewInteraction compatButton4 = onView(
                allOf(withId(R.id.topicEditText), withText("USG Elections"),
                        isDisplayed()));
        compatButton4.perform(closeSoftKeyboard());

        ///////////////

        ViewInteraction compatButton5 = onView(
                allOf(withId(R.id.timeEditText), withText(""),
                        isDisplayed()));
        compatButton5.perform(click());

        ViewInteraction compatButton6 = onView(
                allOf(withId(R.id.timeEditText), withText(""),
                        isDisplayed()));
        compatButton6.perform(replaceText("3:30pm"));

        ViewInteraction compatButton7 = onView(
                allOf(withId(R.id.timeEditText), withText("3:30pm"),
                        isDisplayed()));
        compatButton7.perform(closeSoftKeyboard());

        ///////////////

        ViewInteraction compatButton8 = onView(
                allOf(withId(R.id.locationZoomEditText), withText(""),
                        isDisplayed()));
        compatButton8.perform(click());

        ViewInteraction compatButton9 = onView(
                allOf(withId(R.id.locationZoomEditText), withText(""),
                        isDisplayed()));
        compatButton9.perform(replaceText("Tables outside Cava"));

        ViewInteraction compatButton10 = onView(
                allOf(withId(R.id.locationZoomEditText), withText("Tables outside Cava"),
                        isDisplayed()));
        compatButton10.perform(closeSoftKeyboard());

        //////////////

        ViewInteraction compatButton1 = onView(
                allOf(withId(R.id.createButton), withText("Create"),
                        isDisplayed()));
        compatButton1.perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(withId(R.id.meetingsPageLabel), withText("Meetings"),
                        isDisplayed()));
        textView.check(matches(withText("Meetings")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
