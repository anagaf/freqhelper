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
    private Listener mListener;

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
                moveToPrevChannel();
            }
        });

        final Button nextChannelButton = (Button) findViewById(R.id.next_channel_button);
        nextChannelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNextChannel();
            }
        });
    }

    public void setRange(Range range) {
        mRange = range;

        final TextView title = (TextView) findViewById(R.id.title);
        title.setText(range.getNameStringId());
    }

    public void setFrequency(Frequency frequency) {
        mChannel = mRange.getChannel(frequency);
        final EditText channelEdit = (EditText) findViewById(R.id.channel);
        final String channelString;
        if (mChannel == Range.INVALID_CHANNEL) {
            channelString = "--";
        } else {
            channelString = String.valueOf(mChannel);
        }
        channelEdit.setText(channelString);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    private void moveToPrevChannel() {
        final int prevChannel = mRange.getPrevChannel(mChannel);
        if (prevChannel != Range.INVALID_CHANNEL) {
            moveToChannel(prevChannel);
            mListener.onChannelChanged();
        }
    }

    private void moveToNextChannel() {
        final int nextChannel = mRange.getNextChannel(mChannel);
        if (nextChannel != Range.INVALID_CHANNEL) {
            moveToChannel(nextChannel);
        }
    }

    private void moveToChannel(int channel) {
        final Frequency frequency = mRange.getFrequency(channel);
        Settings.setFrequency(getContext(), frequency);
        mListener.onChannelChanged();
    }

    /**
     * ******* Inner Classes *********
     */

    public interface Listener {
        public void onChannelChanged();
    }
}
