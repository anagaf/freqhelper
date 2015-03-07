package com.anagaf.freqhelper.ui.pages;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.app.BackStack;
import com.anagaf.freqhelper.app.Settings;
import com.anagaf.freqhelper.model.ranges.Range;
import com.anagaf.freqhelper.ui.views.RangeView;

public abstract class Page extends Fragment {
    public static final String PAGE_INDEX_KEY = "pageIndex";
    private final RangeView.Listener mRangeViewListener = new RangeView.Listener() {
        @Override
        public void onKeyChanged(long value) {
            pushCurrentStateToBackStack();
            writeValueToSettings(getActivity(), value);
            updateValue();
        }
    };
    private int mIndex;

    protected abstract TableLayout getRangesLayout();

    protected abstract void updateValue();

    protected abstract long getDefaultValue();

    protected abstract String getSettingsKey();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt(PAGE_INDEX_KEY);
    }

    public void restoreState(long value) {
        writeValueToSettings(getActivity(), value);
        updateValue();
    }

    public void pushCurrentStateToBackStack() {
        final long value = readValueFromSettings(getActivity());
        BackStack.getsInstance().push(new BackStack.Item(getIndex(), value));
    }

    protected int getIndex() {
        return mIndex;
    }

    protected void addRangeRow(LayoutInflater inflater, Range range) {
        View view = inflater.inflate(R.layout.range, null, false);
        RangeView row = (RangeView) view;
        row.setRange(range);
        row.setListener(mRangeViewListener);
        getRangesLayout().addView(row);
    }

    protected long readValueFromSettings(Context context) {
        long value = Settings.read(context, getSettingsKey());
        if (value == Range.INVALID_VALUE) {
            value = getDefaultValue();
            writeValueToSettings(context, value);
        }
        return value;
    }

    protected void writeValueToSettings(Context context, long value) {
        Settings.write(context, getSettingsKey(), value);
    }
}
