package com.anagaf.freqhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

public class MainActivity extends Activity {
    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mFrequencyMhzEdit;
    private FrequencyComponentEdit mFrequencyKhzEdit;
    private FrequencyComponentEdit mFrequencyHzEdit;
    private boolean mStarted;

    private RangeView.Listener mRangeViewListener = new RangeView.Listener() {
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

        final FrequencyComponentEdit.Listener frequencyComponentChangeListener = new FrequencyComponentEdit.Listener() {
            @Override
            public void onChanged() {
                final Frequency frequency = getFrequency();
                Settings.setFrequency(MainActivity.this, frequency);
                onFrequencyChanged();
            }
        };

        mFrequencyMhzEdit = (FrequencyComponentEdit) findViewById(R.id.frequency_mhz_edit);
        mFrequencyMhzEdit.setListener(frequencyComponentChangeListener);

        mFrequencyKhzEdit = (FrequencyComponentEdit) findViewById(R.id.frequency_khz_edit);
        mFrequencyKhzEdit.setListener(frequencyComponentChangeListener);

        mFrequencyHzEdit = (FrequencyComponentEdit) findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.setListener(frequencyComponentChangeListener);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addRangeRow(inflater, new Lpd69());
        addRangeRow(inflater, new Lpd8());
        addRangeRow(inflater, new Pmr());
    }

    private void addRangeRow(LayoutInflater inflater, Range range) {
        View view = inflater.inflate(R.layout.range, null, false);
        RangeView row = (RangeView) view;
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
        mFrequencyMhzEdit.setValue(frequency.getMegahertzComponent());
        mFrequencyKhzEdit.setValue(frequency.getKilohertzComponent());
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
    }

    private void onFrequencyChanged() {
        final Frequency frequency = getFrequency();

        if (mStarted) {
            Settings.setFrequency(this, frequency);
        }

        for (int i = 0; i < mRangesLayout.getChildCount(); i++) {
            final RangeView row = (RangeView) mRangesLayout.getChildAt(i);
            row.setFrequency(frequency);
        }
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

}
