package com.example.drinksafe;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import com.example.drinksafe.ProfileScreen;

import java.util.HashMap;

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

    /*@Test
    public void checkChangesWithEmptyViews() {
        if(withId(R.id.name_edit_box).matches(withText("Edit"))) {
            onView(withId(R.id.save_edit_button)).perform(click());
        }
        onView(withId(R.id.name_edit_box)).perform(clearText());
        onView(withId(R.id.email_edit_box)).perform(clearText());
        onView(withId(R.id.weight_edit_box)).perform(clearText());

        HashMap<String,Boolean> tmp = new HashMap<>();
        ProfileScreen.getChanges(tmp);
    }*/

}
