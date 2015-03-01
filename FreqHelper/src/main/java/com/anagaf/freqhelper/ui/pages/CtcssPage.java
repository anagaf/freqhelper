package com.anagaf.freqhelper.ui.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.Frequency;
import com.anagaf.freqhelper.model.ranges.Ctcss38;
import com.anagaf.freqhelper.model.ranges.Ctcss39;
import com.anagaf.freqhelper.model.ranges.Ctcss64;
import com.anagaf.freqhelper.ui.views.ValueComponentEdit;

public class CtcssPage extends AbstractFrequencyPage {
    private TableLayout mRangesLayout;
    private ValueComponentEdit mFrequencyHzEdit;
    private ValueComponentEdit mFrequencyDeciHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ctcss, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ctcss_ranges_layout);

        mFrequencyHzEdit = (ValueComponentEdit) view.findViewById(R.id.ctcss_hz_edit);
        mFrequencyHzEdit.setListener(getValueComponentEditListener());

        mFrequencyDeciHzEdit = (ValueComponentEdit) view.findViewById(R.id.ctcss_dhz_edit);
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
        mFrequencyHzEdit.setValueComponent(frequency.getHertzComponent());
        mFrequencyDeciHzEdit.setValueComponent(frequency.getDeciHertzComponent());
        updateRanges();
    }

    @Override
    protected long getValue() {
        final Integer hz = mFrequencyHzEdit.getValueComponent();
        final Integer deciHz = mFrequencyDeciHzEdit.getValueComponent();
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
