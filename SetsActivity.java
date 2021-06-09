package com.example.quizzer;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.quizzer.Adapters.SetsGridViewAdapter;

public class SetsActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0098R.layout.activity_sets);
        setSupportActionBar((Toolbar) findViewById(C0098R.C0101id.setToolBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle((CharSequence) getIntent().getStringExtra("title"));
        ((GridView) findViewById(C0098R.C0101id.gridview)).setAdapter(new SetsGridViewAdapter(getIntent().getIntExtra("sets", 0), getIntent().getStringExtra("title")));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
