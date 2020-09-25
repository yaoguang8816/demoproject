package com.yg.demoproject.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yg.demoproject.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.MainViewHolder> implements ItemTouchHelperListener{
    Context mContext;
    private OnItemClickListener mOnItemClickListener;

    ArrayList<MainViewItem> mList;

    public MainViewAdapter(Context context, OnItemClickListener onItemClickListener) {
        mContext = context;
        mOnItemClickListener = onItemClickListener;
    }

    public void setDataList(ArrayList<MainViewItem> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_view_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final MainViewItem item = mList.get(position);

        holder.title.setText(item.title);
        if (item.contents == null || item.contents.length() == 0) {
            holder.content.setVisibility(View.GONE);
        } else {
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(item.contents);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onItemSwiped(int position) {
        if (position < 0 || position > getItemCount()) {
            return;
        }
        mList.remove(position);
        notifyItemRemoved(position);

        // 解决 RecyclerView 删除 Item 导致位置错乱的问题
        if (position != mList.size()) {
            notifyItemRangeChanged(position, mList.size() - position);
        }
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        MainViewItem prevItem = mList.remove(fromPosition);
        mList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prevItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.contents);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MainViewItem item);
    }
}
