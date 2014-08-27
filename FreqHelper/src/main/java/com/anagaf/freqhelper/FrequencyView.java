package com.anagaf.freqhelper;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrequencyView extends LinearLayout {

    private static final int DIGIT_COUNT = 9;
    private static final int DOT_POSITION = 3;

    private final List<TextView> mDigitViews;
    private OnFrequencyChangedListener mOnFrequencyChangedListener;

    public FrequencyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        };

        List<TextView> digitViews = new ArrayList<TextView>(DIGIT_COUNT);
        for (int i = 0; i < DIGIT_COUNT; i++) {
            if (i == DOT_POSITION) {
                addView(createDotItem(attrs));
            }
            TextView digitView = createDigitItem(context, attrs, onClickListener);
            digitViews.add(digitView);
            addView(digitView);
        }
        mDigitViews = Collections.unmodifiableList(digitViews);
    }

    public void setOnFrequencyChangedListener(OnFrequencyChangedListener onFrequencyChangedListener) {
        mOnFrequencyChangedListener = onFrequencyChangedListener;
    }

    public Frequency getFrequency() {
        BigInteger hertz = BigInteger.ZERO;
        int position = DIGIT_COUNT - 1;
        for (TextView digitView : mDigitViews) {
            final BigInteger digit = BigInteger.valueOf(Integer.parseInt(digitView.getText().toString()));
            hertz = hertz.add(digit.multiply(BigInteger.valueOf(10).pow(position)));
            position--;
        }
        return new Frequency(hertz.intValue());
    }

    public void setFrequency(Frequency frequency) {
        int hertz = frequency.getHertz();
        int pos = DIGIT_COUNT - 1;
        while (hertz > 0) {
            assert (pos >= 0 && pos < mDigitViews.size());
            mDigitViews.get(pos).setText(String.valueOf(hertz % 10));
            hertz = hertz / 10;
            --pos;
        }
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        TextView textView = (TextView) view;
        popupMenu.setOnMenuItemClickListener(new OnPopupMenuItemClickListener(textView));
        for (int i = 0; i < 10; i++) {
            popupMenu.getMenu().add(Menu.NONE, i, Menu.NONE, String.valueOf(i));
        }
        popupMenu.show();
    }

    private TextView createDotItem(AttributeSet attrs) {
        TextView textView = new TextView(getContext(), attrs);

        textView.setText(".");

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);

        return textView;
    }

    private TextView createDigitItem(Context context, AttributeSet attrs, OnClickListener onClickListener) {
        TextView textView = new TextView(context, attrs);

        textView.setText("0");

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.leftMargin = getContext().getResources().getDimensionPixelSize(R.dimen.content_item_small_gap);
        lp.rightMargin = getContext().getResources().getDimensionPixelSize(R.dimen.content_item_small_gap);
        textView.setLayoutParams(lp);

        if (onClickListener != null) {
            textView.setOnClickListener(onClickListener);
        }

        return textView;
    }

    /**
     * ******* Inner Classes *********
     */

    public static interface OnFrequencyChangedListener {
        public void onFrequencyChanged(Frequency frequency);
    }

    private class OnPopupMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private TextView mTextView;

        private OnPopupMenuItemClickListener(TextView textView) {
            mTextView = textView;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            mTextView.setText(String.valueOf(menuItem.getItemId()));
            mOnFrequencyChangedListener.onFrequencyChanged(getFrequency());
            return true;
        }
    }
}
