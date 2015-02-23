package com.anagaf.freqhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.anagaf.freqhelper.model.ranges.Range;

public abstract class FrequencyPage extends Page {

    final FrequencyComponentEdit.Listener mFrequencyComponentEditListener = new FrequencyComponentEdit.Listener() {
        @Override
        public void onValueChanged(int value) {
            pushCurrentStateToBackStack();
            writeValueToSettings(getActivity(), getValue());
            updateRanges();
        }
    };

    final RangeView.Listener mRangeViewListener = new RangeView.Listener() {
        @Override
        public void onKeyChanged(long value) {
            pushCurrentStateToBackStack();
            writeValueToSettings(getActivity(), value);
            updateFrequency();
        }
    };

    protected abstract void updateFrequency();

    protected abstract long getValue();

    protected abstract long getDefaultValue();

    protected abstract String getSettingsKey();

    protected void addRangeRow(LayoutInflater inflater, Range range) {
        View view = inflater.inflate(R.layout.range, null, false);
        RangeView row = (RangeView) view;
        row.setRange(range);
        row.setListener(mRangeViewListener);
        getRangesLayout().addView(row);
    }

    protected void updateRanges() {
        final long value = getValue();
        for (int i = 0; i < getRangesLayout().getChildCount(); i++) {
            final RangeView row = (RangeView) getRangesLayout().getChildAt(i);
            row.setValue(value);
        }
    }

    @Override
    public void restoreState(long value) {
        writeValueToSettings(getActivity(), value);
        updateFrequency();
    }

    public void pushCurrentStateToBackStack() {
        final long value = readValueFromSettings(getActivity());
        BackStack.getsInstance().push(new BackStack.Item(getIndex(), value));
    }

    protected static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }

    protected long readValueFromSettings(Context context) {
        Long value = Settings.read(context, getSettingsKey());
        if (value == Range.INVALID_VALUE) {
            value = getDefaultValue();
            writeValueToSettings(context, value);
        }
        return value;
    }

    protected void writeValueToSettings(Context context, long value) {
        Settings.write(context, getSettingsKey(), value);
    }

    protected FrequencyComponentEdit.Listener getFrequencyComponentEditListener() {
        return mFrequencyComponentEditListener;
    }
}
