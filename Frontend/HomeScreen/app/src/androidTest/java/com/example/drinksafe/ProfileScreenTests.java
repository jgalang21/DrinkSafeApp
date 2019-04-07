package com.example.drinksafe;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class ProfileScreenTests {

    @Rule
    public ActivityTestRule<ProfileScreen> activityScenarioRule
            = new ActivityTestRule<>(ProfileScreen.class);

    @Test
    public void checkIntent() {


    }

    @Test
    public void checkFieldStatesOnStart() {
        onView(withId(R.id.name_edit_box)).check(matches(not(isEnabled())));
        onView(withId(R.id.email_edit_box)).check(matches(not(isEnabled())));
        onView(withId(R.id.weight_edit_box)).check(matches(not(isEnabled())));
        onView(withId(R.id.inches_spin)).check(matches(not(isEnabled())));
        onView(withId(R.id.feet_spin)).check(matches(not(isEnabled())));
        onView(withId(R.id.sex_spin)).check(matches(not(isEnabled())));

        onView(withId(R.id.save_edit_button)).check(matches(withText("Edit")));
    }

    @Test
    public void checkFieldStatesOnClick() {
        onView(withId(R.id.save_edit_button)).perform(click());

        onView(withId(R.id.name_edit_box)).check(matches(isEnabled()));
        onView(withId(R.id.email_edit_box)).check(matches(isEnabled()));
        onView(withId(R.id.weight_edit_box)).check(matches(isEnabled()));
        onView(withId(R.id.inches_spin)).check(matches(isEnabled()));
        onView(withId(R.id.feet_spin)).check(matches(isEnabled()));
        onView(withId(R.id.sex_spin)).check(matches(isEnabled()));

        onView(withId(R.id.save_edit_button)).check(matches(withText("Edit")));
    }


}
