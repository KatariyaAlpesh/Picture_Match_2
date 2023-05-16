package com.example.picture_match_2.Level;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.picture_match_2.NoTimeLimit_Page;
import com.example.picture_match_2.Interface.Myinterface3;
import com.example.picture_match_2.Adapter.Picture_Adapter3;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class level_3 extends AppCompatActivity implements Myinterface3
{

    SeekBar seekBar;
    GridView GridView;
    ArrayList<String> ArrayListImage3;
    List<String> ImageList;


    ActionBar actionBar;


    int temp = 0 , t = 0 , t1 = 0;
    int ClickedPositionOne , ClickedPositionTwo;
    View clickedViewOne , clickedViewTwo;
    int  total = 30000;
    int second = 6000;


    //   This is for Dialog Box
    Dialog dialogWin , dialogLoss;
    int MOVE = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_3);


        seekBar = findViewById(R.id.SeekBar3);
        GridView = findViewById(R.id.Gridvie3);

        seekBar.setMax(total);
        seekBar.setProgress(second);

        actionBar = getSupportActionBar();
        actionBar.setTitle((second / 1000) + " / " + (total / 1000));

        final Handler handler = new Handler();

        //===========================================================================================================
        //  First 5 Second Handler

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (t < 5)
                {
                    second = second - 1000;
                    actionBar.setTitle((second / 1000) + " / " + (total / 1000));
                    seekBar.setProgress(second);
                    handler.postDelayed(this, 1000);
                    t++;
                }
            }
        }, 0);

        //===========================================================================================================
        //  Second 30 Second Handler

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (t1 < (total / 1000))
                {
                    second = second + 1000;
                    actionBar.setTitle((second / 1000) + " / " + (total / 1000));
                    seekBar.setProgress(second);
                    handler.postDelayed(this , 1000);
                    t1++;
                }
            }
        } , 6000);

        //===========================================================================================================
        //  After Finish Game

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (seekBar.getProgress() == seekBar.getMax())
                {
                    Loss();
                }
                handler2.postDelayed(this , 1000);
            }
        } ,  0);



        try {
            String[] Images = getAssets().list("picture");
            ArrayListImage3 = new ArrayList<>(Arrays.asList(Images));

            ImageList = ArrayListImage3.subList(0 , 8);
            ImageList.addAll(ImageList);
            Collections.shuffle(ImageList);

            Picture_Adapter3 picture_adapter3 = new Picture_Adapter3(this , ArrayListImage3);
            GridView.setAdapter( picture_adapter3);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void Display3(ImageView v, int position)
    {
        if (v.getVisibility() == View.VISIBLE)
        {
            if (temp % 2 == 0)
            {
                System.out.println(" First ");
                v.setVisibility(View.GONE);
                ClickedPositionOne = position;
                clickedViewOne = v;
                temp++;
            }
            else
            {
                System.out.println(" Second ");
                v.setVisibility(View.GONE);
                ClickedPositionTwo = position;
                clickedViewTwo = v;
                temp++;

//
                if (ArrayListImage3.get(ClickedPositionOne).equals(ArrayListImage3.get(ClickedPositionTwo)))
                {
                    total = seekBar.getMax();
                    total = total + 5000;
                    seekBar.setMax(total);
                    actionBar.setTitle((seekBar.getProgress() / 1000) + " / " + (total / 1000));
                    MOVE++;

                    if(MOVE == 8)
                    {
                        Win();
                    }

                }
                else
                {
                    //  if two pictures are not same then 5 second less in total
                    total = seekBar.getMax();
                    total = total - 5000;
                    seekBar.setMax(total);
                    actionBar.setTitle((seekBar.getProgress() / 1000) + " / " + (total / 1000));

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            // This Prosess work After 5 second finish.

                            clickedViewOne.setVisibility(View.VISIBLE);
                            clickedViewTwo.setVisibility(View.VISIBLE);
                        }
                    }, 500);
                }
            }
        }

    }

    //================================================================================================
    // Dialog Box for Win

    public void Win()
    {
        dialogWin = new Dialog(level_3.this);
        dialogWin.setCancelable(true);
        dialogWin.setContentView(R.layout.dailog_win);
        dialogWin.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(level_3.this , NoTimeLimit_Page.class);
                startActivity(IBack);
            }
        });
        dialogWin.findViewById(R.id.Next).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent INext;
                INext = new Intent(level_3.this , level_4.class);
                startActivity(INext);
            }
        });
        dialogWin.show();
    }


    //================================================================================================
    // Dialog Box for LOSS

    public void Loss()
    {
        dialogLoss = new Dialog(level_3.this);
        dialogLoss.setCancelable(true);
        dialogLoss.setContentView(R.layout.dailog_loss);
        dialogLoss.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(level_3.this , NoTimeLimit_Page.class);
                startActivity(IBack);
            }
        });
        dialogLoss.findViewById(R.id.ReStart).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IRestart;
                IRestart = new Intent(level_3.this , level_3.class);
                startActivity(IRestart);
            }
        });
        dialogLoss.show();
    }


}