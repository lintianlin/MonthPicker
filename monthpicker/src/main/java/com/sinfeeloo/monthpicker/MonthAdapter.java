package com.sinfeeloo.monthpicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * MonthAdapter
 * @author: mhj
 * @date: 2017/11/14 15:47
 *
 *
 */

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthViewHolder> {

    private OnMonthClickListener listener = null;

    private Context mContext;
    private List<DateBean> mList = new ArrayList<>();

    public void setOnMonthClickListener(OnMonthClickListener onMonthClickListener) {
        this.listener = onMonthClickListener;
    }

    public MonthAdapter(Context context, List<DateBean> datas) {
        this.mContext = context;
        this.mList = datas;
    }

    @Override
    public MonthAdapter.MonthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MonthViewHolder holder = new MonthViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_select_month, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MonthViewHolder holder, final int position) {
        if (ListUtils.notEmpty(mList)) {
            if (null != mList.get(position)) {
                DateBean item = mList.get(position);
                holder.radioButton.setText(item.getName());
                holder.radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != listener)
                            listener.onItemSelect(position);
                    }
                });
                holder.radioButton.setChecked(item.isSelected());
            }

        }


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnMonthClickListener {
        void onItemSelect(int position);
    }

    class MonthViewHolder extends RecyclerView.ViewHolder {

        RadioButton radioButton;

        public MonthViewHolder(View view) {
            super(view);
            radioButton = view.findViewById(R.id.rb_select_month_name);
        }
    }
}
