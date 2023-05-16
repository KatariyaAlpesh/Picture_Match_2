package com.example.picture_match_2.Level;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.picture_match_2.Adapter.NoTimeLimit_Picture_Adapter;
import com.example.picture_match_2.Interface.MyinterfaceNoTimeLimit;
import com.example.picture_match_2.NoTimeLimit_Page;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NoTimeLimit_Level_Page extends AppCompatActivity implements MyinterfaceNoTimeLimit
{
    SeekBar seekBar;
    GridView gridView;
    ArrayList<String> arrayListImage;
    List<String> ImageList;
    ActionBar actionBar;


    int secondarray[] = {5000 , 5000 , 5000 , 10000 , 10000 , 10000 , 10000 , 10000};
    int second;
    int total;
    int t = 0 , t1 = 0;
    int temp = 0;
    int MOVE = 0;
    View clickedViewOne , clickedViewTwo;
    int clickedPositionOne , clickedPositionTwo;
    AlertDialog.Builder dialogWin , dialogLoss;


    int Levellength[] = { 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12};

    int ColumnArray[] = { 3 , 3 , 4 , 4 , 4 , 4 , 4 , 4};


    int ClickedPosition ;


//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nolimit_page);


        ClickedPosition = getIntent().getIntExtra("ClickedPosition" , 0);


        seekBar = findViewById(R.id.SeekBar);
        gridView = findViewById(R.id.Gridview);

        System.out.println(ClickedPosition);

        dialogWin = new AlertDialog.Builder(NoTimeLimit_Level_Page.this);
        dialogLoss = new AlertDialog.Builder(NoTimeLimit_Level_Page.this);

        second = secondarray[ClickedPosition];

        seekBar.setMax(total);
        seekBar.setProgress(second);

        actionBar = getSupportActionBar();
        actionBar.setTitle((second / 1000) + " / " + (total / 1000));

        final Handler handler = new Handler();
        final Handler handler1 = new Handler();
        final Handler handler2 = new Handler();


        //-----------------------------------------------------------------------------------------------------------------
        //  for First 5 Second

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
//                else if (t > 5 && t <= 10)
//                {
//                    second = second - 1000;
//                    actionBar.setTitle((second / 1000) + " / " + (total / 1000));
//                    seekBar.setProgress(second);
//                    handler.postDelayed(this , 1000);
//                    t++;
//                }
            }
        },  0);


        // -------------------------------------------------------------------------------------------------------------------------------------
        //  for 30 Second Total Game


//        if (t > 5)
//        {
            handler1.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    if (t1 >=  0)  //(total / 1000))
                    {

                        second = second + 1000;
                        actionBar.setTitle((second / 1000) + " / " + (total / 1000));
                        seekBar.setProgress(second);
                        handler.postDelayed(this, 1000);
                        t1++;
                    }
                }
            }, 6000);
//        }
//        else
//        {
//            handler1.postDelayed(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    if (t1 < (total / 1000))
//                    {
//
//                        second = second + 1000;
//                        actionBar.setTitle((second / 1000) + " / " + (total / 1000));
//                        seekBar.setProgress(second);
//                        handler.postDelayed(this, 1000);
//                        t1++;
//                    }
//                }
//            }, 6000);
//
//        }
        // -------------------------------------------------------------------------------------------------------------------------------------
        // Alert for Finish Game

