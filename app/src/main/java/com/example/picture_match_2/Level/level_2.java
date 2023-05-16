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
import android.widget.SeekBar;

import com.example.picture_match_2.NoTimeLimit_Page;
import com.example.picture_match_2.Interface.Myinterface2;
import com.example.picture_match_2.Adapter.Picture_Adapter2;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class level_2 extends AppCompatActivity implements Myinterface2 //implements Myinterface
{

    // This Is For Main Activity

    SeekBar SeekBar;
    GridView GridView;
    ArrayList<String> ArrayListImage2;
    List<String> ImageList;


    ActionBar actionBar;

//  This is for Timemanagement and Orignal code

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
        setContentView(R.layout.level_2);

        SeekBar = findViewById(R.id.SeekBar2);
        GridView = findViewById(R.id.Gridvie2);

        SeekBar.setMax(total);
        SeekBar.setProgress(second);

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
                    SeekBar.setProgress(second);
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
                    SeekBar.setProgress(second);
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
                if (SeekBar.getProgress() == SeekBar.getMax())
                {
                    Loss();
                }
                handler2.postDelayed(this , 1000);
            }
        } ,  0);



        try {
            String[] Images = getAssets().list("picture");
            ArrayListImage2 = new ArrayList<>(Arrays.asList(Images));

            ImageList = ArrayListImage2.subList(0 , 7);
            ImageList.addAll(ImageList);
            Collections.shuffle(ImageList);

            Picture_Adapter2 picture_adapter2 = new Picture_Adapter2(this , ArrayListImage2);
            GridView.setAdapter( picture_adapter2);

            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
         }



    @Override
    public void Display2(View v, int position)
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
               if (ArrayListImage2.get(ClickedPositionOne).equals(ArrayListImage2.get(ClickedPositionTwo)))
               {
                   total = SeekBar.getMax();
                   total = total + 5000;
                   SeekBar.setMax(total);
                   actionBar.setTitle((SeekBar.getProgress() / 1000) + " / " + (total / 1000));
                   MOVE++;

                   if(MOVE == 7)
                   {
                       Win();
                   }

               }
               else
               {
                   //  if two pictures are not same then 5 second less in total
                   total = SeekBar.getMax();
                   total = total - 5000;
                   SeekBar.setMax(total);
                   actionBar.setTitle((SeekBar.getProgress() / 1000) + " / " + (total / 1000));

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

   // ================================================================================================
    // Dialog Box for WIN

    public void Win()
    {
        dialogWin = new Dialog(level_2.this);
        dialogWin.setCancelable(true);
        dialogWin.setContentView(R.layout.dailog_win);
        dialogWin.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(level_2.this , NoTimeLimit_Page.class);
                startActivity(IBack);

            }
        });
        dialogWin.findViewById(R.id.Next).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent INext;
                INext = new Intent(level_2.this , level_3.class);
                startActivity(INext);

            }
        });
        dialogWin.show();
    }


    //================================================================================================
    // Dialog Box for LOSS

    public void Loss()
    {
        dialogLoss = new Dialog(level_2.this);
        dialogLoss.setCancelable(true);
        dialogLoss.setContentView(R.layout.dailog_loss);
        dialogLoss.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(level_2.this , NoTimeLimit_Page.class);
                startActivity(IBack);

            }
        });
        dialogLoss.findViewById(R.id.ReStart).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IRestart;
                IRestart = new Intent(level_2.this , level_2.class);
                startActivity(IRestart);

            }
        });
        dialogLoss.show();
   }
}