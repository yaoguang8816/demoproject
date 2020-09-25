package com.yg.demoproject;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileRWActivity extends AppCompatActivity implements View.OnClickListener {
    protected long LOG_CLEAR_INTERVAL =  60 * 1000;//for test

    String mSP = "";
    String mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_rw);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        mFile = this.getFilesDir().getAbsolutePath() + "/" + "cpu";
    }

    public void clearSystemLog() {
        String s = mSP;
        String[] files = s.split(";");
        long currentTime = System.currentTimeMillis();
        for (String fileName : files) {
            long time = getTimeFromFileName(fileName);
            if ((currentTime - time) >= LOG_CLEAR_INTERVAL) {
                File file = new File(mFile + fileName);
                if (file.exists() && file.isFile()) {
                    if (file.delete()) {
                        if (s.startsWith(fileName)) {
                            s = s.replaceAll(fileName + ";", "");
                        } else {
                            s = s.replaceAll(";" + fileName, "");
                        }
                        Toast.makeText(this, "Del file " + mFile + fileName + " success", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        mSP = s;
    }

    private long getTimeFromFileName(String fileName) {
        long result = -1;

        try {
            result = Long.valueOf(getValueFromFileName(fileName, "t"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getValueFromFileName(String fileName, String label) {
        String result = null;
        if (fileName == null) {
            return result;
        }
        int delimiterIndex = fileName.indexOf("_");
        if (delimiterIndex < 0) {
            return result;
        }

        for (; delimiterIndex < fileName.length() - 2; ) {
            if (!fileName.substring(delimiterIndex + 1, delimiterIndex + 2).equals(label)) {
                delimiterIndex = fileName.indexOf("_", delimiterIndex + 1);
                if (delimiterIndex <= 0) {
                    return result;
                }
                continue;
            }
            //指向“s”下一位,如果不是数字，寻找下一个
            delimiterIndex += 2;
            if (fileName.charAt(delimiterIndex) < '0' || fileName.charAt(delimiterIndex) > '9') {
                continue;
            }
            int nextDelimiterIndex = fileName.indexOf("_", delimiterIndex);
            if (nextDelimiterIndex < 0) {
                result = fileName.substring(delimiterIndex);
            } else {
                result = fileName.substring(delimiterIndex,nextDelimiterIndex);
            }
            break;
        }
        return result;
    }

    public void produceSystemLog() {
        FileOutputStream fileOutputStream = null;
        try {
            String filePostfix = "_t" + System.currentTimeMillis()
                    + "_s" + 12;
            fileOutputStream = new FileOutputStream(mFile + filePostfix);
            byte[] b={'a','f',32,33,34,'h'};
            fileOutputStream.write(b);

            if (mSP != null && mSP.length() > 1) {
                mSP += ";";
            }
            mSP += filePostfix;
            Toast.makeText(this, "Make file " + mFile + filePostfix + " success", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                produceSystemLog();
                break;
            case R.id.button2:
                clearSystemLog();
                break;
        }
    }
}
