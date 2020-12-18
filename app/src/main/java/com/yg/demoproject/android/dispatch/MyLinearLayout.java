package com.yg.demoproject.android.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yg.demoproject.android.DispatchActivity;

import androidx.annotation.Nullable;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

//    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(DispatchActivity.TAG, "MyLinearLayout onTouchEvent: " + event.getAction());
//        Toast.makeText(this.getContext(), "MyLinearLayout onTouchEvent: " + event.getAction(), Toast.LENGTH_SHORT).show();
//        return super.onTouchEvent(event);
//        return true;
        return false;
    }
}
