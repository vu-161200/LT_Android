package com.example.filemanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    List<ItemModel> items;
    Context context;

    public ItemAdapter(List<ItemModel> items, Context context){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
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
                    .inflate(R.layout.layout_item, null);
            viewHolder = new ContactViewHolder();
            viewHolder.icon = view.findViewById(R.id.item_icon);
            viewHolder.name = view.findViewById(R.id.item_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ContactViewHolder) view.getTag();
        }

        ItemModel contact = items.get(i);

        // Get ID of icon in drawable folder
        int id = context.getResources().getIdentifier(
                contact.getIcon(),
                "drawable",
                context.getPackageName()
        );
        viewHolder.icon.setImageResource(id);
        viewHolder.name.setText(contact.getName());

        return view;
    }

    static class ContactViewHolder {
        ImageView icon;
        TextView name;
    }
}
