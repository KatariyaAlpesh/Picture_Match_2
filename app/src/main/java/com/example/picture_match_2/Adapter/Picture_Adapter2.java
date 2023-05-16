package com.example.picture_match_2.Adapter;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.picture_match_2.Interface.Myinterface2;
import com.example.picture_match_2.Level.level_2;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Picture_Adapter2 extends BaseAdapter
{
    List<String> ArrayListImage2;
    level_2 context2;
    com.example.picture_match_2.Interface.Myinterface2 Myinterface2;

    public Picture_Adapter2(level_2 context2 , List<String> ArrayListImage2)
    {
        this.context2 = context2;
        this.ArrayListImage2 = ArrayListImage2;
        this.Myinterface2 = (Myinterface2)context2 ;
    }

    @Override
    public int getCount()
    {
        return 14;
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
        view = LayoutInflater.from(context2).inflate(R.layout.image_page , viewGroup , false);

        ImageView Image2 = view.findViewById(R.id.ImageData);
        ImageView Color2 = view.findViewById(R.id.ImageColordata);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);

        InputStream inputStream;
        relativeLayout.setOnClickListener(V -> {

            Myinterface2.Display2(Color2 , i);
        });

        try {
            inputStream = context2.getAssets().open("picture/" + ArrayListImage2.get(i));
            Drawable drwable = Drawable.createFromStream(inputStream, null);
            Image2.setImageDrawable(drwable);

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
                Color2.setVisibility(View.VISIBLE);

            }
            @Override
            public void onTick(long l)
            {

            }
        } .start();


        return view;
    }
}
