package com.anagaf.freqhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.anagaf.freqhelper.model.Range;

public class RangeTableRow extends TableRow {

    private int mChannel;
    private Range mRange;

    public RangeTableRow(Context context) {
        super(context);
    }

    public RangeTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final Button prevChannelButton = (Button) findViewById(R.id.prev_channel_button);
        prevChannelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPrevChannel();
            }
        });

        final Button nextChannelButton = (Button) findViewById(R.id.next_channel_button);
        nextChannelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNextChannel();
            }
        });
    }

    private void switchToPrevChannel() {
        final int prevChannel = mRange.getPrevChannel(mChannel);
        if (prevChannel != Range.INVALID_CHANNEL) {
            setChannel(prevChannel);
        }
    }

    private void switchToNextChannel() {
        final int nextChannel = mRange.getNextChannel(mChannel);
        if (nextChannel != Range.INVALID_CHANNEL) {
            setChannel(nextChannel);
        }
    }

    public void setRange(Range range) {
        mRange = range;

        final TextView title = (TextView) findViewById(R.id.title);
        title.setText(range.getNameStringId());
    }

    public void setChannel(int channel) {
        mChannel = channel;

        final EditText channelEdit = (EditText) findViewById(R.id.channel);
        final String channelString;
        if (channel == Range.INVALID_CHANNEL) {
            channelString = "--";
        } else {
            channelString = String.valueOf(channel);
        }
        channelEdit.setText(channelString);
    }

    public Range getRange() {
        return mRange;
    }
}
