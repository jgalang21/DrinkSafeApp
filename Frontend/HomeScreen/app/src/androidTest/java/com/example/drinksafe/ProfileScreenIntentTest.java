package com.example.drinksafe;

import android.app.Activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Instrumentation;

import androidx.test.runner.AndroidJUnit4;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class ProfileScreenIntentTest {
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

        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(Home.class.getName())));
        //, hasAction(Intent.)
    }
}
