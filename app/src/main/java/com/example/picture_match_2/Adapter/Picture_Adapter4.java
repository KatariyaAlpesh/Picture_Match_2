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

import com.example.picture_match_2.Interface.Myinterface4;
import com.example.picture_match_2.Level.level_4;
import com.example.picture_match_2.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Picture_Adapter4 extends BaseAdapter
{

    List<String> arrayListImage4;
    com.example.picture_match_2.Interface.Myinterface4 Myinterface4;
    Context context4;


    public Picture_Adapter4(Context context4, List<String> arrayListImage4)
    {
        this.arrayListImage4 = arrayListImage4;
        this.context4 = context4;
        this.Myinterface4 = (Myinterface4)  context4;

    }

    @Override
    public int getCount()
    {
        return 18;
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

        view = LayoutInflater.from(context4).inflate(R.layout.image_page , viewGroup , false);

        ImageView Image4 = view.findViewById(R.id.ImageData);
        ImageView Color4 = view.findViewById(R.id.ImageColordata);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);


        relativeLayout.setOnClickListener(V -> {

            Myinterface4.Display4(Color4 , i);
        });


        InputStream inputStream;

        try {
            inputStream = context4.getAssets().open("picture/" + arrayListImage4.get(i));
            Drawable drwable = Drawable.createFromStream(inputStream, null);
            Image4.setImageDrawable(drwable);

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
                Color4.setVisibility(View.VISIBLE);

            }
            @Override
            public void onTick(long l)
            {

            }
        } .start();


        return view;

    }
}
