package com.example.chris.mypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PintarView pintar = new PintarView(this);
        setContentView(pintar);
    }

    class PintarView extends View{
        float x=50;
        float y=50;
        String accion = "nada";
        Path path = new Path();
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        public PintarView(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){
            canvas.drawColor(Color.WHITE);
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);


            if (accion=="down"){

                path.moveTo(x,y);
            }
            if (accion=="move"){
                path.lineTo(x,y);
            }
            if (accion=="arriba"){
                paint.setColor(color);
            }
            canvas.drawPath(path, paint);
        }

        public boolean onTouchEvent(MotionEvent evento){
            int axion = evento.getAction();
            x=evento.getX();
            y=evento.getY();
            if (axion==MotionEvent.ACTION_DOWN){
                accion="down";
            }
            if(axion==MotionEvent.ACTION_MOVE){
                accion="move";
            }
            if(axion==MotionEvent.ACTION_UP){
                accion="arriba";
            }
            invalidate();
            return true;
        }
    }
}
