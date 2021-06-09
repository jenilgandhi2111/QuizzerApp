package com.example.quizzer;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.quizzer.Models.QuestionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {
    public static final String FILE_NAME = "QUIZZER";
    public static final String KEY_NAME = "QUESTIONS";
    /* access modifiers changed from: private */
    public List<QuestionModel> bookMarksList;
    /* access modifiers changed from: private */
    public FloatingActionButton bookmark;
    private String category;
    /* access modifiers changed from: private */
    public int count = 0;
    private SharedPreferences.Editor editor;
    private Gson gson;
    /* access modifiers changed from: private */
    public Dialog loadingDialog;
    /* access modifiers changed from: private */
    public int matchedQuestionPosition;
    /* access modifiers changed from: private */
    public Button nextBtn;
    /* access modifiers changed from: private */
    public TextView numberIndicator;
    /* access modifiers changed from: private */
    public LinearLayout optionsContainer;
    /* access modifiers changed from: private */
    public int position = 0;
    private SharedPreferences preferences;
    /* access modifiers changed from: private */
    public TextView question;
    List<QuestionModel> questionModelList;
    /* access modifiers changed from: private */
    public int score = 0;
    private int setNo;
    private Button shareBtn;

    static /* synthetic */ int access$1308(QuestionsActivity x0) {
        int i = x0.count;
        x0.count = i + 1;
        return i;
    }

    static /* synthetic */ int access$408(QuestionsActivity x0) {
        int i = x0.position;
        x0.position = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0098R.layout.activity_questions);
        setSupportActionBar((Toolbar) findViewById(C0098R.C0101id.ToolBar));
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
        SharedPreferences sharedPreferences = getSharedPreferences("QUIZZER", 0);
        this.preferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.gson = new Gson();
        this.question = (TextView) findViewById(C0098R.C0101id.question);
        this.numberIndicator = (TextView) findViewById(C0098R.C0101id.noindicator);
        this.bookmark = (FloatingActionButton) findViewById(C0098R.C0101id.fab);
        this.optionsContainer = (LinearLayout) findViewById(C0098R.C0101id.optionsContainer);
        this.shareBtn = (Button) findViewById(C0098R.C0101id.sharebtn);
        this.nextBtn = (Button) findViewById(C0098R.C0101id.nextbtn);
        this.questionModelList = new ArrayList();
        this.setNo = getIntent().getIntExtra("SET_NO", 0) + 1;
        this.category = getIntent().getStringExtra("category");
        Dialog dialog = new Dialog(this, 2131820939);
        this.loadingDialog = dialog;
        dialog.setContentView(C0098R.layout.loading);
        this.loadingDialog.getWindow().setLayout(-1, -1);
        this.loadingDialog.setCancelable(false);
        getBookMarks();
        this.bookmark.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (QuestionsActivity.this.isBookmarked()) {
                    QuestionsActivity.this.bookMarksList.remove(QuestionsActivity.this.matchedQuestionPosition);
                    QuestionsActivity.this.bookmark.setImageDrawable(QuestionsActivity.this.getDrawable(C0098R.C0100drawable.ic_action_bookmark));
                    return;
                }
                QuestionsActivity.this.bookMarksList.add(QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position));
                QuestionsActivity.this.bookmark.setImageDrawable(QuestionsActivity.this.getDrawable(C0098R.C0100drawable.ic_action_fill_book));
            }
        });
        this.shareBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent shareIntent = new Intent("android.intent.action.SEND");
                shareIntent.setType("text/plain");
                shareIntent.putExtra("android.intent.extra.SUBJECT", "Quizzer Challenge");
                shareIntent.putExtra("android.intent.extra.TEXT", QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position).getQuestion() + "\nA.)" + QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position).getOptA() + "\nB.)" + QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position).getOptB() + "\nC.)" + QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position).getOptC() + "\nD.)" + QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position).getOptD() + "\nFinding difficulty to answer join Quizzer Now!!");
                QuestionsActivity.this.startActivity(Intent.createChooser(shareIntent, "Send this via"));
            }
        });
        this.loadingDialog.show();
        myref.child("SETS").child(this.category.toLowerCase()).child("questions").orderByChild("setNo").equalTo(1.0d).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    QuestionsActivity.this.questionModelList.add((QuestionModel) snapshot.getValue(QuestionModel.class));
                }
                if (QuestionsActivity.this.questionModelList.size() > 0) {
                    for (int i = 0; i < 4; i++) {
                        QuestionsActivity.this.optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                QuestionsActivity.this.checkAnswer((Button) v);
                            }
                        });
                    }
                    QuestionsActivity.this.loadingDialog.dismiss();
                    QuestionsActivity questionsActivity = QuestionsActivity.this;
                    questionsActivity.playAnim(questionsActivity.question, 0, QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position).getQuestion());
                    QuestionsActivity.this.nextBtn.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            QuestionsActivity.this.nextBtn.setEnabled(false);
                            QuestionsActivity.this.nextBtn.setAlpha(0.7f);
                            QuestionsActivity.this.enableOptions(true);
                            QuestionsActivity.access$408(QuestionsActivity.this);
                            if (QuestionsActivity.this.position == QuestionsActivity.this.questionModelList.size()) {
                                Intent finalScoreIntent = new Intent(QuestionsActivity.this, ScoreActivity.class);
                                finalScoreIntent.putExtra("final_score", QuestionsActivity.this.score);
                                finalScoreIntent.putExtra("total_questions", QuestionsActivity.this.questionModelList.size());
                                QuestionsActivity.this.startActivity(finalScoreIntent);
                            }
                            int unused = QuestionsActivity.this.count = 0;
                            QuestionsActivity.this.playAnim(QuestionsActivity.this.question, 0, QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position).getQuestion());
                        }
                    });
                    return;
                }
                QuestionsActivity.this.loadingDialog.dismiss();
                Toast.makeText(QuestionsActivity.this, "no questions", 0).show();
                QuestionsActivity.this.finish();
            }

            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(QuestionsActivity.this, databaseError.getMessage(), 0).show();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        storeBookMarks();
    }

    /* access modifiers changed from: private */
    public void playAnim(final View view, final int value, final String data) {
        view.animate().alpha((float) value).scaleX((float) value).scaleY((float) value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
                if (value == 0 && QuestionsActivity.this.count < 4) {
                    QuestionsActivity questionsActivity = QuestionsActivity.this;
                    questionsActivity.playAnim(questionsActivity.optionsContainer.getChildAt(QuestionsActivity.this.count), 0, QuestionsActivity.this.questionModelList.get(QuestionsActivity.this.position).getOptionByPosition(QuestionsActivity.this.count));
                    QuestionsActivity.access$1308(QuestionsActivity.this);
                }
            }

            public void onAnimationEnd(Animator animation) {
                try {
                    ((TextView) view).setText(data);
                    QuestionsActivity.this.numberIndicator.setText((QuestionsActivity.this.position + 1) + "/" + QuestionsActivity.this.questionModelList.size());
                    if (QuestionsActivity.this.isBookmarked()) {
                        QuestionsActivity.this.bookmark.setImageDrawable(QuestionsActivity.this.getDrawable(C0098R.C0100drawable.ic_action_fill_book));
                    } else {
                        QuestionsActivity.this.bookmark.setImageDrawable(QuestionsActivity.this.getDrawable(C0098R.C0100drawable.ic_action_bookmark));
                    }
                } catch (ClassCastException e) {
                    ((Button) view).setText(data);
                }
                view.setTag(data);
                if (value == 0) {
                    QuestionsActivity.this.playAnim(view, 1, data);
                }
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void checkAnswer(Button selectedOption) {
        enableOptions(false);
        this.nextBtn.setEnabled(true);
        this.nextBtn.setAlpha(1.0f);
        if (selectedOption.getText().toString().equals(this.questionModelList.get(this.position).getCorrectAns())) {
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF4CAF50")));
            this.score++;
            return;
        }
        selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
        ((Button) this.optionsContainer.findViewWithTag(this.questionModelList.get(this.position).getCorrectAns())).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF4CAF50")));
    }

    /* access modifiers changed from: private */
    public void enableOptions(boolean is_enabled) {
        for (int i = 0; i < 4; i++) {
            this.optionsContainer.getChildAt(i).setEnabled(is_enabled);
            if (is_enabled) {
                this.optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }
    }

    public void getBookMarks() {
        List<QuestionModel> list = (List) this.gson.fromJson(this.preferences.getString("QUESTIONS", ""), new TypeToken<List<QuestionModel>>() {
        }.getType());
        this.bookMarksList = list;
        if (list == null) {
            this.bookMarksList = new ArrayList();
        }
    }

    /* access modifiers changed from: private */
    public boolean isBookmarked() {
        int i = 0;
        for (QuestionModel item : this.bookMarksList) {
            if (item.getQuestion().equals(this.questionModelList.get(this.position).getQuestion())) {
                this.matchedQuestionPosition = i;
                return true;
            }
            i++;
        }
        return false;
    }

    private void storeBookMarks() {
        this.editor.putString("QUESTIONS", this.gson.toJson((Object) this.bookMarksList));
        this.editor.commit();
    }
}
