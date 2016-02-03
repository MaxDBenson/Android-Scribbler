package edu.pitt.cs.cs1635.mdb91.notetaker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;

import static edu.pitt.cs.cs1635.mdb91.notetaker.R.color.black;

public class optionsActivity extends AppCompatActivity {

    View selectedColor;
    View selectedSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_options);

        Intent intent = getIntent();
        int selectedColorID = intent.getIntExtra("color", 0);
        int selectedWidthID = intent.getIntExtra("width", 0);

        switch(selectedColorID)
        {
            case R.color.black:
                selectedColor = findViewById(R.id.rButtonBlack);
                break;
            case R.color.blue:
                selectedColor = findViewById(R.id.rButtonBlue);
                break;
            case R.color.red:
                selectedColor = findViewById(R.id.rButtonRed);
                break;
            case R.color.green:
                selectedColor = findViewById(R.id.rButtonGreen);
        }
        ColorDrawable color = (ColorDrawable) selectedColor.getBackground();
        selectedColor.setBackgroundColor(Color.argb(color.getAlpha() - 60, Color.red(color.getColor()), Color.green(color.getColor()), Color.blue(color.getColor())));

        switch(selectedWidthID)
        {
            case 5:
                selectedSize = findViewById(R.id.rButtonFive);
                break;
            case 15:
                selectedSize = findViewById(R.id.rButtonFifteen);
                break;
            case 25:
                selectedSize = findViewById(R.id.rButtonTwentyFive);
                break;
            case 35:
                selectedSize = findViewById(R.id.rButtonThirtyFive);
        }

        color = (ColorDrawable) selectedSize.getBackground();
        selectedSize.setBackgroundColor(Color.argb(color.getAlpha() - 60, Color.red(color.getColor()), Color.green(color.getColor()), Color.blue(color.getColor())));

        LinearLayout button = (LinearLayout)findViewById(R.id.rButtonFive);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWidth(v);
            }
        });

        button = (LinearLayout)findViewById(R.id.rButtonFifteen);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setWidth(v);
            }
        });

        button = (LinearLayout)findViewById(R.id.rButtonTwentyFive);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setWidth(v);
            }
        });

        button = (LinearLayout)findViewById(R.id.rButtonThirtyFive);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setWidth(v);
            }
        });
    }

    public void setColor(View view)
    {
        if (!selectedColor.equals(view))
        {
            ColorDrawable color = (ColorDrawable) view.getBackground();
            view.setBackgroundColor(Color.argb(color.getAlpha() - 60, Color.red(color.getColor()), Color.green(color.getColor()), Color.blue(color.getColor())));

            color = (ColorDrawable) selectedColor.getBackground();
            selectedColor.setBackgroundColor(Color.argb(color.getAlpha() + 60, Color.red(color.getColor()), Color.green(color.getColor()), Color.blue(color.getColor())));

            selectedColor = view;
        }
        switch (view.getId())
        {

            case R.id.rButtonBlack:
                MainActivity.mainCanvas.setColor(R.color.black);
                break;

            case R.id.rButtonBlue:
                MainActivity.mainCanvas.setColor(R.color.blue);
                break;

            case R.id.rButtonRed:
                MainActivity.mainCanvas.setColor(R.color.red);
            break;

            case R.id.rButtonGreen:
                MainActivity.mainCanvas.setColor(R.color.green);
            break;
        }
    }

    public void setWidth(View view)
    {
        if (!selectedSize.equals(view))
        {
            ColorDrawable color = (ColorDrawable) view.getBackground();
            view.setBackgroundColor(Color.argb(color.getAlpha() - 60, Color.red(color.getColor()), Color.green(color.getColor()), Color.blue(color.getColor())));

            color = (ColorDrawable) selectedSize.getBackground();
            selectedSize.setBackgroundColor(Color.argb(color.getAlpha() + 60, Color.red(color.getColor()), Color.green(color.getColor()), Color.blue(color.getColor())));

            selectedSize = view;
        }

        switch (view.getId())
        {
            case R.id.rButtonFive:
                MainActivity.mainCanvas.setBrushWidth(5);
                break;

            case R.id.rButtonFifteen:
                MainActivity.mainCanvas.setBrushWidth(15);
                break;

            case R.id.rButtonTwentyFive:
                MainActivity.mainCanvas.setBrushWidth(25);
                break;

            case R.id.rButtonThirtyFive:
                MainActivity.mainCanvas.setBrushWidth(35);
                break;
        }
    }

}
