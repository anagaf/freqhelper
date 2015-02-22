package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Frs;
import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;

public class ChannelsPage extends Page {

    private int mIndex;
    private FrequencyComponentEdit mFrequencyMhzEdit;
    private FrequencyComponentEdit mFrequencyKhzEdit;
    private FrequencyComponentEdit mFrequencyHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.channels, null);

        mIndex = getArguments().getInt(PAGE_INDEX_KEY);

        setRangesLayout((TableLayout) view.findViewById(R.id.ranges_layout));

        final FrequencyComponentEdit.Listener frequencyComponentChangeListener = new FrequencyComponentEdit.Listener() {
            @Override
            public void onValueChanged(int value) {
                saveFrequency();
                updateRanges();
            }
        };

        mFrequencyMhzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_mhz_edit);
        mFrequencyMhzEdit.setListener(frequencyComponentChangeListener);

        mFrequencyKhzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_khz_edit);
        mFrequencyKhzEdit.setListener(frequencyComponentChangeListener);

        mFrequencyHzEdit = (FrequencyComponentEdit) view.findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.setListener(frequencyComponentChangeListener);

        final RangeView.Listener rangeViewListener = new RangeView.Listener() {
            @Override
            public void onFrequencyChanged(Frequency frequency) {
                final Frequency currentFrequency = Settings.getChannelFrequency(getActivity());
                BackStack.getsInstance().push(new BackStack.Item(mIndex, currentFrequency));
                Settings.setChannelFrequency(getActivity(), frequency);
                loadFrequency();
            }
        };

        addRangeRow(inflater, rangeViewListener, new Lpd69());
        addRangeRow(inflater, rangeViewListener, new Lpd8());
        addRangeRow(inflater, rangeViewListener, new Pmr());
        addRangeRow(inflater, rangeViewListener, new Frs());

        loadFrequency();

        return view;
    }

    private void loadFrequency() {
        final Frequency frequency = Settings.getChannelFrequency(getActivity());
        mFrequencyMhzEdit.setValue(frequency.getMegahertzComponent());
        mFrequencyKhzEdit.setValue(frequency.getKilohertzComponent());
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
        updateRanges();
    }

    private void saveFrequency() {
        final Frequency currentFrequency = Settings.getChannelFrequency(getActivity());
        BackStack.getsInstance().push(new BackStack.Item(mIndex, currentFrequency));
        final Frequency newFrequency = getFrequency();
        Settings.setChannelFrequency(getActivity(), newFrequency);
    }

    @Override
    protected Frequency getFrequency() {
        final Integer mhz = frequencyComponentStringToInteger(mFrequencyMhzEdit.getText().toString());
        final Integer khz = frequencyComponentStringToInteger(mFrequencyKhzEdit.getText().toString());
        final Integer hz = frequencyComponentStringToInteger(mFrequencyHzEdit.getText().toString());
        return Frequency.newChannelFrequency(mhz, khz, hz);
    }

    @Override
    public void restoreFrequency(Frequency frequency) {
        Settings.setChannelFrequency(getActivity(), frequency);
        loadFrequency();
    }
}
