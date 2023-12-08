package com.example.retrofit.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.retrofit.R;

public class StartActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button dramaBtn = findViewById(R.id.dramaBtn);
        dramaBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("query", "drama");
            this.startActivity(intent);
        });

        Button actionBtn = findViewById(R.id.actionBtn);
        actionBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("query", "action");
            this.startActivity(intent);
            //Toast.makeText(this, "Action Movies", Toast.LENGTH_SHORT).show();
        });
        Button comedyBtn = findViewById(R.id.comedyBtn);
        comedyBtn.setOnClickListener(View -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("query", "comedy");
            this.startActivity(intent);
        });
        Button fictionBtn = findViewById(R.id.fictionBtn);
        fictionBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("query", "fiction");
            this.startActivity(intent);
        });

        SearchView searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                             @Override
                                             public boolean onQueryTextSubmit(String query) {
                                                 // hide keyboard when button is clicked
                                                 InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                 if (inputMethodManager != null){
                                                     inputMethodManager.hideSoftInputFromWindow(searchBtn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                                                 }
                                                 //searchBtn.setQuery("", false);
                                                 Intent intent = new Intent(StartActivity.this, MainActivity.class);
                                                 intent.putExtra("query", query);
                                                 StartActivity.this.startActivity(intent);
                                                 return true;
                                             }
                                             @Override
                                             public boolean onQueryTextChange(String s) {
                                                 return false;
                                             }
        });
    }
}
