package com.gdziejestes.ui.mainactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.gdziejestes.R;

import com.gdziejestes.core.listener.ViewPagerUserListener;
import com.gdziejestes.core.services.DeleteToken;
import com.gdziejestes.core.services.DeleteTokenService;
import com.gdziejestes.core.services.RefreshToken;
import com.gdziejestes.model.User;
import com.gdziejestes.model.entities.Accounts;
import com.gdziejestes.ui.AddFriendActivity;
import com.gdziejestes.ui.BaseAuthenticationActivity;
import com.gdziejestes.ui.LoginActivity;
import com.gdziejestes.util.JsonFormatter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gdziejestes.common.Authorization.AUTH_PREFERENCS_JSON_INFORMATION;
import static com.gdziejestes.util.Constants.FRIEND_ADD;
import static com.gdziejestes.util.Constants.JSON_EXTRAS;

public class MainActivity extends BaseAuthenticationActivity implements OnMapReadyCallback, ViewPager.OnPageChangeListener, MainActivityContract.Views, ViewPagerUserListener, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int STATE_EDITING = 2;
    private static final int STATE_VIEW = 1;
    private int currentState = 1;
    private ActionMode addRequestActionMode;
    private GoogleApiClient googleApiClient;


    private Location location;
    private GoogleMap mMap;
    LocationRequest mLocationRequest;
    private List<User> users;

    @BindView(R.id.activity_main_viewPager)
    ViewPager viewPager;

    @BindView(R.id.activity_main_progressFrame)
    View progressFrame;

    private MainActivityContract.Actions presenter;
    private User currentUser;
    private User mainUser;
    private Toolbar toolbar;

    private JsonFormatter formatter;

    private String jsonInformation;
    private String jsonData;

    @Override
    protected void onSocialCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String[] perms = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

        int permsRequestCode = 200;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(perms, permsRequestCode);
        }

        formatter = new JsonFormatter();
        getNotificationExtras();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new MainActivityPresenter(this);
        users = new ArrayList<>();
        currentUser = null;

        viewPager.addOnPageChangeListener(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_main_map);
        mapFragment.getMapAsync(this);

        changeState(STATE_VIEW);
        jsonInformation = getJsonData();
        mainUser = formatter.getMainUser(jsonInformation);

        Toast.makeText(this, mainUser.getUsername(), Toast.LENGTH_SHORT).show();
        Log.e(MainActivity.class.getSimpleName(), application.getAuth().getPreferences().getString(AUTH_PREFERENCS_JSON_INFORMATION, null));
    }

    private void getNotificationExtras() {
/*        if(getIntent().getExtras().getString("anserw") != null && getIntent().getExtras().getString("anserw").equals("yes") ){
            String information = getIntent().getExtras().getString("anserw");
            String word = information.substring(0,1);
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_logout:
                logout();
                return true;
            case R.id.action_addFriend:
                startActivityForResult(new Intent(this, AddFriendActivity.class), FRIEND_ADD);
                return true;
            case R.id.action_refresh:
                bus.post(new Accounts.RefreshRequest(mainUser.getUsername(), mainUser.getPassword(), application.getAuth().getFirebaseToken(), getLat(), getLng()));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getLat() {
        double lat = location.getLatitude();
        return Double.toString(lat);
    }

    private String getLng() {
        double lng = location.getLongitude();
        return Double.toString(lng);
    }

    private void logout() {
        new DeleteToken(application).execute();
        getMyApp().getAuth().logout();
        //new RefreshToken(application).execute();
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadContacts(jsonInformation);
        notifyAdapterAboutChanged();
    }

    private void notifyAdapterAboutChanged() {
        progressFrame.setVisibility(View.GONE);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (users != null && !users.isEmpty()) {
                    User user = users.get(position);
                    changeViewPagerZoomMap(users.get(position));
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

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();


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
        } else{
            clearMap();
        }

    }

    private void clearMap() {
        mMap.clear();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeViewPagerZoomMap(User user) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(user.getCoordinate()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user.getCoordinate(), 12));
    }


    @Override
    public void showContacts(List<User> users) {
        if(users != null){
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

    public String getJsonData() {
      /*  String json = getIntent().getExtras().getString(JSON_EXTRAS);
        if(json != null && !json.equals("")){
            return json;
        } else {
            return getMyApp().getAuth().getPreferences().getString(AUTH_PREFERENCS_JSON_INFORMATION, null);
        }
        */
        String jsonPreferences = getMyApp().getAuth().getPreferences().getString(AUTH_PREFERENCS_JSON_INFORMATION, null);
        if(jsonPreferences != null && !jsonPreferences.isEmpty()){
            return jsonPreferences;
        } else{
            String json = getIntent().getExtras().getString(JSON_EXTRAS);
            return json;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest= LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
            if(currentUser != null){
                bus.post(new Accounts.SendNotification(currentUser.getUsername(), "@"+mainUser.getUsername()+" chce dodać Cię do znajomych!"));
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            if(currentState != STATE_VIEW){
                changeState(STATE_VIEW);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == FRIEND_ADD){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Wysłano zaproszenie!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Subscribe
    public void getUpdatedInformation(Accounts.RefreshResponse response){
        application.getAuth().setPreferences(response.json);
        presenter.loadContacts(response.json);
    }

}