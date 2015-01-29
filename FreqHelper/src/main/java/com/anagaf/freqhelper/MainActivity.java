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

        mFrequencyMhzEdit = (EditText) findViewById(R.id.frequency_mhz_edit);
        mFrequencyMhzEdit.addTextChangedListener(mFrequencyChangeListener);
        mFrequencyMhzEdit.setOnEditorActionListener(new KeyboardHider(this, mFrequencyMhzEdit));

        mFrequencyKhzEdit = (EditText) findViewById(R.id.frequency_khz_edit);
        mFrequencyKhzEdit.addTextChangedListener(mFrequencyChangeListener);
        mFrequencyKhzEdit.setOnEditorActionListener(new KeyboardHider(this, mFrequencyKhzEdit));

        mFrequencyHzEdit = (EditText) findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.addTextChangedListener(mFrequencyChangeListener);
        mFrequencyHzEdit.setOnEditorActionListener(new KeyboardHider(this, mFrequencyHzEdit));

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

    private static String frequencyComponentIntegerToString(Integer value) {
        return String.format("%03d", value);
    }

}
