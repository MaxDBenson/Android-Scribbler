package edu.pitt.cs.cs1635.mdb91.notetaker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class pathPaintPair
{
    private Path path;
    private Paint paint;
    public pathPaintPair(Paint pnt)
    {
        path = new Path();
        paint = pnt;
    }

    public pathPaintPair()
    {
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.BLACK); //default to black
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    public Path getPath()
    {
        return path;
    }

    public Paint getPaint()
    {
        return paint;
    }
}

class myCanvas extends View
{
    private int currColor;
    private int currWidth;
    ArrayList<pathPaintPair> pathList;
    public myCanvas(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        currColor = R.color.black;
        currWidth = 5;
        pathList = new ArrayList<pathPaintPair>();
        pathPaintPair initPair = new pathPaintPair();
        pathList.add(initPair);
    }

    public void setColor(int newColor)
    {
        currColor = newColor;
        pathList.get(pathList.size()-1).getPaint().setColor(ContextCompat.getColor(getContext(), newColor));
    }

    public void setBrushWidth (int newWidth)
    {
        currWidth = newWidth;
        pathList.get(pathList.size()-1).getPaint().setStrokeWidth(newWidth);
    }

    public int getCurrColor()
    {
        return currColor;
    }

    public int getCurrWidth()
    {
        return currWidth;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        for (pathPaintPair pair : pathList)
             canvas.drawPath(pair.getPath(), pair.getPaint());

    }
}


public class MainActivity extends AppCompatActivity {

    static myCanvas mainCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainCanvas = new myCanvas(this, null);
        ViewGroup mainLayout = (ViewGroup)findViewById(R.id.main_layout);
        mainLayout.addView(mainCanvas, 0);
        mainCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int actionType = event.getActionMasked();
                switch (actionType) {
                    case MotionEvent.ACTION_DOWN:
                        mainCanvas.pathList.get(mainCanvas.pathList.size() - 1).getPath().setLastPoint(event.getX(), event.getY());
                        break;

                    case MotionEvent.ACTION_UP:
                        pathPaintPair newPair = new pathPaintPair(new Paint(mainCanvas.pathList.get(mainCanvas.pathList.size() - 1).getPaint()));
                        mainCanvas.pathList.add(newPair);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        mainCanvas.pathList.get(mainCanvas.pathList.size() - 1).getPath().lineTo(event.getX(), event.getY());
                        mainCanvas.invalidate();
                        break;

                    default:
                        return false;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                gotoOptions();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void gotoOptions()
    {
        Intent intent = new Intent(this, optionsActivity.class);
        intent.putExtra("color", mainCanvas.getCurrColor());
        intent.putExtra("width", mainCanvas.getCurrWidth());
        startActivity(intent);
    }


    public void clearCanvas(View view)
    {
        int listSize = mainCanvas.pathList.size();
        //remove all paths except current one
        for (int i = 0; i < listSize-1; i++)
            mainCanvas.pathList.remove(0);
        //re-draw
        mainCanvas.invalidate();
    }
}
