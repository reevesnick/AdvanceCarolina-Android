package com.app.advancecarolina;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewGroupCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import org.w3c.dom.Text;

/**
 * Created by neegbeahreeves on 8/20/16.
 */
public class NewsListViewAdapter extends ParseQueryAdapter {
    //Context context;
    LayoutInflater inflater;


    public NewsListViewAdapter(Context context) {
        super(context, new QueryFactory<ParseObject>() {
            @Override
            public ParseQuery<ParseObject> create() {
                ParseQuery query = new ParseQuery("News");
                query.orderByDescending("createdAt");
                return query;
            }
        });
    }


    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent){
        if (v == null){
            v = View.inflate(getContext(), R.layout.fragment_news_item, null);
        }

        final ParseImageView headlineImage = (ParseImageView)v.findViewById(R.id.headlineImage);
        final ParseFile imageFile = object.getParseFile("headline_pic");
        if (imageFile != null){
            headlineImage.setParseFile(imageFile);
            headlineImage.loadInBackground();
        }

        TextView headlineTextView = (TextView)v.findViewById(R.id.headlineText);
        headlineTextView.setText(object.getString("headline"));

        TextView dateTextView = (TextView)v.findViewById(R.id.dateText);
        dateTextView.setText(object.getCreatedAt().toString());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewsSingleItemView.class);
                intent.putExtra("headlineText", object.getString("headline"));
                intent.putExtra("dateText",object.getCreatedAt().toString());
                intent.putExtra("articleText",object.getString("article_description"));
                intent.putExtra("headlineImage",imageFile.getUrl());
                getContext().startActivity(intent);

            }
        });

        super.getItemView(object, v, parent);

        return v;
    }


}
