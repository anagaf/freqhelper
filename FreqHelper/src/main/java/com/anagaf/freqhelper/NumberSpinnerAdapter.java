package com.anagaf.freqhelper;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NumberSpinnerAdapter extends BaseAdapter {

    private final Context mContext;
    private final int mMinNumber;
    private final int mMaxNumber;

    public NumberSpinnerAdapter(Context context, int minNumber, int maxNumber) {
        mContext = context;
        mMinNumber = minNumber;
        mMaxNumber = maxNumber;
    }

    @Override
    public int getCount() {
        return mMaxNumber - mMinNumber + 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup viewGroup) {
        TextView textView = (TextView) convertView;
        if (textView == null) {
            textView = new TextView(mContext);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.large_text_size));
        }
        textView.setText(String.valueOf(postion + mMinNumber));
        textView.setBackgroundColor(Color.GREEN);
        return textView;
    }
}
