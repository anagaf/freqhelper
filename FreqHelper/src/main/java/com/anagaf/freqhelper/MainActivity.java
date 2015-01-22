package com.anagaf.freqhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

import java.util.zip.Inflater;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
}
