package com.example.quizzer.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.quizzer.C0098R;
import com.example.quizzer.Models.CategoryModel;
import com.example.quizzer.SetsActivity;
import java.util.List;
import p000de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<viewHolder> {
    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList2) {
        this.categoryModelList = categoryModelList2;
    }

    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(C0098R.layout.categoryitem, parent, false));
    }

    public void onBindViewHolder(viewHolder holder, int position) {
        CategoryModel categoryModelOBJ = this.categoryModelList.get(position);
        holder.setData(categoryModelOBJ.getImageURL(), categoryModelOBJ.getTitle(), this.categoryModelList.get(position).getSets());
    }

    public int getItemCount() {
        return this.categoryModelList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;
        private TextView titleTextView;

        public viewHolder(View itemView) {
            super(itemView);
            this.circleImageView = (CircleImageView) itemView.findViewById(C0098R.C0101id.image);
            this.titleTextView = (TextView) itemView.findViewById(C0098R.C0101id.title);
        }

        /* access modifiers changed from: private */
        public void setData(String url, final String title, final int sets) {
            Glide.with(this.itemView.getContext()).load(url).into((ImageView) this.circleImageView);
            this.titleTextView.setText(title);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent setIntent = new Intent(viewHolder.this.itemView.getContext(), SetsActivity.class);
                    setIntent.putExtra("title", title);
                    setIntent.putExtra("sets", sets);
                    viewHolder.this.itemView.getContext().startActivity(setIntent);
                }
            });
        }
    }
}
