package com.anagaf.freqhelper;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;

import com.anagaf.freqhelper.model.Range;

public class RangeChannelEdit extends BaseEdit {
    private final String EMPTY_CHANNEL_NUMBER = "--";

    public RangeChannelEdit(Context context) {
        super(context);
    }

    public RangeChannelEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RangeChannelEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setValue(int value) {
        final String channelString;
        if (value == Range.INVALID_CHANNEL) {
            channelString = EMPTY_CHANNEL_NUMBER;
        } else {
            channelString = String.valueOf(value);
        }
        setText(channelString);
    }

    public void setMaxChannel(int maxChannel) {
        final InputFilter channelInputFilter = new MinMaxFilter(1, maxChannel);
        setFilters(new InputFilter[]{channelInputFilter});
    }

    /**
     * ******* Inner Classes *********
     */

    private class MinMaxFilter implements InputFilter {
        private int mMin;
        private int mMax;

        private MinMaxFilter(int min, int max) {
            mMin = min;
            mMax = max;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            final String replacement = source.subSequence(start, end).toString();
            String newValueString = dest.subSequence(0, dstart).toString() + replacement + dest.subSequence(dend, dest.length()).toString();
            CharSequence result = "";
            if (newValueString.equals(EMPTY_CHANNEL_NUMBER)) {
                result = null;
            } else {
                try {
                    int newValue = Integer.parseInt(newValueString);
                    if (newValue >= mMin && newValue <= mMax) {
                        result = null;
                    }
                } catch (NumberFormatException ex) {
                    result = "";
                }
            }
            return result;
        }
    }

}
