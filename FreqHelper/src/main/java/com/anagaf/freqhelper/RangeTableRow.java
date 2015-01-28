package com.anagaf.freqhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.anagaf.freqhelper.model.Range;

public class RangeTableRow extends TableRow {

    private Range mRange;

    public RangeTableRow(Context context) {
        super(context);
    }

    public RangeTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRange(Range range) {
        mRange = range;

        final TextView title = (TextView) findViewById(R.id.title);
        title.setText(range.getNameStringId());
    }

    public void setChannel(int i) {
        final EditText channel = (EditText) findViewById(R.id.channel);
        channel.setText("" + i);
    }
}
