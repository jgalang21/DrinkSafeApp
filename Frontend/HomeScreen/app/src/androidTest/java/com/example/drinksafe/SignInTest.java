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
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
public class SignInTest {
    @Rule
    public IntentsTestRule<SignIn> intentsTestRule =
            new IntentsTestRule<>(SignIn.class);

/*    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }*/

    @Test
    public void checkSignUpBtn() {
        onView(withId(R.id.SignUpbtn)).perform(click());

        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(SignUp.class.getName())));
        System.out.println(SignUp.class.getName());

    }
    @Test
    public void checkLoginTest(){
        onView(withId(R.id.etemail)).perform(typeText("BigHAAS"));
        onView(withId(R.id.etpass)).perform(typeText("password123"));
        onView(withId(R.id.btnLogin)).perform(click());

        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(Home.class.getName())));
        System.out.println(Home.class.getName());
    }
    /*@Test
    public void checkLoginTestAdmin(){
        onView(withId(R.id.etemail)).perform(typeText("Admin"));
        onView(withId(R.id.etpass)).perform(typeText("123Abc"));
        onView(withId(R.id.btnLogin)).perform(click());

        intended(allOf(toPackage("com.example.drinksafe"), hasComponent(Home.class.getName())));
    }*/
    @Test
    public void checkButtons(){
        onView(withId(R.id.btnLogin)).check(matches(isClickable()));
        onView(withId(R.id.SignUpbtn)).check(matches(isClickable()));
    }
    @Test
    public void checkHints(){
        onView(withId(R.id.etpass)).check(matches(withHint("password")));
        onView(withId(R.id.etemail)).check(matches(withHint("Email")));
    }
}
