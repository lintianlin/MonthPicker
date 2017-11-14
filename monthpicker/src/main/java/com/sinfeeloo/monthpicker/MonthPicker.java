package com.sinfeeloo.monthpicker;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * MonthPicker
 *
 * @author: mhj
 * @date: 2017/11/14 15:47
 * <p>
 * 月份选择控件
 */

public class MonthPicker extends LinearLayout implements View.OnClickListener {

    private Context context;
    private RecyclerView recyclerView;
    private TextView tvYear;
    private ImageView sub;
    private ImageView add;
    private int currentYear;
    private Calendar calendar;
    private List<DateBean> mList = new ArrayList<>();

    private OnMonthSelectEventListener listener = null;
    private MonthAdapter adapter;

    public MonthPicker(Context context) {
        super(context);
    }

    public MonthPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_month_picker, this);
        recyclerView = findViewById(R.id.recyclerView);
        tvYear = findViewById(R.id.tv_year);
        sub = findViewById(R.id.iv_year_sub);
        add = findViewById(R.id.iv_year_add);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
        final GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(layoutManage);
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        tvYear.setText(currentYear + "");

        adapter = new MonthAdapter(context, getAllMonth());
        recyclerView.setAdapter(adapter);
        adapter.setOnMonthClickListener(new MonthAdapter.OnMonthClickListener() {
            @Override
            public void onItemSelect(int position) {
                String month = mList.get(position).getName();
                int monthid = mList.get(position).getId();
                String year = tvYear.getText().toString();

                for (int i = 0; i < mList.size(); i++) {
                    if (position == i) {
                        mList.get(i).setSelected(true);
                    } else {
                        mList.get(i).setSelected(false);
                    }
                }
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.isComputingLayout()) {
                        adapter.notifyDataSetChanged();
                    }
                }
                if (null != listener) {
                    listener.onMonthSelected(year, String.valueOf(monthid));
                }
            }
        });


    }

    public MonthPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MonthPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private List<DateBean> getAllMonth() {
        mList.clear();
        int currentMonth = calendar.get(Calendar.MONTH);

        for (int i = 1; i <= 12; i++) {
            DateBean dateBean = new DateBean();
            dateBean.setId(i);
            dateBean.setName(i + "月");
            if (currentMonth + 1 == i) {
                dateBean.setSelected(true);
            }
            mList.add(dateBean);
        }
        return mList;
    }

    public void setOnMonthSelectEventListener(OnMonthSelectEventListener onMonthSelectEventListener) {
        this.listener = onMonthSelectEventListener;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_year_add) {
            addYear();
        } else if (i == R.id.iv_year_sub) {
            subYear();
        }
    }

    /**
     * 加年份
     */
    public void addYear() {
        calendar.add(Calendar.YEAR, 1);
        currentYear = calendar.get(Calendar.YEAR);
        tvYear.setText(String.valueOf(currentYear));
    }

    /**
     * 减年份
     */
    public void subYear() {
        calendar.add(Calendar.YEAR, -1);
        currentYear = calendar.get(Calendar.YEAR);
        tvYear.setText(String.valueOf(currentYear));
    }


    public interface OnMonthSelectEventListener {
        void onMonthSelected(String year, String month);
    }

}
