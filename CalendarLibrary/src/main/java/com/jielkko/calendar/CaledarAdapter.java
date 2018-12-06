package com.jielkko.calendar;

import android.view.View;
import android.view.ViewGroup;

import com.jielkko.calendar.CalendarBean;



public interface CaledarAdapter {
     View getView(View convertView, ViewGroup parentView, CalendarBean bean);
}
