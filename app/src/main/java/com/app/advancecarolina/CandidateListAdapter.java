package com.app.advancecarolina;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by neegbeahreeves on 8/20/16.
 */
public class CandidateListAdapter extends ParseQueryAdapter {
    public CandidateListAdapter(Context context) {
        super(context, new QueryFactory<ParseObject>() {
            @Override
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("CandidaeList");
                query.orderByDescending("created_at");
                return query;
            }
        });
    }
    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent){
        if (v == null){
            v = View.inflate(getContext(), R.layout.fragment_candidate_item, null);
        }

        Circle_ImageView headlineImage = (Circle_ImageView) v.findViewById(R.id.CandidatePic);
        ParseFile imageFile = object.getParseFile("candidate_picture");
        if (imageFile != null){
            headlineImage.setParseFile(imageFile);
            headlineImage.loadInBackground();
        }

        TextView headlineTextView = (TextView)v.findViewById(R.id.candidateText);
        headlineTextView.setText(object.getString("candidate_name"));

        TextView dateTextView = (TextView)v.findViewById(R.id.partyText);
        dateTextView.setText(object.getString("candidate_party"));

        super.getItemView(object, v, parent);

        return v;
    }

}