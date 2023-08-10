package com.example.x;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    private static final int NAV_PROFILE = R.id.nav_profile;
    private static final int NAV_BLUE = R.id.nav_blue;
    private static final int NAV_BOOKMARK = R.id.nav_bookmark;
    private static final int NAV_LIST = R.id.nav_list;
    private static final int NAV_FOR_PROFESSIONAL = R.id.nav_forprofessional;
    private static final int NAV_SETTINGS = R.id.nav_Settings;
    private static final int NAV_HELP_CENTER = R.id.nav_helpcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.nav_blue:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlueFragment()).commit();
                break;
            case R.id.nav_bookmark:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BookmarkFragment()).commit();
                break;
            case R.id.nav_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment()).commit();
                break;
            case R.id.nav_forprofessional:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ForProfessionalFragment()).commit();
                break;
            case R.id.nav_Settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
            case R.id.nav_helpcenter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HelpCenterFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
