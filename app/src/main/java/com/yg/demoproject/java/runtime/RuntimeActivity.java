package com.yg.demoproject.java.runtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.yg.demoproject.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class RuntimeActivity extends AppCompatActivity {
    public static final String TAG = "RuntimeActivity";
    public static final String A = "logcat -b kernel -v threadtime -v printable -v uid -d *:v";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = execCommand(A);
                copyToFile(inputStream, new File(getCacheDir().toString() + "/teset"));
            }
        });
        thread.start();
//        execCommand(A);
    }

    public static InputStream execCommand(String cmd) {
        Process p = null;
        BufferedReader result = null;
        try {
            p = Runtime.getRuntime().exec(cmd);
//            int status = p.waitFor();
//            if (status != 0) {
//                Log.e(TAG, String.format("// Shell command %s status was %s",
//                        cmd, status));
//            }
            boolean res = p.waitFor(10, TimeUnit.SECONDS);
            return p.getInputStream();
        } catch (Exception e) {
            Log.e(TAG, "// Exception from " + cmd + " : " + e.getMessage());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (p != null) {
                    p.destroy();
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException : " + e.toString());
            }
        }
        return null;
    }

    /**
     * Copy data from a source stream to {@code destFile}.
     *
     * @return true if succeed, false if failed.
     */
    public static boolean copyToFile(InputStream inputStream, File destFile) {
        if (destFile.exists()) {
            if (!destFile.delete()) {
                return false;
            }
        }
        boolean ret = false;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                out.write(buffer, 0, bytesRead);
            }
            ret = true;
        } catch (Exception e) {
            Log.e(TAG, "copyToFile", e);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.getFD().sync();
                    out.close();
                } catch (IOException e) {
                    Log.e(TAG, "FileUtil", e);
                }
            }
        }

        return ret;
    }
}
