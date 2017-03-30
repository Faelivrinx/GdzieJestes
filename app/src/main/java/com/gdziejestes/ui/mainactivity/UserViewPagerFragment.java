package com.gdziejestes.ui.mainactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdziejestes.R;
import com.gdziejestes.core.listener.ViewPagerUserListener;
import com.gdziejestes.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 26.03.2017.
 */

//TODO: Avatar implement


public class UserViewPagerFragment extends Fragment implements View.OnClickListener {

    private static final String USER_USERNAME = "USER_USERNAME";

    private String userName;

    ViewPagerUserListener listener;

    @BindView(R.id.list_item_contact_displayName)
    TextView displayName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listener = (ViewPagerUserListener) getActivity();

        userName = getArguments().getString(USER_USERNAME);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item_contact, container, false);
        ButterKnife.bind(this, rootView);

        rootView.setOnClickListener(this);
        displayName.setText(userName);


        return rootView;
    }



    public static UserViewPagerFragment newInstance(User user) {
        Bundle arguments = new Bundle();
        //arguments.putString(USER_USERNAME, user.getUserName());
        UserViewPagerFragment fragment = new UserViewPagerFragment();
        fragment.setArguments(arguments);

        return fragment;
    }


    @Override
    public void onClick(View view) {
        listener.onUserClicked();
    }
}
