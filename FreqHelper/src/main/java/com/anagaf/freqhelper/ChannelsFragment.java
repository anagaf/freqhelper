package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Frs;
import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

public class ChannelsFragment extends Fragment {

    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mFrequencyMhzEdit;
    private FrequencyComponentEdit mFrequencyKhzEdit;
    private FrequencyComponentEdit mFrequencyHzEdit;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.channels, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

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
            public void onFrequencyChanged() {
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

    private void addRangeRow(LayoutInflater inflater, RangeView.Listener listener, Range range) {
        View view = inflater.inflate(R.layout.range, null, false);
        RangeView row = (RangeView) view;
        row.setRange(range);
        row.setListener(listener);
        mRangesLayout.addView(row);
    }

    private void loadFrequency() {
        final Frequency frequency = Settings.getFrequency(getActivity());
        mFrequencyMhzEdit.setValue(frequency.getMegahertzComponent());
        mFrequencyKhzEdit.setValue(frequency.getKilohertzComponent());
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
        updateRanges();
    }

    private void saveFrequency() {
        BackStack.getsInstance().push(Settings.getFrequency(getActivity()));
        final Frequency frequency = getFrequency();
        Settings.setFrequency(getActivity(), frequency);
    }

    private void updateRanges() {
        final Frequency frequency = getFrequency();
        for (int i = 0; i < mRangesLayout.getChildCount(); i++) {
            final RangeView row = (RangeView) mRangesLayout.getChildAt(i);
            row.setFrequency(frequency);
        }
    }

    private Frequency getFrequency() {
        final Integer mhz = frequencyComponentStringToInteger(mFrequencyMhzEdit.getText().toString());
        final Integer khz = frequencyComponentStringToInteger(mFrequencyKhzEdit.getText().toString());
        final Integer hz = frequencyComponentStringToInteger(mFrequencyHzEdit.getText().toString());
        return Frequency.newChannelFrequency(mhz, khz, hz);
    }

    private static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }

//    @Override
//    public void onBackPressed() {
//        final Frequency frequency = BackStack.getsInstance().pop();
//        if (frequency == null) {
//            super.onBackPressed();
//        } else {
//            Settings.setFrequency(this, frequency);
//            loadFrequency();
//        }
//    }
}
