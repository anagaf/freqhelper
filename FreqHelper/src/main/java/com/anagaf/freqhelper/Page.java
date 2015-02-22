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
    private TableLayout mRangesLayout;

    final RangeView.Listener mRangeViewListener = new RangeView.Listener() {
        @Override
        public void onFrequencyChanged(Frequency frequency) {
            final Frequency currentFrequency = readFrequencyFromSettings(getActivity());
            BackStack.getsInstance().push(new BackStack.Item(mIndex, currentFrequency));
            writeFrequencyToSettings(getActivity(), frequency);
            loadFrequency();
        }
    };

    protected abstract Frequency readFrequencyFromSettings(Context context);

    protected abstract void writeFrequencyToSettings(Context context, Frequency frequency);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt(PAGE_INDEX_KEY);
    }

    protected abstract void loadFrequency();

    public void setRangesLayout(TableLayout rangesLayout) {
        mRangesLayout = rangesLayout;
    }

    protected abstract Frequency getFrequency();

    protected void addRangeRow(LayoutInflater inflater, Range range) {
        View view = inflater.inflate(R.layout.range, null, false);
        RangeView row = (RangeView) view;
        row.setRange(range);
        row.setListener(mRangeViewListener);
        mRangesLayout.addView(row);
    }

    protected void updateRanges() {
        final Frequency frequency = getFrequency();
        for (int i = 0; i < mRangesLayout.getChildCount(); i++) {
            final RangeView row = (RangeView) mRangesLayout.getChildAt(i);
            row.setFrequency(frequency);
        }
    }

    protected void saveFrequency() {
        final Frequency currentFrequency = readFrequencyFromSettings(getActivity());
        BackStack.getsInstance().push(new BackStack.Item(mIndex, currentFrequency));
        final Frequency newFrequency = getFrequency();
        writeFrequencyToSettings(getActivity(), newFrequency);
    }

    public void restoreFrequency(Frequency frequency) {
        writeFrequencyToSettings(getActivity(), frequency);
        loadFrequency();
    }

    protected static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }
}
