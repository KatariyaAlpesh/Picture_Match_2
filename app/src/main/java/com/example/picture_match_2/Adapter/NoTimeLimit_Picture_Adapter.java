package com.example.picture_match_2.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.picture_match_2.Interface.MyinterfaceNoTimeLimit;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NoTimeLimit_Picture_Adapter extends BaseAdapter
{
    List<String> arrayListImage;
    Context context;
    MyinterfaceNoTimeLimit myinterface;
    int ClickedPosition ; //  , ClickedPosition2;

    int t = 0;
   // int ImageLength;

    public NoTimeLimit_Picture_Adapter(Context context , List<String> arrayListImage , int ClickedPosition ) // , int ClickedPosition2)
    {
        this.context = context;
        this.ClickedPosition  = ClickedPosition;
     //   this.ClickedPosition2 = ClickedPosition2;
        this.arrayListImage = arrayListImage;
        myinterface = (MyinterfaceNoTimeLimit) context;
    }

    @Override
    public int getCount()
    {
        return ClickedPosition * 2;
    }

    @Override
    public Object getItem(int i)
    {
        return i;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = LayoutInflater.from(context).inflate(R.layout.image_page , viewGroup , false);

        ImageView Image = view.findViewById(R.id.ImageData);
        ImageView color = view.findViewById(R.id.ImageColordata);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);

        InputStream inputStream;
        relativeLayout.setOnClickListener(V -> {

            myinterface.Display(color , i);
        });

        try {
            inputStream = context.getAssets().open("picture/" + arrayListImage.get(i));
            Drawable drawable = Drawable.createFromStream(inputStream , null);
            Image.setImageDrawable(drawable);

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        if (t > 5)
        {
            new CountDownTimer(10000, 1000)
            {
                public void onFinish()
                {
                    System.out.println("hello");
                    color.setVisibility(View.VISIBLE);
                    // When timer is finished
                    // Execute your code here
                }

                public void onTick(long millisUntilFinished)
                {
                    // System.out.println(millisUntilFinished);
                    // millisUntilFinished    The amount of time until finished.
                }
            }.start();
        }
        else
        {
            new CountDownTimer(5000, 1000)
            {
                public void onFinish()
                {
                    System.out.println("hello");
                    color.setVisibility(View.VISIBLE);
                    // When timer is finished
                    // Execute your code here
                }

                public void onTick(long millisUntilFinished)
                {
                    // System.out.println(millisUntilFinished);
                    // millisUntilFinished    The amount of time until finished.
                }
            }.start();

        }

        return view;
    }

}


//
