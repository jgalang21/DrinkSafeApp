package com.example.drinksafe;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.Espresso.*;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions.*;
import android.support.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class ProfileScreenTests {

    @Rule
    public ActivityTestRule<ProfileScreen> activityScenarioRule
            = new ActivityTestRule<>(ProfileScreen.class);

    @Test
    public void checkIntent() {


    }

}
