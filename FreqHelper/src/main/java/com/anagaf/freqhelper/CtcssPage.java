package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.ranges.Ctcss38;
import com.anagaf.freqhelper.model.ranges.Ctcss39;
import com.anagaf.freqhelper.model.ranges.Ctcss64;
import com.anagaf.freqhelper.model.keys.Frequency;

public class CtcssPage extends FrequencyPage {
    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mFrequencyHzEdit;
    private FrequencyDeciHertzComponentEdit mFrequencyDeciHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ctcss, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

        mFrequencyHzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.setListener(getFrequencyComponentEditListener());

        mFrequencyDeciHzEdit = (FrequencyDeciHertzComponentEdit) view.findViewById(R.id.frequency_deci_hz_edit);
        mFrequencyDeciHzEdit.setListener(getFrequencyComponentEditListener());

        addRangeRow(inflater, new Ctcss38());
        addRangeRow(inflater, new Ctcss39());
        addRangeRow(inflater, new Ctcss64());

        updateFrequency();

        return view;
    }

    @Override
    protected TableLayout getRangesLayout() {
        return mRangesLayout;
    }

    @Override
    protected void updateFrequency() {
        final Frequency frequency = readFrequencyFromSettings(getActivity());
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
        mFrequencyDeciHzEdit.setValue(frequency.getDeciHertzComponent());
        updateRanges();
    }

    @Override
    protected Frequency getFrequency() {
        final Integer hz = frequencyComponentStringToInteger(mFrequencyHzEdit.getText().toString());
        final Integer deciHz = frequencyComponentStringToInteger(mFrequencyDeciHzEdit.getText().toString());
        return Frequency.newCtcssFrequency(hz, deciHz);
    }

    @Override
    protected Frequency getDefaultKey() {
        return new Ctcss38().getFrequency(1);
    }

    @Override
    protected String getSettingsKey() {
        return "CtcssFrequency";
    }

    @Override
    protected Frequency createKey(Long value) {
        return Frequency.newFrequency(value);
    }

}
