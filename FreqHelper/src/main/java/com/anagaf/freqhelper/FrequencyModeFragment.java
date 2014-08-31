package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anagaf.freqhelper.model.Range;
import com.anagaf.freqhelper.model.Ranges;

import java.util.HashMap;
import java.util.Map;

public class FrequencyModeFragment extends Fragment {

    private final Map<Range, View> mRangeCells = new HashMap<Range, View>();

    private EditText mFrequencyMhzEdit;
    private EditText mFrequencyKhzEdit;
    private EditText mFrequencyHzEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frequency_to_channel, container, false);
        assert rootView != null;

        mFrequencyMhzEdit = createFrequencyComponentEdit(rootView, R.id.frequency_mhz_edit);
        mFrequencyKhzEdit = createFrequencyComponentEdit(rootView, R.id.frequency_khz_edit);
        mFrequencyHzEdit = createFrequencyComponentEdit(rootView, R.id.frequency_hz_edit);

        LinearLayout rangesLayout = (LinearLayout) rootView.findViewById(R.id.ranges_layout);
        for (Range range : Ranges.availableRanges()) {
            final String rangeName = getActivity().getString(range.getNameStringId());
            View rangeCell = createRangeCell(rangeName, rangesLayout);
            mRangeCells.put(range, rangeCell);
        }

        return rootView;
    }

    private EditText createFrequencyComponentEdit(View rootView, int viewId) {
        EditText edit = (EditText) rootView.findViewById(viewId);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                onFrequencyChanged(getFrequency());
            }
        });
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    final Integer value = frequencyComponentStringToInteger(textView.getText().toString());
                    textView.setText(String.format("%03d", value));
                }
                return false;
            }
        });
        return edit;
    }

    @Override
    public void onStart() {
        super.onStart();
        Frequency frequency = Settings.getFrequency(getActivity());
        if (frequency.getHertz() == 0) {
            frequency = Ranges.availableRanges().get(0).getFrequency(1);
        }
        setFrequency(frequency);
    }

    private View createRangeCell(String name, LinearLayout layout) {
        View cell = View.inflate(getActivity(), R.layout.frequency_range_cell, null);

        TextView titleTextView = (TextView) cell.findViewById(R.id.frequency_range_title_text_view);
        titleTextView.setText(name);

        layout.addView(cell);

        return cell;
    }

    private void onFrequencyChanged(Frequency frequency) {
        Settings.setFrequency(getActivity(), frequency);

        for (Range range : mRangeCells.keySet()) {
            View cell = mRangeCells.get(range);

            final Range.Entry lowerEntry = range.getLowerEntry(frequency);
            TextView lowerChannelTextView = (TextView) cell.findViewById(R.id.frequency_range_lower_channel_text_view);
            if (lowerEntry != null) {
                lowerChannelTextView.setVisibility(View.VISIBLE);
                lowerChannelTextView.setText(getEnclosingChannelString(R.string.lower_channel_format, lowerEntry));
            } else {
                lowerChannelTextView.setVisibility(View.GONE);
            }

            final int channel = range.getChannel(frequency);
            TextView channelTextView = (TextView) cell.findViewById(R.id.frequency_range_channel_text_view);
            if (channel != Range.INVALID_CHANNEL) {
                channelTextView.setVisibility(View.VISIBLE);
                channelTextView.setText(getString(R.string.channel) + " " + channel);
            } else {
                channelTextView.setVisibility(View.GONE);
            }

            final Range.Entry ceilingEntry = range.getHigherEntry(frequency);
            TextView ceilingChannelTextView = (TextView) cell.findViewById(R.id.frequency_range_ceiling_channel_text_view);
            if (ceilingEntry != null) {
                ceilingChannelTextView.setVisibility(View.VISIBLE);
                ceilingChannelTextView.setText(getEnclosingChannelString(R.string.higher_channel_format, ceilingEntry));
            } else {
                ceilingChannelTextView.setVisibility(View.GONE);
            }
        }
    }

    private String getEnclosingChannelString(int formatStringId, Range.Entry rangeEntry) {
        return String.format(getString(formatStringId), rangeEntry.getChannel(), rangeEntry.getFrequency().toString());
    }

    private void setFrequency(Frequency frequency) {
        mFrequencyMhzEdit.setText(frequencyComponentIntegerToString(frequency.getMegahertzComponent()));
        mFrequencyKhzEdit.setText(frequencyComponentIntegerToString(frequency.getKilohertzComponent()));
        mFrequencyHzEdit.setText(frequencyComponentIntegerToString(frequency.getHertzComponent()));
    }

    private Frequency getFrequency() {
        final Integer mhz = frequencyComponentStringToInteger(mFrequencyMhzEdit.getText().toString());
        final Integer khz = frequencyComponentStringToInteger(mFrequencyKhzEdit.getText().toString());
        final Integer hz = frequencyComponentStringToInteger(mFrequencyHzEdit.getText().toString());
        return new Frequency(mhz, khz, hz);
    }

    private Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.valueOf(string);
    }

    private String frequencyComponentIntegerToString(Integer value) {
        return String.format("%03d", value);
    }
}