package com.anagaf.freqhelper;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Range;

public abstract class Page extends Fragment {
    public static final String PAGE_INDEX_KEY = "pageIndex";

    private TableLayout mRangesLayout;

    public void setRangesLayout(TableLayout rangesLayout) {
        mRangesLayout = rangesLayout;
    }

    protected abstract Frequency getFrequency();

    public abstract void restoreFrequency(Frequency frequency);

    protected void addRangeRow(LayoutInflater inflater, RangeView.Listener listener, Range range) {
        View view = inflater.inflate(R.layout.range, null, false);
        RangeView row = (RangeView) view;
        row.setRange(range);
        row.setListener(listener);
        mRangesLayout.addView(row);
    }

    protected void updateRanges() {
        final Frequency frequency = getFrequency();
        for (int i = 0; i < mRangesLayout.getChildCount(); i++) {
            final RangeView row = (RangeView) mRangesLayout.getChildAt(i);
            row.setFrequency(frequency);
        }
    }

    protected static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }
}
