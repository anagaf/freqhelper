package com.anagaf.freqhelper.ui.pages;

import com.anagaf.freqhelper.ui.views.BaseEdit;
import com.anagaf.freqhelper.ui.views.RangeView;

public abstract class AbstractFrequencyPage extends Page {

    final BaseEdit.Listener mValueComponentEditListener = new BaseEdit.Listener() {
        @Override
        public void onValueChanged(int value) {
            pushCurrentStateToBackStack();
            writeValueToSettings(getActivity(), getValue());
            updateRanges();
        }
    };

    protected abstract long getValue();

    protected BaseEdit.Listener getValueComponentEditListener() {
        return mValueComponentEditListener;
    }

    protected void updateRanges() {
        final long value = getValue();
        for (int i = 0; i < getRangesLayout().getChildCount(); i++) {
            final RangeView row = (RangeView) getRangesLayout().getChildAt(i);
            row.setValue(value);
        }
    }
}
