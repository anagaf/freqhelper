package com.anagaf.freqhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Frequency;
import com.anagaf.freqhelper.model.Frs;
import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;

public class ChannelsPage extends Page {

    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mFrequencyMhzEdit;
    private FrequencyComponentEdit mFrequencyKhzEdit;
    private FrequencyComponentEdit mFrequencyHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.channels, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

        mFrequencyMhzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_mhz_edit);
        mFrequencyMhzEdit.setListener(getFrequencyComponentEditListener());

        mFrequencyKhzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_khz_edit);
        mFrequencyKhzEdit.setListener(getFrequencyComponentEditListener());

        mFrequencyHzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.setListener(getFrequencyComponentEditListener());

        addRangeRow(inflater, new Lpd69());
        addRangeRow(inflater, new Lpd8());
        addRangeRow(inflater, new Pmr());
        addRangeRow(inflater, new Frs());

        updateFrequency();

        return view;
    }

    @Override
    protected TableLayout getRangesLayout() {
        return mRangesLayout;
    }

    @Override
    protected Frequency readFrequencyFromSettings(Context context) {
        return Settings.getChannelFrequency(context);
    }

    @Override
    protected void writeFrequencyToSettings(Context context, Frequency frequency) {
        Settings.setChannelFrequency(context, frequency);
    }

    @Override
    protected void updateFrequency() {
        final Frequency frequency = Settings.getChannelFrequency(getActivity());
        mFrequencyMhzEdit.setValue(frequency.getMegahertzComponent());
        mFrequencyKhzEdit.setValue(frequency.getKilohertzComponent());
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
        updateRanges();
    }

    @Override
    protected Frequency getFrequency() {
        final Integer mhz = frequencyComponentStringToInteger(mFrequencyMhzEdit.getText().toString());
        final Integer khz = frequencyComponentStringToInteger(mFrequencyKhzEdit.getText().toString());
        final Integer hz = frequencyComponentStringToInteger(mFrequencyHzEdit.getText().toString());
        return Frequency.newChannelFrequency(mhz, khz, hz);
    }
}
