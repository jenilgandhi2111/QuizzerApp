package com.example.quizzer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    Button doneBtn;
    TextView finalScoreView;
    TextView totalQuestions;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0098R.layout.activity_score);
        this.finalScoreView = (TextView) findViewById(C0098R.C0101id.score);
        this.totalQuestions = (TextView) findViewById(C0098R.C0101id.totalQuestions);
        this.doneBtn = (Button) findViewById(C0098R.C0101id.doneBtn);
        this.finalScoreView.setText(String.valueOf(getIntent().getIntExtra("final_score", 0)));
        this.totalQuestions.setText("Out of " + getIntent().getIntExtra("total_questions", 0));
        this.doneBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ScoreActivity.this.finish();
            }
        });
    }
}
