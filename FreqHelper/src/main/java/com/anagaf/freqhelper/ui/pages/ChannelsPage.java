package com.anagaf.freqhelper.ui.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.Frequency;
import com.anagaf.freqhelper.model.ranges.Frs;
import com.anagaf.freqhelper.model.ranges.Lpd69;
import com.anagaf.freqhelper.model.ranges.Lpd8;
import com.anagaf.freqhelper.model.ranges.Pmr;
import com.anagaf.freqhelper.ui.views.ValueComponentEdit;

public class ChannelsPage extends AbstractFrequencyPage {
    public static final String KEY = "Channels";

    private TableLayout mRangesLayout;
    private ValueComponentEdit mFrequencyMhzEdit;
    private ValueComponentEdit mFrequencyKhzEdit;
    private ValueComponentEdit mFrequencyHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.channels, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.channels_ranges_layout);

        mFrequencyMhzEdit = (ValueComponentEdit) view.findViewById(R.id.frequency_mhz_edit);
        mFrequencyMhzEdit.setListener(getValueComponentEditListener());

        mFrequencyKhzEdit = (ValueComponentEdit) view.findViewById(R.id.frequency_khz_edit);
        mFrequencyKhzEdit.setListener(getValueComponentEditListener());

        mFrequencyHzEdit = (ValueComponentEdit) view.findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.setListener(getValueComponentEditListener());

        addRangeRow(inflater, new Lpd69());
        addRangeRow(inflater, new Lpd8());
        addRangeRow(inflater, new Pmr());
        addRangeRow(inflater, new Frs());

        updateValue();

        return view;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    protected TableLayout getRangesLayout() {
        return mRangesLayout;
    }

    @Override
    protected void updateValue() {
        final long value = readValueFromSettings(getActivity());
        final Frequency frequency = Frequency.newFrequency(value);
        mFrequencyMhzEdit.setValueComponent(frequency.getMegahertzComponent());
        mFrequencyKhzEdit.setValueComponent(frequency.getKilohertzComponent());
        mFrequencyHzEdit.setValueComponent(frequency.getHertzComponent());
        updateRanges();
    }

    @Override
    protected long getValue() {
        final Integer mhz = mFrequencyMhzEdit.getValueComponent();
        final Integer khz = mFrequencyKhzEdit.getValueComponent();
        final Integer hz = mFrequencyHzEdit.getValueComponent();
        return Frequency.getChannelFrequencyDecihertz(mhz, khz, hz);
    }

    @Override
    protected long getDefaultValue() {
        return new Lpd69().getValue(1);
    }

    @Override
    protected String getSettingsKey() {
        return "ChannelFrequency";
    }

}
