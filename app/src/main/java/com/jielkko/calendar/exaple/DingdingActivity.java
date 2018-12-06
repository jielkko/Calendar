package com.jielkko.calendar.exaple;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jielkko.calendar.CaledarAdapter;
import com.jielkko.calendar.CalendarBean;
import com.jielkko.calendar.CalendarDateView;
import com.jielkko.calendar.CalendarUtil;
import com.jielkko.calendar.CalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jielkko.calendar.exaple.Utils.px;


public class DingdingActivity extends AppCompatActivity {
    public Context mContext;
    @BindView(R.id.calendarDateView)
    CalendarDateView mCalendarDateView;
    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = DingdingActivity.this;
        List<String> markList = new ArrayList<>();
        markList.add("2018-12-4");
        markList.add("2018-12-5");
        markList.add("2018-12-6");

        markList.add("2018-12-14");
        markList.add("2018-12-15");
        markList.add("2018-12-16");

        markList.add("2018-12-22");
        markList.add("2018-12-25");
        markList.add("2018-12-28");
        mCalendarDateView.setMark(markList);

        mCalendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {

                TextView mText;
                ImageView mDot;



                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_calendar, null);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px(48), px(48));
                    convertView.setLayoutParams(params);
                }
                mText = (TextView) convertView.findViewById(R.id.text);
                mDot = (ImageView) convertView.findViewById(R.id.dot);


                mText.setText("" + bean.day);
                if (bean.mothFlag != 0) {
                    mText.setTextColor(0xff999999);
                } else {
                    mText.setTextColor(0xff000000);
                }
                if (bean.isMark) {
                    //mText.setTextColor(0xfffa4a32);
                    mDot.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dot_red));
                }else{
                    mDot.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dot_white));

                }

                return convertView;
            }
        });

        mCalendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, CalendarBean bean) {
                mTitle.setText(bean.year + "/" + getDisPlayNumber(bean.moth) + "/" + getDisPlayNumber(bean.day));
            }
        });

        int[] data = CalendarUtil.getYMD(new Date());
        mTitle.setText(data[0] + "/" + data[1] + "/" + data[2]);

        mList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(DingdingActivity.this).inflate(android.R.layout.simple_list_item_1, null);
                }

                TextView textView = (TextView) convertView;
                textView.setText("item" + position);

                return convertView;
            }
        });

    }

    private String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
