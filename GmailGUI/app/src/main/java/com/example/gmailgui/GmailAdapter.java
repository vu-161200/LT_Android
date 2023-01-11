package com.example.gmailgui;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.core.graphics.drawable.DrawableCompat;

import java.util.Collections;
import java.util.List;

public class GmailAdapter extends BaseAdapter {
    List<GmailModel> gmails;

    public GmailAdapter(List<GmailModel> gmails){
        this.gmails = gmails;
        Collections.reverse(this.gmails);
    }

    @Override
    public int getCount() {
        return gmails.size();
    }

    @Override
    public Object getItem(int i) {
        return gmails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GmailViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_gmail, null);
            viewHolder = new GmailViewHolder();
            viewHolder.gmail_avatar = view.findViewById(R.id.gmail_avatar);
            viewHolder.gmail_email = view.findViewById(R.id.gmail_email);
            viewHolder.gmail_title = view.findViewById(R.id.gmail_title);
            viewHolder.gmail_content = view.findViewById(R.id.gmail_content);
            viewHolder.gmail_time = view.findViewById(R.id.gmail_time);
            viewHolder.gmail_favourite = view.findViewById(R.id.gmail_favourite);
            view.setTag(viewHolder);
        } else {
            viewHolder = (GmailViewHolder) view.getTag();
        }

        GmailModel gmail = gmails.get(i);

        viewHolder.gmail_avatar.setText(String.valueOf(gmail.getEmail().charAt(0)));
        // Change avatar background
        GradientDrawable gradientDrawable = (GradientDrawable) viewHolder.gmail_avatar.getBackground().mutate();
        gradientDrawable.setColor(gmail.getParseColor());

        viewHolder.gmail_email.setText(gmail.getEmail());
        viewHolder.gmail_title.setText(gmail.getTitle());
        viewHolder.gmail_content.setText(gmail.getContent());
        viewHolder.gmail_time.setText(gmail.getTime());
        viewHolder.gmail_favourite.setChecked(gmail.isFavourite());

        viewHolder.gmail_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gmail.setFavourite(!gmail.isFavourite());
                notifyDataSetChanged();
            }
        });

        return view;
    }

    static class GmailViewHolder {
        TextView gmail_avatar;
        TextView gmail_email;
        TextView gmail_title;
        TextView gmail_content;
        TextView gmail_time;
        CheckBox gmail_favourite;
    }
}