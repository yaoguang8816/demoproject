package com.yg.demoproject.java.comparator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yg.demoproject.R;
import com.yg.demoproject.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ComparatorActivity extends AppCompatActivity {

    ArrayList<TestEntry> testEntries = new ArrayList<>();

    TextView mTextView;
    TextView mTextView2;

    Comparator<TestEntry> idComparator = new Comparator<TestEntry>() {
        @Override
        public int compare(TestEntry o1, TestEntry o2) {
            if (o1.id < o2.id) return 1;
            if (o1.id > o2.id) return -1;
            return 0;
        }
    };

    Comparator<TestEntry> nameComparator = new Comparator<TestEntry>() {
        @Override
        public int compare(TestEntry o1, TestEntry o2) {
            if (o1.name.compareTo(o2.name) > 0) return 1;
            if (o1.name.compareTo(o2.name) < 0) return -1;
            return 0;
        }
    };

    Comparator<TestEntry> key2Comparator = new Comparator<TestEntry>() {
        @Override
        public int compare(TestEntry o1, TestEntry o2) {
            if (o1.key2 < o2.key2) return 1;
            if (o1.key2 > o2.key2) return -1;
            return 0;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparator);
        mTextView = findViewById(R.id.textView);
        mTextView2 = findViewById(R.id.textView2);

        init();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run();
            }
        });
    }

    void run() {
        int type = Utils.randomInt(0,3);
        String typeName = "id";
        switch (type) {
            case 0:
                Collections.sort(testEntries, idComparator);
                typeName = "id";
                break;
            case 1:
                Collections.sort(testEntries, nameComparator);
                typeName = "name";
                break;
            case 2:
                Collections.sort(testEntries, key2Comparator);
                typeName = "key2";
                break;
            default:
                break;

        }
        StringBuilder sb = new StringBuilder();
        sb.append("Sort by ");
        sb.append(typeName);
        sb.append(":\n");
        sb.append(listToString(testEntries));
        mTextView2.setText(sb);
    }

    void init() {
        int size = Utils.randomInt(10, 18);
        for (int i = 0; i < size; i++) {
            TestEntry entry = new TestEntry();
            entry.id = Utils.randomInt(0, 100);
            entry.name = Utils.randomString(10);
            entry.key2 = Utils.randomFloat();
            testEntries.add(entry);
        }
        mTextView.setText(listToString(testEntries));
    }

    String listToString(ArrayList<TestEntry> entries) {
        StringBuilder sb = new StringBuilder();
        TestEntry entry;
        for (int i = 0; i < entries.size(); i++) {
            entry = entries.get(i);
            sb.append(entry.id);
            sb.append('\t');
            sb.append('\t');
            sb.append(entry.name);
            sb.append('\t');
            sb.append('\t');
            sb.append(entry.key2);
            sb.append('\n');
        }
        return sb.toString();
    }

    class TestEntry {
        int id;
        String name;
        float key2;
    }
}
