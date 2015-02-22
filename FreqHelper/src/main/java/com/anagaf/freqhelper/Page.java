package com.anagaf.freqhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Key;
import com.anagaf.freqhelper.model.Range;

public abstract class Page extends Fragment {
    public static final String PAGE_INDEX_KEY = "pageIndex";

    private int mIndex;

    final FrequencyComponentEdit.Listener mFrequencyComponentEditListener = new FrequencyComponentEdit.Listener() {
        @Override
        public void onValueChanged(int value) {
            pushCurrentStateToBackStack();
            writeKeyToSettings(getActivity(), getKey());
            updateRanges();
        }
    };

    final RangeView.Listener mRangeViewListener = new RangeView.Listener() {
        @Override
        public void onKeyChanged(Key key) {
            pushCurrentStateToBackStack();
            writeKeyToSettings(getActivity(), key);
            updateKey();
        }
    };

    protected abstract TableLayout getRangesLayout();

    protected abstract void updateKey();

    protected abstract Key getKey();

    protected abstract Key getDefaultKey();

    protected abstract String getSettingsKey();

    protected abstract Key createKey(Long value);

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
        final Key key = getKey();
        for (int i = 0; i < getRangesLayout().getChildCount(); i++) {
            final RangeView row = (RangeView) getRangesLayout().getChildAt(i);
            row.setKey(key);
        }
    }

    public void restoreFrequency(Key key) {
        writeKeyToSettings(getActivity(), key);
        updateKey();
    }

    public void pushCurrentStateToBackStack() {
        final Key currentKey = readKeyFromSettings(getActivity());
        BackStack.getsInstance().push(new BackStack.Item(mIndex, currentKey));
    }

    protected static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }

    protected Key readKeyFromSettings(Context context) {
        Key key;
        Long value = Settings.read(context, getSettingsKey());
        if (value == null) {
            key = getDefaultKey();
            writeKeyToSettings(context, key);
        } else {
            key = createKey(value);
        }
        return key;
    }

    protected void writeKeyToSettings(Context context, Key key) {
        Settings.write(context, getSettingsKey(), key.getValue());
    }
}
