package com.example.drinksafe;

import android.app.Activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class ProfileScreenTests {

    @Rule
    public ActivityTestRule<ProfileScreen> activityScenarioRule
            = new ActivityTestRule<>(ProfileScreen.class);


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

        onView(withId(R.id.save_edit_button)).check(matches(withText("Save")));
    }

    @Rule
    public IntentsTestRule<ProfileScreen> intentsTestRule =
            new IntentsTestRule<>(ProfileScreen.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void checkIntent() {
        onView(withId(R.id.back_button)).perform(click());

        intended(allOf(toPackage("com.example.drinksafe")));
        //, hasAction(Intent.)
    }

}
