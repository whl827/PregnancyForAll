package itp341.lee.woonghee.finalproject.View;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.net.URI;

import itp341.lee.woonghee.finalproject.Controller.ViewPagerAdapter;
import itp341.lee.woonghee.finalproject.R;

public class UserActivity extends AppCompatActivity {

    private String fragments[] = {"Pregnancy Care", "Posts/Tips by users"};
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //for hamburger
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private ImageView navigation_image;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nv = (NavigationView) findViewById(R.id.navigation_view);
        View view = nv.getHeaderView(0);
        navigation_image = (ImageView) view.findViewById(R.id.navigation_profile_image);

        //set picture of navigation drawer header
        Intent i = getIntent();
        String uri_string = i.getStringExtra("uri"); // get the string
        if (uri_string.equals("null") || uri_string == null) {
            navigation_image.setImageResource(R.drawable.baby);
        } else {
            Uri uri = Uri.parse(i.getStringExtra("uri"));
            try {
                navigation_image.setImageURI(uri);
            } catch (Exception e) {
                navigation_image.setImageResource(R.drawable.baby);
            }
        }
        //set taylayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //set title for toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.pregnancy_care));
        setSupportActionBar(toolbar);

        //for navigation view
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //set up viewpager
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }
    //for hamburger icon
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    //log out of facebook when back if pressed (closing app)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginManager.getInstance().logOut();
    }
}
