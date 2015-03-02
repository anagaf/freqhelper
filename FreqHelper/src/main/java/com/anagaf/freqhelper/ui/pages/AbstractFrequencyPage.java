package com.anagaf.freqhelper.ui.pages;

import com.anagaf.freqhelper.ui.views.AbstractEdit;
import com.anagaf.freqhelper.ui.views.RangeView;

public abstract class AbstractFrequencyPage extends Page {

    private final AbstractEdit.Listener mValueComponentEditListener = new AbstractEdit.Listener() {
        @Override
        public void onValueChanged() {
            pushCurrentStateToBackStack();
            writeValueToSettings(getActivity(), getValue());
            updateRanges();
        }
    };

    protected abstract long getValue();

    protected AbstractEdit.Listener getValueComponentEditListener() {
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
