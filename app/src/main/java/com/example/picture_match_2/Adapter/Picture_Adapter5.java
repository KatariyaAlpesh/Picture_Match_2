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

import com.example.picture_match_2.Interface.Myinterface5;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

public class Picture_Adapter5 extends BaseAdapter
{

    Context context5;
    List<String> arrayListImage5;

    com.example.picture_match_2.Interface.Myinterface5 Myinterface5;


    public Picture_Adapter5(Context context5, List<String> arrayListImage5)
    {
        this.arrayListImage5 = arrayListImage5;
        this.context5 = context5;
        this.Myinterface5 = (Myinterface5) context5;

    }

    @Override
    public int getCount()
    {
        return 20;
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

        view = LayoutInflater.from(context5).inflate(R.layout.image_page , viewGroup , false);

        ImageView Image5 = view.findViewById(R.id.ImageData);
        ImageView Color5 = view.findViewById(R.id.ImageColordata);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);


        relativeLayout.setOnClickListener(view1 -> {

            Myinterface5.Display5(Color5 , i);

        });

        InputStream inputStream = null;

        try {

            inputStream = context5.getAssets().open("picture/" + arrayListImage5.get(i));
            Drawable drawable = Drawable.createFromStream(inputStream , null);
            Image5.setImageDrawable(drawable);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        new CountDownTimer(10000 , 1000)
        {

            @Override
            public void onFinish()
            {
                System.out.println("Hello");
                Color5.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTick(long l)
            {

            }

        } .start();


        return view;
    }
}
