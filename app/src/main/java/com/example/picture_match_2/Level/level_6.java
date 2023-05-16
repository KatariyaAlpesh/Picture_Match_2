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

import com.example.picture_match_2.Adapter.Picture_Adapter6;
import com.example.picture_match_2.NoTimeLimit_Page;
import com.example.picture_match_2.Interface.Myinterface6;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class level_6 extends AppCompatActivity implements Myinterface6
{

    SeekBar seekBar;
    GridView gridView;
    ArrayList<String> arrayListImage6;
    List<String> ImageList;

    ActionBar actionBar;


/////    This is for Handelr

    int temp = 0 , t = 0 , t1 = 0;
    int second = 11000;
    int total = 30000;


//////  This is for Myinterface4.

    int ClickedPositionOne , ClickedPositionTwo;
    View clickedViewOne , clickedViewTwo;


//////  This is for DialogBox.

    Dialog dialogWin , dialogLoss;

    int Move = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_6);


        seekBar = findViewById(R.id.SeekBar6);
        gridView = findViewById(R.id.Gridvie6);

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
                if (t < 10)
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
        } , 11000);


        //===========================================================================================================
        //  Second 30 Second Handler


        final  Handler handler2 = new Handler();
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
        } , 0);



        try {

            String[] Image = getAssets().list("picture");
            arrayListImage6 = new ArrayList<>(Arrays.asList(Image));

            ImageList = arrayListImage6.subList(0 , 11);
            ImageList.addAll(ImageList);
            Collections.shuffle(ImageList);

            Picture_Adapter6 picture_adapter6 = new Picture_Adapter6(this , arrayListImage6);
            gridView.setAdapter(picture_adapter6);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Display6(ImageView v, int position)
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
                if (arrayListImage6.get(ClickedPositionOne).equals(arrayListImage6.get(ClickedPositionTwo)))
                {
                    //  if two pictures are same then 5 second Plus in total

                    total = seekBar.getMax();
                    total = total + 5000;
                    seekBar.setMax(total);
                    actionBar.setTitle((seekBar.getProgress() / 1000) + " / " + (total / 1000));
                    Move++;

                    if(Move == 11)
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
                            // This Prosess work After 10 second finish.

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
        dialogWin = new Dialog(level_6.this);
        dialogWin.setCancelable(true);
        dialogWin.setContentView(R.layout.dailog_win);
        dialogWin.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(level_6.this , NoTimeLimit_Page.class);
                startActivity(IBack);

            }
        });
        dialogWin.findViewById(R.id.Next).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent INext;
                INext = new Intent(level_6.this , level_7.class);
                startActivity(INext);

            }
        });
        dialogWin.show();
    }


    //================================================================================================
    // Dialog Box for LOSS

    public void Loss()
    {
        dialogLoss = new Dialog(level_6.this);
        dialogLoss.setCancelable(true);
        dialogLoss.setContentView(R.layout.dailog_loss);
        dialogLoss.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(level_6.this , NoTimeLimit_Page.class);
                startActivity(IBack);

            }
        });
        dialogLoss.findViewById(R.id.ReStart).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IRestart;
                IRestart = new Intent(level_6.this , level_6.class);
                startActivity(IRestart);

            }
        });
        dialogLoss.show();
    }

}