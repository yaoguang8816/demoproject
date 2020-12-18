package com.yg.demoproject.android.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;


import com.yg.demoproject.android.DispatchActivity;

import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(DispatchActivity.TAG, "Button onTouchEvent: " + event.getAction());
//        Toast.makeText(this.getContext(), "Button onTouchEvent: " + event.getAction(), Toast.LENGTH_SHORT).show();
//        return super.onTouchEvent(event);
        return false;
    }
}
