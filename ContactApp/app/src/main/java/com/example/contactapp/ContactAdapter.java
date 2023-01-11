package com.example.contactapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    List<ContactModel> contacts;

    public ContactAdapter(List<ContactModel> contacts){
        this.contacts = contacts;
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
            view.setTag(viewHolder);
        } else {
            viewHolder = (ContactViewHolder) view.getTag();
        }

        ContactModel contact = contacts.get(i);

        viewHolder.avatar.setText(String.valueOf(contact.getName().charAt(0)));
        viewHolder.name.setText(contact.getName());

        return view;
    }

    static class ContactViewHolder {
        TextView avatar;
        TextView name;
    }
}