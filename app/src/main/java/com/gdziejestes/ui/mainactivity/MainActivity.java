package com.gdziejestes.ui.mainactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.gdziejestes.R;
import com.gdziejestes.data.inmemory.request.Contacts;
import com.gdziejestes.model.User;
import com.gdziejestes.ui.activities.BaseAuthenticationActivity;
import com.gdziejestes.ui.fragments.contacts.UserViewPagerFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

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

        users = new ArrayList<>();
        bus.post(new Contacts.GetContactRequest());



        viewPager.addOnPageChangeListener(this);



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_main_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        if(users != null && !users.isEmpty()){
            changeViewPagerZoomMap(users.get(0));
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(users != null && !users.isEmpty()){
            User user = users.get(position);
            changeViewPagerZoomMap(user);
        }



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeViewPagerZoomMap(User user) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(user.getCoordinate()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user.getCoordinate(), 15));
    }

    @Subscribe
    public void onGetContactResponse(Contacts.GetContactResponse response){
        users.clear();
        users.addAll(response.Users);

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
    }

}
