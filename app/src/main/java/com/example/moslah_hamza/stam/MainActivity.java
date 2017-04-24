package com.example.moslah_hamza.stam;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private Boolean logtest;
    private UserLocalStore userLocalStore;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataBaseHandler db = new DataBaseHandler(getApplicationContext());
       // db.createDataBase();

        userLocalStore = new UserLocalStore(this);

//        userLocalStore.clearUserData();
//        userLocalStore.setUserLoggedIn(false);
        logtest = userLocalStore.getUserLoggedIn();
        if (logtest == false) {
            fragment = new LoginFragment();
        } else {
            fragment = new SuppliersFragment();
        }

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //change menu items if user not logged in
        Menu menu = navigationView.getMenu();
        final MenuItem log = menu.findItem(R.id.nav_log);
        final MenuItem sup = menu.findItem(R.id.nav_Sup);
        final MenuItem profile = menu.findItem(R.id.nav_sign);
        if (!logtest) {
            log.setIcon(R.drawable.login);
            log.setTitle("Login");
            profile.setIcon(R.drawable.signin);
            profile.setTitle("Sign up");
            sup.setVisible(false);
        } else {
            profile.setVisible(false);
            log.setIcon(R.drawable.logout);
            log.setTitle("Logout");
        }

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                android.support.v4.app.FragmentTransaction fragmentTransaction;
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    case R.id.nav_log:
                        if (logtest) {
                            userLocalStore.clearUserData();
                            userLocalStore.setUserLoggedIn(false);

                        }
                        finish();
                        startActivity(getIntent());
//                        fragment = new LoginFragment();
//                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.frame, fragment);
//                        fragmentTransaction.commit();

                        return true;

                    case R.id.nav_sign:
                        fragment = new SignUPFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.nav_Sup:
                        fragment = new SuppliersFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    default:
                        return true;
                }

            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }
}
