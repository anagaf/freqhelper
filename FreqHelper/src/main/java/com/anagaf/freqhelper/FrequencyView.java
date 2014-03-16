package com.anagaf.freqhelper;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FrequencyView extends LinearLayout {

    private static final int MHZ_DIGIT_COUNT = 3;
    private static final int HZ_DIGIT_COUNT = 6;

    public FrequencyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        };

        for (int i = 0; i < MHZ_DIGIT_COUNT; i++) {
            addView(createDigitItem(attrs, onClickListener, 0));
        }
        addView(createDotItem(attrs));
        for (int i = 0; i < HZ_DIGIT_COUNT; i++) {
            addView(createDigitItem(attrs, onClickListener, 0));
        }
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
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

    private TextView createDigitItem(AttributeSet attrs, OnClickListener onClickListener, int digit) {
        TextView textView = new TextView(getContext(), attrs);

        textView.setText(String.valueOf(digit));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.leftMargin = getContext().getResources().getDimensionPixelSize(R.dimen.content_item_small_gap);
        lp.rightMargin = getContext().getResources().getDimensionPixelSize(R.dimen.content_item_small_gap);
        textView.setLayoutParams(lp);

        if (onClickListener != null) {
            textView.setOnClickListener(onClickListener);
        }

        return textView;
    }
}
