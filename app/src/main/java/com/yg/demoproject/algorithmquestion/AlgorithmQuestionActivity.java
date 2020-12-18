package com.yg.demoproject.algorithmquestion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Toast;

import com.yg.demoproject.R;
import com.yg.demoproject.java.comparator.ComparatorActivity;
import com.yg.demoproject.java.runtime.RuntimeActivity;
import com.yg.demoproject.java.tree.FindTreeNode;
import com.yg.demoproject.view.MainItemTouchHelperCallback;
import com.yg.demoproject.view.MainViewAdapter;
import com.yg.demoproject.view.MainViewItem;

import java.util.ArrayList;

public class AlgorithmQuestionActivity extends AppCompatActivity implements MainViewAdapter.OnItemClickListener {
    RecyclerView mRecyclerView;
    final ArrayList<MainViewItem> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_question);

        mRecyclerView = findViewById(R.id.recyclerView);

        initView();
    }

    void initData() {
        Bundle bundle = new Bundle();
        bundle.putInt(QuestionUtils.EXTRA_QUESTION_TYPE, QuestionUtils.ARRAY_INTERATOR);
        mList.add(new MainViewItem("数组遍历", "遍历数组找出和为target的两个数下标", bundle));
        bundle.putInt(QuestionUtils.EXTRA_QUESTION_TYPE, QuestionUtils.LINKEDLIST_SUM);
        mList.add(new MainViewItem("链表求和", "返回两个链表代表的正整数的和", bundle));
        bundle.putInt(QuestionUtils.EXTRA_QUESTION_TYPE, QuestionUtils.LARGEST_UNREPEATING_SUB_STRING);
        mList.add(new MainViewItem("最大不重复子串", "返回最大不重复子串的大小", bundle));
        bundle.putInt(QuestionUtils.EXTRA_QUESTION_TYPE, QuestionUtils.MEDIAN_NUMBER);
        mList.add(new MainViewItem("中位数", "寻找两个正有序数组的中位数", bundle));
        bundle.putInt(QuestionUtils.EXTRA_QUESTION_TYPE, QuestionUtils.SIGNED_INT_REVERSE);
        mList.add(new MainViewItem("有符号整数反转", "反转给定的有符号整数", bundle));
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
        } else if (item.getBundle() != null) {
            Intent intent = new Intent();
            intent.setClass(this, BaseQuestionActivity.class);
            intent.putExtra(QuestionUtils.EXTRA_BUNDLE, item.getBundle());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Clicked " + item.getTitle() + ", contents is:" + item.getContents(), Toast.LENGTH_SHORT).show();
        }
    }
}
