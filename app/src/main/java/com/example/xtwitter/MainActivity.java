package com.example.xtwitter;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_profile:
                transaction.replace(R.id.fragment_container, new ProfileFragment());
                break;

            case R.id.nav_Blue:
                transaction.replace(R.id.fragment_container, new BlueFragment());
                break;

            case R.id.nav_BookMark:
                transaction.replace(R.id.fragment_container, new BookmarkFragment());
                break;

            case R.id.nav_List:
                transaction.replace(R.id.fragment_container, new ListFragment());
                break;

            case R.id.nav_forprofessional:
                transaction.replace(R.id.fragment_container, new ProfessionalFragment());
                break;

            case R.id.nav_settingsandsupport:
                SubMenu supportSubMenu = item.getSubMenu();
                if (supportSubMenu != null) {
                    MenuItem settingsItem = supportSubMenu.findItem(R.id.nav_Settings);
                    MenuItem helpCenterItem = supportSubMenu.findItem(R.id.nav_helpcenter);
                    if (settingsItem != null && helpCenterItem != null) {
                        settingsItem.setVisible(!settingsItem.isVisible());
                        helpCenterItem.setVisible(!helpCenterItem.isVisible());
                    }
                }
                break;

            case R.id.nav_helpcenter:
                transaction.replace(R.id.fragment_container, new HelpCenterFragment());
                break;
        }

        transaction.commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
