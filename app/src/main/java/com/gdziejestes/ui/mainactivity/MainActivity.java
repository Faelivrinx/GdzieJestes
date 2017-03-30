package com.gdziejestes.ui.mainactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.gdziejestes.R;

import com.gdziejestes.core.listener.ViewPagerUserListener;
import com.gdziejestes.model.User;
import com.gdziejestes.ui.BaseAuthenticationActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAuthenticationActivity implements OnMapReadyCallback, ViewPager.OnPageChangeListener, MainActivityContract.Views, ViewPagerUserListener {

    private static final int STATE_EDITING = 2;
    private static final int STATE_VIEW = 1;
    private int currentState = 1;
    private ActionMode addRequestActionMode;

    GestureDetector tapGestureDetector;

    private GoogleMap mMap;
    private List<User> users;

    @BindView(R.id.activity_main_viewPager)
    ViewPager viewPager;

    @BindView(R.id.activity_main_progressFrame)
    View progressFrame;

    private MainActivityContract.Actions presenter;
    private User currentUser;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new MainActivityPresenter(this);
        users = new ArrayList<>();
        currentUser = null;

        //viewPager.addOnPageChangeListener(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_main_map);
        mapFragment.getMapAsync(this);

        //changeState(STATE_VIEW);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadContacts();
        notifyAdapterAboutChanged();
    }

    private void notifyAdapterAboutChanged() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (users != null && !users.isEmpty()) {
                    User user = users.get(position);
                    return UserViewPagerFragment.newInstance(user);
                }
                return null;
            }

            @Override
            public int getCount() {
                return users.size();
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (users != null && !users.isEmpty()) {
            changeViewPagerZoomMap(users.get(0));
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        changeState(STATE_VIEW);
    }

    @Override
    public void onPageSelected(int position) {
        if (users != null && !users.isEmpty()) {
            User user = users.get(position);
            currentUser = user;
            changeViewPagerZoomMap(user);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeViewPagerZoomMap(User user) {
        mMap.clear();
       /* mMap.addMarker(new MarkerOptions().position(user.getCoordinate()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user.getCoordinate(), 15));*/
    }


    @Override
    public void showContacts(List<User> users) {
        if(users != null && !users.isEmpty()){

            this.users.clear();
            this.users.addAll(users);
            notifyAdapterAboutChanged();
            progressFrame.setVisibility(View.GONE);

        }

    }

    @Override
    public void updateGoogleMap(User user) {

    }

    @Override
    public void showSendRequestToolbarOption() {

    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }



    private void changeState(int state) {

        if (state == currentState) {
            return;
        }

        currentState = state;
        if (state == STATE_VIEW) {
            viewPager.setEnabled(true);

            if (addRequestActionMode != null) {
                addRequestActionMode.finish();
                addRequestActionMode = null;
            }

        } else if (state == STATE_EDITING) {
            viewPager.setEnabled(false);

            addRequestActionMode = toolbar.startActionMode(new AddRequestActionCallback());
        } else{
            throw new IllegalArgumentException("Invalid state " + state);
        }

    }

    @Override
    public void onUserClicked() {
        changeState(STATE_EDITING);
    }



    private class AddRequestActionCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            getMenuInflater().inflate(R.menu.activity_menu_contact_request, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            //TODO: use presenter to send request

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            if(currentState != STATE_VIEW){
                changeState(STATE_VIEW);
            }
        }
    }


}