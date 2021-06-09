package com.example.quizzer;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quizzer.Adapters.CategoryAdapter;
import com.example.quizzer.Models.CategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    List<CategoryModel> list;
    Dialog loadingDialog;
    private RecyclerView recyclerView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView((int) C0098R.layout.activity_category);
        this.recyclerView = (RecyclerView) findViewById(C0098R.C0101id.recyclerView);
        setSupportActionBar((Toolbar) findViewById(C0098R.C0101id.ToolBar));
        getSupportActionBar().setTitle((CharSequence) "Categories");
        Dialog dialog = new Dialog(this, 2131820939);
        this.loadingDialog = dialog;
        dialog.setContentView(C0098R.layout.loading);
        this.loadingDialog.getWindow().setLayout(-1, -1);
        this.loadingDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.list = new ArrayList();
        final CategoryAdapter categoryAdapter = new CategoryAdapter(this.list);
        this.recyclerView.setAdapter(categoryAdapter);
        this.loadingDialog.show();
        myref.child("Categories").addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot categoryModel : snapshot.getChildren()) {
                    CategoryActivity.this.list.add((CategoryModel) categoryModel.getValue(CategoryModel.class));
                }
                categoryAdapter.notifyDataSetChanged();
                CategoryActivity.this.loadingDialog.dismiss();
            }

            public void onCancelled(DatabaseError error) {
                Toast.makeText(CategoryActivity.this, "Database Error", 0).show();
                CategoryActivity.this.loadingDialog.dismiss();
                CategoryActivity.this.finish();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
