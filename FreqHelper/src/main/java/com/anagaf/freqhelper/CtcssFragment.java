package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Ctcss64;

public class CtcssFragment extends BaseMainActivityFragment {

    private FrequencyComponentEdit mFrequencyHzEdit;
    private FrequencyDeciHertzComponentEdit mFrequencyHzFractionEdit;

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

        mFrequencyHzFractionEdit = (FrequencyDeciHertzComponentEdit) view.findViewById(R.id.frequency_deci_hz_edit);
        mFrequencyHzFractionEdit.setListener(frequencyComponentChangeListener);

        final RangeView.Listener rangeViewListener = new RangeView.Listener() {
            @Override
            public void onFrequencyChanged(Frequency frequency) {
                Settings.setCtcssFrequency(getActivity(), frequency);
                loadFrequency();
            }
        };

        addRangeRow(inflater, rangeViewListener, new Ctcss64());

        loadFrequency();

        return view;
    }

    private void loadFrequency() {
        final Frequency frequency = Settings.getCtcssFrequency(getActivity());
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
        mFrequencyHzFractionEdit.setValue(frequency.getHertzFractionComponent());
        updateRanges();
    }

    private void saveFrequency() {
        //BackStack.getsInstance().push(Settings.getChannelFrequency(getActivity()));
        final Frequency frequency = getFrequency();
        Settings.setCtcssFrequency(getActivity(), frequency);
    }

    @Override
    protected Frequency getFrequency() {
        final Integer hz = frequencyComponentStringToInteger(mFrequencyHzEdit.getText().toString());
        final Integer hzFraction = frequencyComponentStringToInteger(mFrequencyHzFractionEdit.getText().toString());
        return Frequency.newCtcssFrequency(hz, hzFraction);
    }
}
