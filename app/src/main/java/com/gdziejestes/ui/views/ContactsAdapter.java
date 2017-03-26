package com.gdziejestes.ui.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdziejestes.R;
import com.gdziejestes.model.User;
import com.gdziejestes.ui.activities.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 2017-03-21.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>{

    private BaseActivity activity;
    private LayoutInflater layoutInflater;
    private List<User> users;
    //TODO: Trzeba dokończyć klasę, będzie tutaj viewholder horyzontalny

    public ContactsAdapter(BaseActivity activity, List<User> users) {
        this.activity = activity;
        layoutInflater = activity.getLayoutInflater();
        this.users = new ArrayList<>();
        this.users = users;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);
        ContactsViewHolder viewHolder = new ContactsViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        User user = users.get(position);
        holder.imageAvatar.setImageResource(R.mipmap.ic_launcher);
        holder.displayName.setText(user.getUserName());

        Log.e(getClass().getSimpleName(), "OnBindView " + position);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_contact_avatar)
        ImageView imageAvatar;

        @BindView(R.id.list_item_contact_displayName)
        TextView displayName;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
