package com.example.picture_match_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class FirstPage extends AppCompatActivity
{

    TextView noTimeLimit , normal , hard , removeAds , share , moreGame;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        noTimeLimit = findViewById(R.id.TextViewNoTimeLimit);
        normal = findViewById(R.id.TextViewNormal);
        hard = findViewById(R.id.TextViewHard);
        removeAds = findViewById(R.id.TextViewRemoveAds);
        share = findViewById(R.id.TextViewShare);
        moreGame = findViewById(R.id.TextViewMoreGame);



        noTimeLimit.setOnClickListener(view -> {

            Intent Inext = new Intent(FirstPage.this , NoTimeLimit_Page.class);
            startActivity(Inext);

        });

        normal.setOnClickListener(view -> {

            Intent Inext = new Intent(FirstPage.this , Normal_Page.class);
            startActivity(Inext);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.header_menu , menu);

        if(menu instanceof MenuBuilder)
        {
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
    }
}