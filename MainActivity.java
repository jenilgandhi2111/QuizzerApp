package com.example.quizzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button bookMarkButton;
    private Button startQuizButton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0098R.layout.activity_main);
        this.startQuizButton = (Button) findViewById(C0098R.C0101id.startQuizBtn);
        this.bookMarkButton = (Button) findViewById(C0098R.C0101id.bookMarksBtn);
        this.startQuizButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        });
        this.bookMarkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, BookmarkActivity.class));
            }
        });
    }
}
