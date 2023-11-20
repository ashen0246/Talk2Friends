package com.example.talk2friends;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
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
public class LeaveMeetingTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    // Custom matcher to find a view with text within a parent view by id
    public static Matcher<View> withinId(final int parentId) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with id: " + parentId);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                while (parent instanceof View) {
                    if (((View) parent).getId() == parentId) {
                        return true;
                    }
                    parent = parent.getParent();
                }
                return false;
            }
        };
    }

    @Test
    public void leaveMeetTest() {
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

       try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.scrollView)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(ScrollView.class), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "Scroll ScrollView to the bottom";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ScrollView scrollView = (ScrollView) view;
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        ViewInteraction textView = onView(
                allOf(withId(R.id.createButton), withText("Create"),
                        isDisplayed()));
        textView.check(matches(withText("Create")));

    }

    public static Matcher<View> withTextInParent(final String text, final Matcher<View> parentMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return item instanceof TextView && ((TextView) item).getText().toString().equals(text)
                        && parentMatcher.matches(item.getParent());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text: " + text + " within parent: ");
                parentMatcher.describeTo(description);
            }
        };
    }
    public static Matcher<View> atPosition(final int position) {
        return new TypeSafeMatcher<View>() {
            int counter = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("at position: " + position);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (counter == position) {
                    counter++;
                    return true;
                }
                counter++;
                return false;
            }
        };
    }

    public static Matcher<View> withTextDirectChildOf(final String text, final int parentId) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with text: " + text + " and is a direct child of: " + parentId);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof Button) && !(view instanceof TextView)) {
                    return false;
                }
                TextView textView = (TextView) view;
                if (!textView.getText().toString().equals(text)) {
                    return false;
                }
                ViewParent parent = view.getParent();
                while (parent instanceof View) {
                    if (((View) parent).getId() == parentId) {
                        return true; // The view is a direct child of the parent with the specified ID
                    }
                    parent = parent.getParent();
                }
                return false;
            }
        };
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
