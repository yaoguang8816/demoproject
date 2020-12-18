package com.yg.demoproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yg.demoproject.algorithmquestion.AlgorithmQuestionActivity;
import com.yg.demoproject.android.AndroidDemoActivity;
import com.yg.demoproject.java.JavaActivity;
import com.yg.demoproject.view.MainItemTouchHelperCallback;
import com.yg.demoproject.view.MainViewAdapter;
import com.yg.demoproject.view.MainViewItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MainViewAdapter.OnItemClickListener {
    RecyclerView mRecyclerView;

    ArrayList<MainViewItem> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    void initDataList() {
        int test = 0;
        mList.add(new MainViewItem("文件操作", "", new Intent(this, FileRWActivity.class)));
        mList.add(new MainViewItem("Android组件Demo", "test content ", new Intent(this, AndroidDemoActivity.class)));
        mList.add(new MainViewItem("Binder相关", "test content "));
        mList.add(new MainViewItem("算法介绍", "test content "));
        mList.add(new MainViewItem("算法题", "算法刷题", new Intent(this, AlgorithmQuestionActivity.class)));
        mList.add(new MainViewItem("Java知识", "", new Intent(this, JavaActivity.class)));
        mList.add(new MainViewItem("编程题目", "test content "));
        mList.add(new MainViewItem("test" + test++, "test content "));
        mList.add(new MainViewItem("test" + test++, "test content "));
        mList.add(new MainViewItem("test" + test++, "test content "));
        mList.add(new MainViewItem("test" + test++, "test content "));
        mList.add(new MainViewItem("test" + test++, "test content "));
        mList.add(new MainViewItem("test" + test++, "test content "));
        mList.add(new MainViewItem("test" + test++, "test content "));
    }

    void initView() {
        MainViewAdapter adapter = new MainViewAdapter(this, this);

        ItemTouchHelper.Callback callback = new MainItemTouchHelperCallback(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        initDataList();
        adapter.setDataList(mList);
    }


    void subtractLargeNumbers(String a, String b) {
        a.trim();
        boolean isBorrowed = false;
        int[] c = new int[a.length()];
        StringBuilder d = new StringBuilder();
        if (a.length() < b.length()) return;
        for (int i = 0; i < a.length(); i++) {
            int x = Integer.valueOf(a.substring(a.length() - i - 1, a.length() - i));
            int y = 0;
            if (i < b.length()) {
                y = Integer.valueOf(b.substring(b.length() - i - 1, b.length() - i));
            }
            if (isBorrowed) {
                x--;
            }
            if (x < y) {
                x = 10+x-y;
                isBorrowed = true;
            } else {
                x=x-y;
                isBorrowed = false;
            }
            c[i] = x;
        }

        boolean head = true;
        for (int j = a.length(); j > 0; j--) {
            if (head && c[j - 1] == 0) {
                continue;
            } else {
                head = false;
            }
            d.append(c[j - 1]);
        }
        d.toString();
        Log.d("baijf", "SubtractLargeNumbers result=" + d);
    }

    public static List<String> getThermalInfo() {
        List<String> result = new ArrayList<>();
        BufferedReader br = null;

        try {
            File dir = new File("/sys/class/thermal/");

            File[] files = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (Pattern.matches("thermal_zone[0-9]", file.getName())) {
                        return true;
                    }
                    return false;
                }
            });

            final int SIZE = files.length;
            String line = null;
            String type = null;
            String temp = null;
            for (int i = 0; i < SIZE; i++) {
                br = new BufferedReader(new FileReader("/sys/class/thermal/thermal_zone" + i + "/type"));
                line = br.readLine();
                if (line != null) {
                    type = line;
                }

                br = new BufferedReader(new FileReader("/sys/class/thermal/thermal_zone" + i + "/temp"));
                line = br.readLine();
                if (line != null) {
                    long temperature = Long.parseLong(line);
                    if (temperature < 0) {
                        temp = "Unknow";
                    } else {
                        temp = (float) (temperature / 1000.0) + "°C";
                    }

                }

                result.add(type + " : " + temp);
            }

            br.close();
        } catch (FileNotFoundException e) {
            result.add(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    @Override
    public void onItemClick(MainViewItem item) {
        if (item.getTarget() != null) {
            startActivity(item.getTarget());
        } else {
            Toast.makeText(this, "Clicked " + item.getTitle() + ", contents is:" + item.getContents(), Toast.LENGTH_SHORT).show();
        }
    }
}
