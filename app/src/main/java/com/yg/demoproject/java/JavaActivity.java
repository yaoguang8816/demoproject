package com.yg.demoproject.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.yg.demoproject.R;
import com.yg.demoproject.java.comparator.ComparatorActivity;
import com.yg.demoproject.java.runtime.RuntimeActivity;
import com.yg.demoproject.java.tree.FindTreeNode;
import com.yg.demoproject.view.MainItemTouchHelperCallback;
import com.yg.demoproject.view.MainViewAdapter;
import com.yg.demoproject.view.MainViewItem;

import java.util.ArrayList;

public class JavaActivity extends AppCompatActivity implements MainViewAdapter.OnItemClickListener {
    RecyclerView mRecyclerView;
    final ArrayList<MainViewItem> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        mRecyclerView = findViewById(R.id.recyclerView);

        initView();
    }

    void initData() {
        mList.add(new MainViewItem("Deque", ""));
        mList.add(new MainViewItem("HashMap", "test content "));
        mList.add(new MainViewItem("Volatile", "test content "));
        mList.add(new MainViewItem("final", "test content "));
        mList.add(new MainViewItem("多态", "test content "));
        mList.add(new MainViewItem("引用", "test content "));
        mList.add(new MainViewItem("内部类", "test content "));
        mList.add(new MainViewItem("String", "test content "));
        mList.add(new MainViewItem("static", "test content "));
        mList.add(new MainViewItem("反射", "test content "));
        mList.add(new MainViewItem("类加载", "test content "));
        mList.add(new MainViewItem("ThreadLocal", "test content "));
        mList.add(new MainViewItem("synchronized与Lock", "test content "));
        mList.add(new MainViewItem("线程池", "test content "));
        mList.add(new MainViewItem("RunTime", "test content ", new Intent(this, RuntimeActivity.class)));
        mList.add(new MainViewItem("Comparator", "test content ", new Intent(this, ComparatorActivity.class)));
        mList.add(new MainViewItem("other", "test content "));
        mList.add(new MainViewItem("FindTreeNode", "test content ", 1));
    }

    void initView() {
        initData();

        MainViewAdapter adapter = new MainViewAdapter(this, this);
        ItemTouchHelper.Callback callback = new MainItemTouchHelperCallback(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setDataList(mList);
    }

    @Override
    public void onItemClick(MainViewItem item) {
        if (item.getTarget() != null) {
            startActivity(item.getTarget());
        } else if (item.getId() == 1) {
            FindTreeNode findNode = new FindTreeNode();
            String s = findNode.findNode("x");
            Toast.makeText(this, "result: " + s, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Clicked " + item.getTitle() + ", contents is:" + item.getContents(), Toast.LENGTH_SHORT).show();
        }
    }
}
