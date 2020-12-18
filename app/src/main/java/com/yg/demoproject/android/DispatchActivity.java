package com.yg.demoproject.android;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yg.demoproject.R;
import com.yg.demoproject.android.dispatch.MyButton;
import com.yg.demoproject.android.dispatch.MyLinearLayout;

public class DispatchActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "yg-dispatch";

    MyLinearLayout linearLayout;
    MyButton button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        linearLayout = findViewById(R.id.linear);
        button1 = findViewById(R.id.button1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear:
                Log.d(TAG, "MyLinearLayout onClick!");
                Toast.makeText(this, "MyLinearLayout onClick!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button1:
                Log.d(TAG, "MyButton onClick!");
                Toast.makeText(this, "MyButton onClick!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "Activity onTouchEvent: " + event.getAction());
//        Toast.makeText(this, "Activity onTouchEvent: " + event.getAction(), Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
