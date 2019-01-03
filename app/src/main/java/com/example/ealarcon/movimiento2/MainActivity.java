package com.example.ealarcon.movimiento2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  class MoverView extends View {
        float[] x ={50, 130};
        float[] y ={50, 100};
        float [] radio = {20, 30};

        Paint paint[] = new Paint[2];
        Paint p;

        int circulo = -1;
        String txt = "mueve algun circulo";


        public MoverView(Context context) {
            super(context);

            paint[0] = new Paint();
            paint[0].setAntiAlias(true);
            paint[0].setColor(Color.BLUE);


            paint[0] = new Paint();
            paint[0].setAntiAlias(true);
            paint[0].setColor(Color.BLACK);

            p = new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.GREEN);


        }

        protected  void onDraw (Canvas canvas){
            canvas.drawColor(Color.argb(255,200,200,150));
            canvas.drawText(txt,50,30,p);
            for(int i=0; i<2;i++){
                canvas.drawCircle(x[i],y[i], radio[i],  paint[i]); }
        }


        public boolean callOnClick(MotionEvent evento) {
            float getx = evento.getX();
            float gety = evento.getY();
            int accion = evento.getAction();

            if (accion == MotionEvent.ACTION_DOWN){
                for(int i=0;i<2; i++ ){
                    double cenx = getx - x[i];
                    double ceny = gety - y[i];

                    float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    if (distancia <= radio[i]){
                        circulo = i;
                        txt = "El circulo tocado es: " + i;
                        invalidate();
                    }
                }
            }
            if (accion == MotionEvent.ACTION_MOVE){
                if(circulo>-1){
                    x[circulo] = getx;
                    y[circulo] = gety;
                    invalidate();
                }
            }
            return true;
        }
    }
}
