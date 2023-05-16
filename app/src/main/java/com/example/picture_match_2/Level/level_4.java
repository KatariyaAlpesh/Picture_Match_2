package com.example.picture_match_2.Level;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.picture_match_2.NoTimeLimit_Page;
import com.example.picture_match_2.Interface.Myinterface4;
import com.example.picture_match_2.Adapter.Picture_Adapter4;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class level_4 extends AppCompatActivity implements Myinterface4
{

    SeekBar seekBar;
    GridView gridView;
    ArrayList<String> arrayListImage4;
    List<String> ImageList;

    ActionBar actionBar;


/////    This is for Handelr


    int  total = 30000;
    int second = 6000;
    int temp = 0 , t = 0 , t1 = 0;


///////  This is for Myinterface4.


    int ClickedPositionOne , ClickedPositionTwo;
    View clickedViewOne , clickedViewTwo;


/////////   This is for Dialog Box

    Dialog dialogWin , dialogLoss;
    int MOVE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_4);

        seekBar = findViewById(R.id.SeekBar4);
        gridView = findViewById(R.id.Gridvie4);

        seekBar.setMax(total);
        seekBar.setProgress(second);

        actionBar = getSupportActionBar();
        actionBar.setTitle((second / 1000) + " / " + (total / 1000));


   //===========================================================================================================
    //  First 5 Second Handler

        final Handler handler = new Handler();

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
                    handler.postDelayed(this , 1000);
                    t++;
                }
            }
        } , 0);


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
            arrayListImage4 = new ArrayList<>(Arrays.asList(Images));

            ImageList = arrayListImage4.subList(0 , 9);
            ImageList.addAll(ImageList);
            Collections.shuffle(ImageList);

            Picture_Adapter4 picture_adapter4 = new Picture_Adapter4(this , arrayListImage4);
            gridView.setAdapter( picture_adapter4);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


////////------>>>    Implements Myinterface4 on top of Program  ==  and get this Override Method


    @Override
    public void Display4(ImageView v, int position)
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
                if (arrayListImage4.get(ClickedPositionOne).equals(arrayListImage4.get(ClickedPositionTwo)))
                {
                    total = seekBar.getMax();
                    total = total + 5000;
                    seekBar.setMax(total);
                    actionBar.setTitle((seekBar.getProgress() / 1000) + " / " + (total / 1000));
                    MOVE++;

                    if(MOVE == 9)
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
        dialogWin = new Dialog(level_4.this);
        dialogWin.setCancelable(true);
        dialogWin.setContentView(R.layout.dailog_win);
        dialogWin.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(level_4.this , NoTimeLimit_Page.class);
                startActivity(IBack);

            }
        });
        dialogWin.findViewById(R.id.Next).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent INext;
                INext = new Intent(level_4.this , level_5.class);
                startActivity(INext);

            }
        });
        dialogWin.show();
    }


    //================================================================================================
    // Dialog Box for LOSS

    public void Loss()
    {
        dialogLoss = new Dialog(level_4.this);
        dialogLoss.setCancelable(true);
        dialogLoss.setContentView(R.layout.dailog_loss);
        dialogLoss.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(level_4.this , NoTimeLimit_Page.class);
                startActivity(IBack);

            }
        });
        dialogLoss.findViewById(R.id.ReStart).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IRestart;
                IRestart = new Intent(level_4.this , level_4.class);
                startActivity(IRestart);

            }
        });
        dialogLoss.show();
    }


}
