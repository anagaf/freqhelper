package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;

import com.anagaf.freqhelper.model.ranges.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChannelEdit extends AbstractEdit {
    public ChannelEdit(Context context) {
        super(context);
    }

    public ChannelEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChannelEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChannel(int channel) {
        if (channel != Range.INVALID_INDEX) {
            setText(String.valueOf(channel));
        } else {
            setInvalidValue();
        }
    }

    public int getChannel() {
        if (getText().toString().isEmpty()) {
            return 0;
        } else if (isInvalidValue()) {
            return Range.INVALID_INDEX;
        } else {
            return Integer.parseInt(getText().toString());
        }
    }

    public void setMaxChannel(int maxChannel) {
        final List<InputFilter> filters = new ArrayList<>(Arrays.asList(getFilters()));
        filters.add(new MinMaxFilter(maxChannel));
        setFilters(filters.toArray(new InputFilter[filters.size()]));

        final int maxLength = (int) (Math.log10(maxChannel)+1);
        setMaxLength(maxLength);
    }

    /**
     * ******* Inner Classes *********
     */

    private class MinMaxFilter implements InputFilter {
        private static final int MIN = 1;
        private final int mMax;

        private MinMaxFilter(int max) {
            mMax = max;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int destStart, int destEnd) {
            final String replacement = source.subSequence(start, end).toString();
            String newValueString = dest.subSequence(0, destStart).toString() + replacement + dest.subSequence(destEnd, dest.length()).toString();
            CharSequence result = "";
            if (isInvalidValue(newValueString)) {
                result = null;
            } else {
                try {
                    int newValue = Integer.parseInt(newValueString);
                    if (newValue >= MIN && newValue <= mMax) {
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
