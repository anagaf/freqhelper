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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mRangesLayout = (TableLayout) findViewById(R.id.ranges_layout);

        final FrequencyComponentEdit.Listener frequencyComponentChangeListener = new FrequencyComponentEdit.Listener() {
            @Override
            public void onValueChanged(int value) {
                saveFrequency();
                updateRanges();
            }
        };

        mFrequencyMhzEdit = (FrequencyComponentEdit) findViewById(R.id.frequency_mhz_edit);
        mFrequencyMhzEdit.setListener(frequencyComponentChangeListener);

        mFrequencyKhzEdit = (FrequencyComponentEdit) findViewById(R.id.frequency_khz_edit);
        mFrequencyKhzEdit.setListener(frequencyComponentChangeListener);

        mFrequencyHzEdit = (FrequencyComponentEdit) findViewById(R.id.frequency_hz_edit);
        mFrequencyHzEdit.setListener(frequencyComponentChangeListener);

        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final RangeView.Listener rangeViewListener = new RangeView.Listener() {
            @Override
            public void onFrequencyChanged() {
                loadFrequency();
            }
        };

        addRangeRow(inflater, rangeViewListener, new Lpd69());
        addRangeRow(inflater, rangeViewListener, new Lpd8());
        addRangeRow(inflater, rangeViewListener, new Pmr());

        loadFrequency();
    }

    private void addRangeRow(LayoutInflater inflater, RangeView.Listener listener, Range range) {
        View view = inflater.inflate(R.layout.range, null, false);
        RangeView row = (RangeView) view;
        row.setRange(range);
        row.setListener(listener);
        mRangesLayout.addView(row);
    }

    private void loadFrequency() {
        final Frequency frequency = Settings.getFrequency(this);
        mFrequencyMhzEdit.setValue(frequency.getMegahertzComponent());
        mFrequencyKhzEdit.setValue(frequency.getKilohertzComponent());
        mFrequencyHzEdit.setValue(frequency.getHertzComponent());
        updateRanges();
    }

    private void saveFrequency() {
        BackStack.getsInstance().push(Settings.getFrequency(this));
        final Frequency frequency = getFrequency();
        Settings.setFrequency(this, frequency);
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
        return new Frequency(mhz, khz, hz);
    }

    private static Integer frequencyComponentStringToInteger(String string) {
        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }

    @Override
    public void onBackPressed() {
        final Frequency frequency = BackStack.getsInstance().pop();
        if (frequency == null) {
            super.onBackPressed();
        } else {
            Settings.setFrequency(this, frequency);
            loadFrequency();
        }
    }
}
