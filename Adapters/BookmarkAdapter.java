package com.example.quizzer.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quizzer.C0098R;
import com.example.quizzer.Models.QuestionModel;
import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<viewHolder> {
    /* access modifiers changed from: private */
    public List<QuestionModel> questionModelList;

    public BookmarkAdapter(List<QuestionModel> questionModelList2) {
        this.questionModelList = questionModelList2;
    }

    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(C0098R.layout.bookmark_row, parent, false));
    }

    public int getItemCount() {
        return this.questionModelList.size();
    }

    public void onBindViewHolder(viewHolder holder, int position) {
        holder.setData(this.questionModelList.get(position), position);
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView answer;
        ImageButton imageButton;
        TextView question;

        public viewHolder(View itemView) {
            super(itemView);
            this.question = (TextView) itemView.findViewById(C0098R.C0101id.question);
            this.answer = (TextView) itemView.findViewById(C0098R.C0101id.answer);
            this.imageButton = (ImageButton) itemView.findViewById(C0098R.C0101id.deleteBtn);
        }

        /* access modifiers changed from: private */
        public void setData(QuestionModel obj, final int position) {
            this.question.setText(obj.getQuestion());
            this.answer.setText(obj.getCorrectAns());
            this.imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    BookmarkAdapter.this.questionModelList.remove(position);
                    BookmarkAdapter.this.notifyItemRemoved(position);
                }
            });
        }
    }
}
