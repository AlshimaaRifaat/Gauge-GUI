package com.example.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import de.nitri.gauge.Gauge;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Gauge gauge1 = findViewById(R.id.gauge1);
        final Gauge gauge2 = findViewById(R.id.gauge2);
        final Gauge gauge3 = findViewById(R.id.gauge3);

        gauge1.moveToValue(5);


        HandlerThread thread = new HandlerThread("GaugeDemoThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gauge1.moveToValue(-5);
            }
        }, 2800);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gauge1.moveToValue(0);
            }
        }, 5600);

        HandlerThread gauge3Thread = new HandlerThread("Gauge3DemoThread");
        gauge3Thread.start();
        Handler gauge3Handler = new Handler(gauge3Thread.getLooper());
        gauge3Handler.post(new Runnable() {
            @Override
            public void run() {
                for (float x = 0; x <= 6; x += .1) {
                    float value = (float) Math.atan(x) * 20;
                    gauge3.moveToValue(value);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}
