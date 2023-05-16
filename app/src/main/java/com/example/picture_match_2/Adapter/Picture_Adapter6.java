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

import com.example.picture_match_2.Interface.Myinterface6;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Picture_Adapter6 extends BaseAdapter
{

    List<String> arrayListImage6;
    Context context6;
    com.example.picture_match_2.Interface.Myinterface6 Myinterface6;

    public Picture_Adapter6(Context context6, List<String> arrayListImage6)
    {

        this.arrayListImage6 = arrayListImage6;
        this.context6 = context6;
        this.Myinterface6 = (Myinterface6) context6;

    }

    @Override
    public int getCount()
    {
        return 22;
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

        view = LayoutInflater.from(context6).inflate(R.layout.image_page  , viewGroup , false);

        ImageView Image6 = view.findViewById(R.id.ImageData);
        ImageView Color6 = view.findViewById(R.id.ImageColordata);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);

        relativeLayout.setOnClickListener(view1 -> {

            Myinterface6.Display6(Color6 , i);

        });


        InputStream inputStream = null;

        try {

            inputStream = context6.getAssets().open("picture/" + arrayListImage6.get(i));
            Drawable drawable = Drawable.createFromStream(inputStream , null);
            Image6.setImageDrawable(drawable);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        new CountDownTimer(10000 , 1000)
        {

            @Override
            public void onTick(long l)
            {

            }

            @Override
            public void onFinish()
            {
                System.out.println("Hello");
                Color6.setVisibility(View.VISIBLE);
            }
        }.start();


        return view;
    }


}
