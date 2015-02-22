package com.anagaf.freqhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Ctcss38;
import com.anagaf.freqhelper.model.Ctcss39;
import com.anagaf.freqhelper.model.Ctcss64;

public class CtcssPage extends Page {

    private FrequencyComponentEdit mFrequencyHzEdit;
    private FrequencyDeciHertzComponentEdit mFrequencyDeciHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ctcss, null);

        setRangesLayout((TableLayout) view.findViewById(R.id.ranges_layout));

        final FrequencyComponentEdit.Listener frequencyComponentChangeListener = new FrequencyComponentEdit.Listener() {
            @Override
            public void onValueChanged(int value) {
                saveFrequency();
                updateRanges();
            }
        };

        mFrequencyHzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.setListener(frequencyComponentChangeListener);

        mFrequencyDeciHzEdit = (FrequencyDeciHertzComponentEdit) view.findViewById(R.id.frequency_deci_hz_edit);
        mFrequencyDeciHzEdit.setListener(frequencyComponentChangeListener);

        addRangeRow(inflater, new Ctcss38());
        addRangeRow(inflater, new Ctcss39());
        addRangeRow(inflater, new Ctcss64());

        loadFrequency();

        return view;
    }

    @Override
    protected Frequency readFrequencyFromSettings(Context context) {
        return Settings.getCtcssFrequency(context);
    }

    @Override
    protected void writeFrequencyToSettings(Context context, Frequency frequency) {
        Settings.setCtcssFrequency(context, frequency);
    }

    @Override
    protected void loadFrequency() {
        final Frequency frequency = Settings.getCtcssFrequency(getActivity());
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
}
