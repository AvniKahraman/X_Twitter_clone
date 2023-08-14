package com.example.x;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Toolbar toolbar;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false); // Hide default title
            actionBar.setDisplayHomeAsUpEnabled(true); // Show back button if needed
        }


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId== R.id.nav_home )
                {
                    openFragment(new ForyouFragment());
                    return true;

                } else if (itemId == R.id.nav_search) {
                    openFragment(new SearchFragment());
                    return true;

                } else if (itemId == R.id.nav_comm) {

                    openFragment(new CommunityFragment());
                    return true;


                } else if (itemId==  R.id.nav_notification) {
                    openFragment(new NotificationFragment());
                    return  true;

                }else if (itemId == R.id.nav_message){
                    openFragment(new MessageFragment());
                    return true;
                }


                return false;
            }
        });
        fragmentManager = getSupportFragmentManager();



        ViewPager viewPager = findViewById(R.id.viewPager);
        FragmentsAdapter fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentsAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       int itemId = item.getItemId();
       if(itemId == R.id.bottom_profile){
            openFragment(new ProfileFragment());
       } else if (itemId == R.id.bottom_blue) {
           openFragment(new BlueFragment());

       } else if (itemId == R.id.bottom_bookmark) {
        openFragment(new BookmarkFragment());
       } else if (itemId == R.id.bottom_list) {
           openFragment(new ListFragment());
       }else if (itemId == R.id.profilePhoto){
           openFragment(new ProfileFragment());
       }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    private  void openFragment(Fragment fragment)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    public void onProfilePhotoClick(View view) {
        openFragment(new ProfileFragment());
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
