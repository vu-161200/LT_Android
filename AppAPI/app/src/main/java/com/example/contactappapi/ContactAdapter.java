package com.example.contactappapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    List<ContactModel> contacts;
    private Context context;

    public ContactAdapter(List<ContactModel> contacts, Context context){
        this.contacts = contacts;
        this.context = context;
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ContactViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_contact, null);

            viewHolder = new ContactViewHolder();
            viewHolder.avatar = view.findViewById(R.id.contact_avatar);
            viewHolder.name = view.findViewById(R.id.contact_name);
            viewHolder.email = view.findViewById(R.id.contact_email);
            viewHolder.phone = view.findViewById(R.id.contact_phone);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ContactViewHolder) view.getTag();
        }
        ContactModel contact = contacts.get(i);
        int id = context.getResources().getIdentifier(contact.getAvatar().getThumb(), "drawable", context.getPackageName());
        viewHolder.avatar.setImageResource(id);
        viewHolder.name.setText(contact.getName());
        viewHolder.email.setText(contact.getEmail());
        viewHolder.phone.setText(contact.getPhone());
        return view;
    }
    static class ContactViewHolder {
        ImageView avatar;
        TextView name;
        TextView email;
        TextView phone;
    }
}
