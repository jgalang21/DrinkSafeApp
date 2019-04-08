package com.example.drinksafe;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.drinksafe.net_utils.Const;
import com.example.drinksafe.app.AppController;


public class Home extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        Intent i;
                        switch(menuItem.getItemId())
                        {
                            case R.id.nav_profile:
                                i = new Intent(Home.this, ProfileScreen.class);
                                startActivity(i);
                                break;
                            case R.id.nav_drinks:
                                i = new Intent(Home.this, Drinks.class);
                                startActivity(i);
                                break;
                            case R.id.nav_party:
                                i = new Intent(Home.this, Party.class);
                                startActivity(i);
                                break;
                            case R.id.nav_chat:
                                i = new Intent(Home.this, Messaging.class);
                                startActivity(i);
                                break;
                            case R.id.nav_map:
                                i = new Intent(Home.this, Map.class);
                                startActivity(i);
                                break;
                            case R.id.nav_log_out:
                                i = new Intent(Home.this, SignIn.class);
                                startActivity(i);
                                break;
                            default:
                                return true;
                        }
                        return true;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NetworkImageView imgView = findViewById(R.id.img);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        // If you are using NetworkImageView
        imgView.setImageUrl(Const.URL_Image, imageLoader);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
