package com.anagaf.freqhelper.ui.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.FrequencyComponentEdit;
import com.anagaf.freqhelper.model.ranges.Ctcss38;
import com.anagaf.freqhelper.model.ranges.Ctcss39;
import com.anagaf.freqhelper.model.ranges.Ctcss64;
import com.anagaf.freqhelper.model.Frequency;

public class CtcssPage extends AbstractFrequencyPage {
    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mFrequencyHzEdit;
    private FrequencyComponentEdit mFrequencyDeciHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ctcss, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

        mFrequencyHzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.setListener(getValueComponentEditListener());

        mFrequencyDeciHzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_deci_hz_edit);
        mFrequencyDeciHzEdit.setListener(getValueComponentEditListener());

        addRangeRow(inflater, new Ctcss38());
        addRangeRow(inflater, new Ctcss39());
        addRangeRow(inflater, new Ctcss64());

        updateValue();

        return view;
    }

    @Override
    protected TableLayout getRangesLayout() {
        return mRangesLayout;
    }

    @Override
    protected void updateValue() {
        final long value = readValueFromSettings(getActivity());
        final Frequency frequency = Frequency.newFrequency(value);
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
        mFrequencyDeciHzEdit.setValue(frequency.getDeciHertzComponent());
        updateRanges();
    }

    @Override
    protected long getValue() {
        final Integer hz = mFrequencyHzEdit.getValue();
        final Integer deciHz = mFrequencyDeciHzEdit.getValue();
        return Frequency.getCtcssFrequencyDecihertz(hz, deciHz);
    }

    @Override
    protected long getDefaultValue() {
        return new Ctcss38().getValue(1);
    }

    @Override
    protected String getSettingsKey() {
        return "CtcssFrequency";
    }

}
