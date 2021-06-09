package com.example.quizzer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quizzer.Adapters.BookmarkAdapter;
import com.example.quizzer.Models.QuestionModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    public static final String FILE_NAME = "QUIZZER";
    public static final String KEY_NAME = "QUESTIONS";
    BookmarkAdapter bookmarkAdapter;
    SharedPreferences.Editor editor;
    Gson gson;
    SharedPreferences preferences;
    List<QuestionModel> questionModelList;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0098R.layout.activity_bookmark);
        Toolbar toolbar = (Toolbar) findViewById(C0098R.C0101id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(C0098R.C0101id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.questionModelList = new ArrayList();
        SharedPreferences sharedPreferences = getSharedPreferences("QUIZZER", 0);
        this.preferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.gson = new Gson();
        getBookMarks();
        BookmarkAdapter bookmarkAdapter2 = new BookmarkAdapter(this.questionModelList);
        this.bookmarkAdapter = bookmarkAdapter2;
        recyclerView.setAdapter(bookmarkAdapter2);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        storeBookMarks();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getBookMarks() {
        List<QuestionModel> list = (List) this.gson.fromJson(this.preferences.getString("QUESTIONS", ""), new TypeToken<List<QuestionModel>>() {
        }.getType());
        this.questionModelList = list;
        if (list == null) {
            this.questionModelList = new ArrayList();
        }
    }

    private void storeBookMarks() {
        this.editor.putString("QUESTIONS", this.gson.toJson((Object) this.questionModelList));
        this.editor.commit();
    }
}
