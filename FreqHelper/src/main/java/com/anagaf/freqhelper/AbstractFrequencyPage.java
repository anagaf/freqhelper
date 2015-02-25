package com.anagaf.freqhelper;

public abstract class AbstractFrequencyPage extends Page {

    final ValueEdit.Listener mValueComponentEditListener = new ValueEdit.Listener() {
        @Override
        public void onValueChanged(int value) {
            pushCurrentStateToBackStack();
            writeValueToSettings(getActivity(), getValue());
            updateRanges();
        }
    };

    protected abstract long getValue();

    protected ValueEdit.Listener getValueComponentEditListener() {
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
