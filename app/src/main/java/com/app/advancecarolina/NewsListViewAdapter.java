package com.app.advancecarolina;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewGroupCompat;
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


    public NewsListViewAdapter(Context context) {
        super(context, new QueryFactory<ParseObject>() {
            @Override
            public ParseQuery<ParseObject> create() {
                ParseQuery query = new ParseQuery("News");
                query.orderByDescending("created_at");
                return query;
            }
        });
    }

    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent){
        if (v == null){
            v = View.inflate(getContext(), R.layout.fragment_news_item, null);
        }

        ParseImageView headlineImage = (ParseImageView)v.findViewById(R.id.headlineImage);
        ParseFile imageFile = object.getParseFile("headline_pic");
        if (imageFile != null){
            headlineImage.setParseFile(imageFile);
            headlineImage.loadInBackground();
        }

        TextView headlineTextView = (TextView)v.findViewById(R.id.headlineText);
        headlineTextView.setText(object.getString("headline"));

        TextView dateTextView = (TextView)v.findViewById(R.id.dateText);
        dateTextView.setText(object.getCreatedAt().toString());

        super.getItemView(object, v, parent);

        return v;
    }


}
