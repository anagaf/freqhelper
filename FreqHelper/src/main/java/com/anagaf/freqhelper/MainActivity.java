package com.anagaf.freqhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

public class MainActivity extends Activity {
    private EditText mFrequencyMhzEdit;
    private EditText mFrequencyKhzEdit;
    private EditText mFrequencyHzEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mFrequencyMhzEdit = (EditText) findViewById(R.id.frequency_mhz_edit);
        mFrequencyKhzEdit = (EditText) findViewById(R.id.frequency_khz_edit);
        mFrequencyHzEdit = (EditText) findViewById(R.id.frequency_hz_edit);

        LayoutInflater inflater = (LayoutInflater)getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        addRangeRow(inflater, new Lpd69());
        addRangeRow(inflater, new Lpd8());
        addRangeRow(inflater, new Pmr());
    }

    private void addRangeRow(LayoutInflater inflater, Range range) {
        TableLayout layout = (TableLayout) findViewById(R.id.ranges_layout);
        TableRow row = (TableRow) inflater.inflate(R.layout.channel_row, null, false);

        TextView title = (TextView) row.findViewById(R.id.title);
        title.setText(range.getNameStringId());

        layout.addView(row);
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshFrequency(Settings.getFrequency(this));
    }

    private void refreshFrequency(Frequency frequency) {
        refreshFrequencyComponent(mFrequencyMhzEdit, frequency.getMegahertzComponent());
        refreshFrequencyComponent(mFrequencyKhzEdit, frequency.getKilohertzComponent());
        refreshFrequencyComponent(mFrequencyHzEdit, frequency.getHertzComponent());
    }

    private void refreshFrequencyComponent(EditText editText, int frequencyComponent) {
        editText.setText(String.format("%03d", frequencyComponent));
    }
}