//        handler2.postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                if (seekBar.getProgress() >= seekBar.getMax())
//                {
//                    Loss();
//                }
//                handler2.postDelayed(this , 1000);
//            }
//        } ,  0);


        try {
            String[] images = getAssets().list("picture");
            arrayListImage = new ArrayList<>(Arrays.asList(images));

            ImageList = arrayListImage.subList(0 , Levellength[ClickedPosition]);

            ImageList.addAll(ImageList);
            Collections.shuffle(ImageList);

            gridView.setNumColumns(ColumnArray[ClickedPosition]);

            gridView.setMinimumHeight(50);
            gridView.setMinimumWidth(150);
            gridView.setColumnWidth(100);

            NoTimeLimit_Picture_Adapter picture_adapter = new NoTimeLimit_Picture_Adapter(this , arrayListImage ,
                                                                          Levellength[ClickedPosition]);  // , secondarray[ClickedPosition]);
            gridView.setAdapter(picture_adapter);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
     }

     // This code is Only for To deside Num of Clicke's is First Or Second  //

    @Override
    public void Display(View v, int position)
    {
        if (v.getVisibility() == View.VISIBLE)
        {
            if (temp % 2 ==0)
            {
                System.out.println("First");
                v.setVisibility(View.GONE);
                clickedPositionOne = position;
                clickedViewOne = v;
                temp++;
            }
            else
            {
                //   For Second Click

                System.out.println("Second");
                v.setVisibility(View.GONE);
                clickedPositionTwo = position;
                clickedViewTwo = v;
                temp++;

                if (arrayListImage.get(clickedPositionOne).equals(arrayListImage.get(clickedPositionTwo)))
                {
                    //  For update 5 Second ( Two picture are Same then got " 5 " extra Point's)

                    total = seekBar.getMax();
//                    total = total + 5000;
                    seekBar.setMax(total);
                    actionBar.setTitle( (seekBar.getProgress() / 1000) + " / " + (total / 1000) );
                    MOVE++;

                    if(MOVE == Levellength[ClickedPosition])
                    {
                        Win();

                    }

                }
                else
                {
                    //  For update 5 Second ( Two picture are Not Same then less  " 5 " Point's)

                    total = seekBar.getMax();
//                    total = total - 5000;
                    seekBar.setMax(total);
                    actionBar.setTitle( (seekBar.getProgress() / 1000) + " / " + (total / 1000));

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            // Do Something After 5 Second;

                            clickedViewOne.setVisibility(View.VISIBLE);
                            clickedViewTwo.setVisibility(View.VISIBLE);
                        }
                    }, 500);
                }
            }
        }
    }

//================================================================================================
    // OnBack press Finish Activity and Back to Level Page


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent IBack;
        IBack = new Intent(NoTimeLimit_Level_Page.this , NoTimeLimit_Page.class);
        startActivity(IBack);
        finish();

    }

// ================================================================================================
//                Dialog Box for WIN

    public void Win()
    {

        View view = LayoutInflater.from(NoTimeLimit_Level_Page.this).inflate(R.layout.dailog_win , null , false);
        dialogWin.setView(view);
        dialogWin.setCancelable(true);

        TextView textView = view.findViewById(R.id.Back);
        TextView textView1 = view.findViewById(R.id.Next);

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(NoTimeLimit_Level_Page.this , NoTimeLimit_Page.class);
                startActivity(IBack);

            }
        });

        textView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent INext;
                INext = new Intent(NoTimeLimit_Level_Page.this , NoTimeLimit_Level_Page.class);
                INext.putExtra("ClickedPosition" , ClickedPosition + 1);
                startActivity(INext);

            }
        });
        dialogWin.show();
    }

    //================================================================================================
    // Dialog Box for LOSS

    public void Loss()
    {
        View view = LayoutInflater.from(this).inflate(R.layout.dailog_loss , null , false);
        dialogLoss.setView(view);
        dialogLoss.setCancelable(true);

        TextView textView = view.findViewById(R.id.Back);
        TextView textView1 = view.findViewById(R.id.ReStart);

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent IBack;
                IBack = new Intent(NoTimeLimit_Level_Page.this , NoTimeLimit_Page.class);
                startActivity(IBack);

            }
        });
        textView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent Irestart;
                Irestart = new Intent(NoTimeLimit_Level_Page.this , NoTimeLimit_Level_Page.class);
                Irestart.putExtra("ClickedPosition" , ClickedPosition );
                startActivity(Irestart);

            }
        });
        dialogLoss.show();

    }
}