package com.example.drinksafe;

import android.app.Activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Instrumentation;


import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.runner.AndroidJUnit4;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.DrawerActions.openDrawer;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class HomeScreenIntentsTests {

    @Rule
    public IntentsTestRule<Home> intentsTestRule =
            new IntentsTestRule<>(Home.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void checkNavBar1() {
        //onView(withId(R.id.drawer_layout)).perform(clickXY(0,0));
        openDrawer(R.id.drawer_layout);
        //onView(allOf(withParent(withId(R.id.toolbar)), isDisplayed())).perform(click());
        //onView(withText("Group Chat")).perform(click());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_chat));
        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(Messaging.class.getName())));
    }

    @Test
    public void checkNavBar2() {

        onView(withId(R.id.nav_drinks)).perform(click());

        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(DrinkSummary.class.getName())));
    }

    @Test
    public void checkNavBar3() {
        onView(withId(R.id.nav_log_out)).perform(click());

        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(SignIn.class.getName())));

    }

    @Test
    public void checkNavBar5() {
        onView(withId(R.id.nav_party)).perform(click());

        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(Party.class.getName())));

    }

    @Test
    public void checkNavBar6() {
        onView(withId(R.id.nav_profile)).perform(click());

        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(ProfileScreen.class.getName())));

    }
}
