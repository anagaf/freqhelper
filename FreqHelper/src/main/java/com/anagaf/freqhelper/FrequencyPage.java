package com.anagaf.freqhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.keys.Frequency;
import com.anagaf.freqhelper.model.ranges.Range;

public abstract class FrequencyPage extends Page {

    final FrequencyComponentEdit.Listener mFrequencyComponentEditListener = new FrequencyComponentEdit.Listener() {
        @Override
        public void onValueChanged(int value) {
            pushCurrentStateToBackStack();
            writeFrequencyToSettings(getActivity(), getFrequency());
            updateRanges();
        }
    };

    final RangeView.Listener mRangeViewListener = new RangeView.Listener() {
        @Override
        public void onKeyChanged(Frequency frequency) {
            pushCurrentStateToBackStack();
            writeFrequencyToSettings(getActivity(), frequency);
            updateFrequency();
        }
    };

    protected abstract void updateFrequency();

    protected abstract Frequency getFrequency();

    protected abstract Frequency getDefaultKey();

    protected abstract String getSettingsKey();

    protected abstract Frequency createKey(Long value);

    protected void addRangeRow(LayoutInflater inflater, Range range) {
        View view = inflater.inflate(R.layout.range, null, false);
        RangeView row = (RangeView) view;
        row.setRange(range);
        row.setListener(mRangeViewListener);
        getRangesLayout().addView(row);
    }

    protected void updateRanges() {
        final Frequency frequency = getFrequency();
        for (int i = 0; i < getRangesLayout().getChildCount(); i++) {
            final RangeView row = (RangeView) getRangesLayout().getChildAt(i);
            row.setFrequency(frequency);
        }
    }


    @Override
    public void restoreState(long value) {
        writeFrequencyToSettings(getActivity(), Frequency.newFrequency(value));
        updateFrequency();
    }

    public void pushCurrentStateToBackStack() {
        final Frequency frequency = readFrequencyFromSettings(getActivity());
        BackStack.getsInstance().push(new BackStack.Item(getIndex(), frequency.getDeciHertz()));
    }

    protected static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }

    protected Frequency readFrequencyFromSettings(Context context) {
        Frequency frequency;
        Long value = Settings.read(context, getSettingsKey());
        if (value == null) {
            frequency = getDefaultKey();
            writeFrequencyToSettings(context, frequency);
        } else {
            frequency = createKey(value);
        }
        return frequency;
    }

    protected void writeFrequencyToSettings(Context context, Frequency frequency) {
        Settings.write(context, getSettingsKey(), frequency.getDeciHertz());
    }

    protected FrequencyComponentEdit.Listener getFrequencyComponentEditListener() {
        return mFrequencyComponentEditListener;
    }
}
