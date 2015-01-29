package com.anagaf.freqhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

public class MainActivity extends Activity {
    private TableLayout mRangesLayout;
    private EditText mFrequencyMhzEdit;
    private EditText mFrequencyKhzEdit;
    private EditText mFrequencyHzEdit;
    private boolean mStarted;

    private TextWatcher mFrequencyChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            onFrequencyChanged();
        }
    };

    private RangeTableRow.Listener mRangeViewListener = new RangeTableRow.Listener() {
        @Override
        public void onChannelChanged() {
            refreshFrequency(Settings.getFrequency(MainActivity.this));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mStarted = false;

        mRangesLayout = (TableLayout) findViewById(R.id.ranges_layout);

        mFrequencyMhzEdit = (EditText) findViewById(R.id.frequency_mhz_edit);
        mFrequencyMhzEdit.addTextChangedListener(mFrequencyChangeListener);
        mFrequencyKhzEdit = (EditText) findViewById(R.id.frequency_khz_edit);
        mFrequencyKhzEdit.addTextChangedListener(mFrequencyChangeListener);
        mFrequencyHzEdit = (EditText) findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.addTextChangedListener(mFrequencyChangeListener);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addRangeRow(inflater, new Lpd69());
        addRangeRow(inflater, new Lpd8());
        addRangeRow(inflater, new Pmr());
    }

    private void addRangeRow(LayoutInflater inflater, Range range) {
        View view = inflater.inflate(R.layout.channel_row, null, false);
        RangeTableRow row = (RangeTableRow) view;
        row.setRange(range);
        row.setListener(mRangeViewListener);
        mRangesLayout.addView(row);
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshFrequency(Settings.getFrequency(this));
        mStarted = true;
    }

    private void refreshFrequency(Frequency frequency) {
        mFrequencyMhzEdit.setText(frequencyComponentIntegerToString(frequency.getMegahertzComponent()));
        mFrequencyKhzEdit.setText(frequencyComponentIntegerToString(frequency.getKilohertzComponent()));
        mFrequencyHzEdit.setText(frequencyComponentIntegerToString(frequency.getHertzComponent()));
    }

    private void onFrequencyChanged() {
        final Frequency frequency = getFrequency();

        if (mStarted) {
            Settings.setFrequency(this, frequency);
        }

        for (int i = 0; i < mRangesLayout.getChildCount(); i++) {
            final RangeTableRow row = (RangeTableRow) mRangesLayout.getChildAt(i);
            row.setFrequency(frequency);
        }

//
//        for (Range range : mRangeCells.keySet()) {
//            View cell = mRangeCells.get(range);
//
//            final Range.Entry lowerEntry = range.getLowerEntry(frequency);
//            TextView lowerChannelTextView = (TextView) cell.findViewById(R.id.frequency_range_lower_channel_text_view);
//            if (lowerEntry != null) {
//                lowerChannelTextView.setVisibility(View.VISIBLE);
//                lowerChannelTextView.setText(getEnclosingChannelString(R.string.lower_channel_format, lowerEntry));
//            } else {
//                lowerChannelTextView.setVisibility(View.GONE);
//            }
//
//            final int channel = range.getChannel(frequency);
//            TextView channelTextView = (TextView) cell.findViewById(R.id.frequency_range_channel_text_view);
//            if (channel != Range.INVALID_CHANNEL) {
//                channelTextView.setVisibility(View.VISIBLE);
//                channelTextView.setText(getString(R.string.channel) + " " + channel);
//            } else {
//                channelTextView.setVisibility(View.GONE);
//            }
//
//            final Range.Entry ceilingEntry = range.getHigherEntry(frequency);
//            TextView ceilingChannelTextView = (TextView) cell.findViewById(R.id.frequency_range_ceiling_channel_text_view);
//            if (ceilingEntry != null) {
//                ceilingChannelTextView.setVisibility(View.VISIBLE);
//                ceilingChannelTextView.setText(getEnclosingChannelString(R.string.higher_channel_format, ceilingEntry));
//            } else {
//                ceilingChannelTextView.setVisibility(View.GONE);
//            }
//        }
    }

    private Frequency getFrequency() {
        final Integer mhz = frequencyComponentStringToInteger(mFrequencyMhzEdit.getText().toString());
        final Integer khz = frequencyComponentStringToInteger(mFrequencyKhzEdit.getText().toString());
        final Integer hz = frequencyComponentStringToInteger(mFrequencyHzEdit.getText().toString());
        return new Frequency(mhz, khz, hz);
    }

    private static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.valueOf(string);
    }

    private static String frequencyComponentIntegerToString(Integer value) {
        return String.format("%03d", value);
    }

}
