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

import com.example.picture_match_2.Interface.Myinterface3;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Picture_Adapter3 extends BaseAdapter
{

    List<String> ArrayListImage3;
    Context context3;
    com.example.picture_match_2.Interface.Myinterface3 Myinterface3;

    public Picture_Adapter3(Context context3 , List<String> ArrayListImage3)
    {
        this.context3 = context3;
        this.ArrayListImage3 = ArrayListImage3;
        this.Myinterface3 = (Myinterface3) context3;
    }

    @Override
    public int getCount()
    {
        return 16;
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

        view = LayoutInflater.from(context3).inflate(R.layout.image_page , viewGroup , false);

        ImageView Image3 = view.findViewById(R.id.ImageData);
        ImageView Color3 = view.findViewById(R.id.ImageColordata);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);

        InputStream inputStream;
        relativeLayout.setOnClickListener(V -> {

            Myinterface3.Display3(Color3 , i);
        });

        try {
            inputStream = context3.getAssets().open("picture/" + ArrayListImage3.get(i));
            Drawable drwable = Drawable.createFromStream(inputStream, null);
            Image3.setImageDrawable(drwable);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        new CountDownTimer(5000, 1000)
        {
            public void onFinish()
            {
                System.out.println("Hello");
                Color3.setVisibility(View.VISIBLE);

            }
            @Override
            public void onTick(long l)
            {

            }
        } .start();


        return view;

    }
}
