package com.gdziejestes.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gdziejestes.R;
import com.gdziejestes.model.User;
import com.gdziejestes.ui.views.ContactsAdapter;
import com.gdziejestes.ui.views.UserViewPagerFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAuthenticationActivity implements OnMapReadyCallback, ViewPager.OnPageChangeListener {

    private GoogleMap mMap;
    private List<User> users;

    @BindView(R.id.activity_main_viewPager)
    ViewPager viewPager;




    //FragmentActivity
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        users = getSimpleUsers();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                User user = users.get(position);
                return UserViewPagerFragment.newInstance(user);
            }

            @Override
            public int getCount() {
                return users.size();
            }
        });

        viewPager.addOnPageChangeListener(this);



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_main_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng currentUser = users.get(0).getCoordinate();
        mMap.addMarker(new MarkerOptions().position(currentUser));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentUser));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        User user = users.get(position);

        changeViewPagerZoomMap(user);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeViewPagerZoomMap(User user) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(user.getCoordinate()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user.getCoordinate(), 15));
    }

    private List<User> getSimpleUsers() {
        users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName("User " + i);
            user.setPassword("fadsaf");
            user.setAvatarUrl(null);
            user.setEmail("gmil");
            user.setCoordinate(new LatLng(49.5898323 + (0.6*i) , 19.1002473));
            users.add(user);
        }

        return users;
    }
}
