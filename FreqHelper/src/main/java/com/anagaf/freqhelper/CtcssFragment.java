package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Ctcss38;
import com.anagaf.freqhelper.model.Ctcss39;
import com.anagaf.freqhelper.model.Ctcss64;

public class CtcssFragment extends BaseMainActivityFragment {

    private int mIndex;
    private FrequencyComponentEdit mFrequencyHzEdit;
    private FrequencyDeciHertzComponentEdit mFrequencyDeciHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ctcss, null);

        mIndex = getArguments().getInt(PAGE_INDEX_KEY);

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

        final RangeView.Listener rangeViewListener = new RangeView.Listener() {
            @Override
            public void onFrequencyChanged(Frequency frequency) {
                final Frequency currentFrequency = Settings.getCtcssFrequency(getActivity());
                BackStack.getsInstance().push(new BackStack.Item(mIndex, currentFrequency));
                Settings.setCtcssFrequency(getActivity(), frequency);
                loadFrequency();
            }
        };

        addRangeRow(inflater, rangeViewListener, new Ctcss38());
        addRangeRow(inflater, rangeViewListener, new Ctcss39());
        addRangeRow(inflater, rangeViewListener, new Ctcss64());

        loadFrequency();

        return view;
    }

    private void loadFrequency() {
        final Frequency frequency = Settings.getCtcssFrequency(getActivity());
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
        mFrequencyDeciHzEdit.setValue(frequency.getDeciHertzComponent());
        updateRanges();
    }

    private void saveFrequency() {
        final Frequency currentFrequency = Settings.getCtcssFrequency(getActivity());
        BackStack.getsInstance().push(new BackStack.Item(mIndex, currentFrequency));
        final Frequency newFrequency = getFrequency();
        Settings.setCtcssFrequency(getActivity(), newFrequency);
    }

    @Override
    protected Frequency getFrequency() {
        final Integer hz = frequencyComponentStringToInteger(mFrequencyHzEdit.getText().toString());
        final Integer deciHz = frequencyComponentStringToInteger(mFrequencyDeciHzEdit.getText().toString());
        return Frequency.newCtcssFrequency(hz, deciHz);
    }

    @Override
    public void restoreFrequency(Frequency frequency) {
        Settings.setCtcssFrequency(getActivity(), frequency);
        loadFrequency();
    }
}
