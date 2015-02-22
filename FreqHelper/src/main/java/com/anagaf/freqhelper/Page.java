package com.anagaf.freqhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Range;

public abstract class Page extends Fragment {
    public static final String PAGE_INDEX_KEY = "pageIndex";

    private int mIndex;

    final FrequencyComponentEdit.Listener mFrequencyComponentEditListener = new FrequencyComponentEdit.Listener() {
        @Override
        public void onValueChanged(int value) {
            pushCurrentStateToBackStack();
            final Frequency frequency = getFrequency();
            writeFrequencyToSettings(getActivity(), frequency);
            updateRanges();
        }
    };

    final RangeView.Listener mRangeViewListener = new RangeView.Listener() {
        @Override
        public void onFrequencyChanged(Frequency frequency) {
            pushCurrentStateToBackStack();
            writeFrequencyToSettings(getActivity(), frequency);
            updateFrequency();
        }
    };

    protected abstract TableLayout getRangesLayout();

    protected abstract Frequency readFrequencyFromSettings(Context context);

    protected abstract void writeFrequencyToSettings(Context context, Frequency frequency);

    protected abstract void updateFrequency();

    protected abstract Frequency getFrequency();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt(PAGE_INDEX_KEY);
    }

    protected FrequencyComponentEdit.Listener getFrequencyComponentEditListener() {
        return mFrequencyComponentEditListener;
    }

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

    public void restoreFrequency(Frequency frequency) {
        writeFrequencyToSettings(getActivity(), frequency);
        updateFrequency();
    }

    public void pushCurrentStateToBackStack() {
        final Frequency currentFrequency = readFrequencyFromSettings(getActivity());
        BackStack.getsInstance().push(new BackStack.Item(mIndex, currentFrequency));
    }

    protected static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }
}
