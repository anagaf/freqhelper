package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.ranges.Range;

public class RangeView extends TableRow {
    private RangeChannelEdit mChannelEdit;
    private Button mPrevChannelButton;
    private Button mNextChannelButton;

    private int mPrevChannel;
    private int mNextChannel;

    private Range mRange;
    private Listener mListener;

    public RangeView(Context context) {
        super(context);
    }

    public RangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mPrevChannelButton = (Button) findViewById(R.id.prev_channel_button);
        mPrevChannelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToChannel(mPrevChannel);
            }
        });

        mNextChannelButton = (Button) findViewById(R.id.next_channel_button);
        mNextChannelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToChannel(mNextChannel);
            }
        });

        mChannelEdit = (RangeChannelEdit) findViewById(R.id.channel);
        mChannelEdit.setListener(new BaseEdit.Listener() {
            @Override
            public void onValueChanged(int value) {
                if (value != Range.INVALID_INDEX) {
                    moveToChannel(value);
                }
            }
        });
    }

    public void setRange(Range range) {
        mRange = range;

        final TextView title = (TextView) findViewById(R.id.title);
        title.setText(range.getNameStringId());

        mChannelEdit.setMaxChannel(mRange.getCount());
    }

    public void setValue(long value) {
        mChannelEdit.setValue(mRange.find(value));

        mPrevChannel = mRange.findPrev(value);
        mPrevChannelButton.setEnabled(mPrevChannel != Range.INVALID_INDEX);
        mNextChannel = mRange.findNext(value);
        mNextChannelButton.setEnabled(mNextChannel != Range.INVALID_INDEX);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    private void moveToChannel(int channel) {
        if (channel >= 1 && channel <= mRange.getCount()) {
            mListener.onKeyChanged(mRange.getValue(channel));
        }
    }

    /**
     * ******* Inner Classes *********
     */

    public interface Listener {
        public void onKeyChanged(long value);
    }
}
