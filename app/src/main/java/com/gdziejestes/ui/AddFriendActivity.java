package com.gdziejestes.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gdziejestes.R;
import com.gdziejestes.model.User;
import com.gdziejestes.model.entities.Accounts;
import com.gdziejestes.util.JsonFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gdziejestes.common.Authorization.AUTH_PREFERENCS_JSON_INFORMATION;

/**
 * Created by Dominik on 2017-04-19.
 */

public class AddFriendActivity extends BaseAuthenticationActivity implements View.OnClickListener {

    @BindView(R.id.activity_add_friend_etAddFriend)
    EditText etAddFriend;

    @BindView(R.id.activity_add_friend_btnAddFriend)
    Button btnAddFriend;

    JsonFormatter formatter;
    User mainUser;

    @Override
    protected void onSocialCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_friend);
        ButterKnife.bind(this);
        formatter = new JsonFormatter();
        String json = getMyApp().getAuth().getPreferences().getString(AUTH_PREFERENCS_JSON_INFORMATION, null);
        mainUser = formatter.getMainUser(json);
        btnAddFriend.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == btnAddFriend){
            String name = getUsername();
            bus.post(new Accounts.SendNotification(name, mainUser.getUsername()+" chce dodać do znajomych " + name));
            setResult(RESULT_OK);
            finish();
        }
    }

    private String getUsername() {
        return etAddFriend.getText().toString();
    }
}
