package com.yg.demoproject.algorithmquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yg.demoproject.R;

public class BaseQuestionActivity extends AppCompatActivity {
    TextView mTitle;
    TextView mQuestionDesc;
    TextView mTestData;
    TextView mResult;

    int mQuestionType;

    IQuestion mQuestionInterface;

    Handler mWorkingHandler;
    Handler mMainHandler;

    Handler.Callback mQuestionCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //运行在工作线程
            final String result = mQuestionInterface.getResult();
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    mResult.setText(result);
                }
            });
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_question);

        mQuestionType = getIntent().getBundleExtra(QuestionUtils.EXTRA_BUNDLE).getInt(QuestionUtils.EXTRA_QUESTION_TYPE);
        mQuestionInterface = QuestionUtils.getInstance(mQuestionType);

        mTitle = findViewById(R.id.title);
        mQuestionDesc = findViewById(R.id.question_desc);
        mTestData = findViewById(R.id.test_data);
        mResult = findViewById(R.id.result);

        HandlerThread handlerThread = new HandlerThread("question_running_thread");
        handlerThread.start();
        mWorkingHandler = new Handler(handlerThread.getLooper(), mQuestionCallback);
        mMainHandler = new Handler();

        findViewById(R.id.run).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionInterface != null) {
                    mWorkingHandler.sendEmptyMessage(0);
                } else {
                    Toast.makeText(BaseQuestionActivity.this, "未解", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestData.setText(mQuestionInterface.refreshTestData());
            }
        });

        mTitle.setText(mQuestionInterface.getTitle());
        mQuestionDesc.setText(mQuestionInterface.getQuestionDesc());
        mTestData.setText(mQuestionInterface.getTestData());
    }
}
