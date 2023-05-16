package com.example.picture_match_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.picture_match_2.Level.Normal_Level_Page;

public class Normal_Page extends AppCompatActivity implements View.OnClickListener
{

    TextView level1,level2,level3,level4,level5,level6,level7,level8;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_level_page);

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        level4 = findViewById(R.id.level4);
        level5 = findViewById(R.id.level5);
        level6 = findViewById(R.id.level6);
        level7 = findViewById(R.id.level7);
        level8 = findViewById(R.id.level8);

        level1.setOnClickListener(this);
        level2.setOnClickListener(this);
        level3.setOnClickListener(this);
        level4.setOnClickListener(this);
        level5.setOnClickListener(this);
        level6.setOnClickListener(this);
        level7.setOnClickListener(this);
        level8.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == level1.getId())
        {
            Intent INext;
            INext = new Intent(Normal_Page.this , Normal_Level_Page.class);
            INext.putExtra("ClickedPosition" , 0);

            startActivity(INext);finish();
        }

        if (view.getId() == level2.getId())
        {
            Intent INext;
            INext = new Intent(Normal_Page.this , Normal_Level_Page.class);
            INext.putExtra("ClickedPosition" , 1);

            startActivity(INext);
            finish();
        }

        if (view.getId() == level3.getId())
        {
            Intent INext;
            INext = new Intent(Normal_Page.this , Normal_Level_Page.class);
            INext.putExtra("ClickedPosition" , 2);

            startActivity(INext);
            finish();
        }

        if (view.getId() == level4.getId())
        {
            Intent INext;
            INext = new Intent(Normal_Page.this , Normal_Level_Page.class);
            INext.putExtra("ClickedPosition" , 3);

            startActivity(INext);
            finish();
        }

        if (view.getId() == level5.getId())
        {
            Intent INext;
            INext = new Intent(Normal_Page.this , Normal_Level_Page.class);
            INext.putExtra("ClickedPosition" , 4);

            startActivity(INext);
            finish();
        }

        if (view.getId() == level6.getId())
        {
            Intent INext;
            INext = new Intent(Normal_Page.this , Normal_Level_Page.class);
            INext.putExtra("ClickedPosition" , 5);

            startActivity(INext);
            finish();
        }

        if (view.getId() == level7.getId())
        {
            Intent INext;
            INext = new Intent(Normal_Page.this , Normal_Level_Page.class);
            INext.putExtra("ClickedPosition" , 6);

            startActivity(INext);
            finish();
        }

        if (view.getId() == level8.getId())
        {
            Intent INext;
            INext = new Intent(Normal_Page.this , Normal_Level_Page.class);
            INext.putExtra("ClickedPosition" , 7);

            startActivity(INext);
            finish();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent Iback;
        Iback = new Intent(Normal_Page.this , FirstPage.class);
        startActivity(Iback);
        finish();
    }
}
