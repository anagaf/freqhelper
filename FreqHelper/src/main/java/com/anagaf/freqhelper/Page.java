package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TableLayout;

public abstract class Page extends Fragment {
    public static final String PAGE_INDEX_KEY = "pageIndex";

    private int mIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt(PAGE_INDEX_KEY);
    }

    protected int getIndex() {
        return mIndex;
    }

    public abstract void pushCurrentStateToBackStack();

    public abstract void restoreState(long value);

    protected abstract TableLayout getRangesLayout();
}
