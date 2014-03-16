package com.anagaf.freqhelper;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class FrequencyView extends LinearLayout {

    private static final int MHZ_DIGIT_COUNT = 3;
    private static final int HZ_DIGIT_COUNT = 6;

    public FrequencyView(Context context) {
        super(context);
        init(context);
    }

    public FrequencyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        SpinnerAdapter adapter = new NumberSpinnerAdapter(context, 0, 9);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < MHZ_DIGIT_COUNT; i++) {
            Spinner spinner = new Spinner(context);
            spinner.setLayoutParams(layoutParams);
            spinner.setAdapter(adapter);
            addView(spinner);
        }

        TextView dotTextView = new TextView(context);
        dotTextView.setText(".");
        dotTextView.setLayoutParams(layoutParams);
        addView(dotTextView);

        for (int i = 0; i < HZ_DIGIT_COUNT; i++) {
            Spinner spinner = new Spinner(context);
            spinner.setLayoutParams(layoutParams);
            spinner.setBackgroundColor(Color.YELLOW);
            spinner.setAdapter(adapter);
            addView(spinner);
        }

        setBackgroundColor(Color.RED);
    }
}
