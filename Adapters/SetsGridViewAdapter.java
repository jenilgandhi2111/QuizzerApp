package com.example.quizzer.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.quizzer.C0098R;
import com.example.quizzer.QuestionsActivity;

public class SetsGridViewAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public String category;
    private int sets = 0;

    public SetsGridViewAdapter(int sets2, String category2) {
        this.sets = sets2;
        this.category = category2;
    }

    public int getCount() {
        return this.sets;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(C0098R.layout.setitem, parent, false);
        } else {
            view = convertView;
        }
        ((TextView) view.findViewById(C0098R.C0101id.numView)).setText(String.valueOf(position + 1));
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent questionIntent = new Intent(parent.getContext(), QuestionsActivity.class);
                questionIntent.putExtra("SET_NO", position);
                questionIntent.putExtra("category", SetsGridViewAdapter.this.category);
                parent.getContext().startActivity(questionIntent);
            }
        });
        return view;
    }
}
