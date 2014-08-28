package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Range;
import com.anagaf.freqhelper.model.Ranges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyModeFragment extends Fragment {

    private final Map<Range, TextView> mRangeViews = new HashMap<Range, TextView>();

    private FrequencyView mFrequencyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frequency_to_channel, container, false);
        assert rootView != null;

        mFrequencyView = (FrequencyView) rootView.findViewById(R.id.frequency_view);
        mFrequencyView.setOnFrequencyChangedListener(new FrequencyView.OnFrequencyChangedListener() {
            @Override
            public void onFrequencyChanged(Frequency frequency) {
                FrequencyModeFragment.this.onFrequencyChanged(frequency);
            }
        });


        LinearLayout rangesLayout = (LinearLayout) rootView.findViewById(R.id.ranges_layout);
        for(Range range : Ranges.availableRanges()) {
            TextView titleTextView = new TextView(getActivity(), null, R.attr.ItemTitleStyleAttr);
            titleTextView.setText(getActivity().getString(range.getNameStringId()));
            rangesLayout.addView(titleTextView);

            TextView valueTextView = new TextView(getActivity(), null, R.attr.ItemTitleStyleAttr);
            rangesLayout.addView(valueTextView);

            mRangeViews.put(range, valueTextView);
        }

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        final Frequency frequency = Settings.getFrequency(getActivity());
        mFrequencyView.setFrequency(frequency);
    }

    private void onFrequencyChanged(Frequency frequency) {
        Settings.setFrequency(getActivity(), frequency);

        for (Range range : mRangeViews.keySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            final int channel = range.getChannel(frequency);
            if(channel == Range.INVALID_CHANNEL) {
                final Range.Entry lowerEntry = range.getLowerEntry(frequency);
                if (lowerEntry != null) {
                    stringBuilder.append("Lower channel ");
                    stringBuilder.append(lowerEntry.getChannel());
                    stringBuilder.append("(" + lowerEntry.getFrequency() + ")");
                }
                final Range.Entry ceilingEntry = range.getCeilingEntry(frequency);
                if (ceilingEntry != null) {
                    stringBuilder.append(stringBuilder.length() > 0 ? ", ceiling channel " : "Ceiling channel ");
                    stringBuilder.append(ceilingEntry.getChannel());
                    stringBuilder.append("(" + ceilingEntry.getFrequency() + ")");
                }
            } else {
                stringBuilder.append("Channel " + channel);
            }
            mRangeViews.get(range).setText(stringBuilder.toString());
        }
    }

    /**
     * ******* Inner Classes *********
     */

    private class FrequencyMhzSpinnerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int postion, View convertView, ViewGroup viewGroup) {
            TextView textView = (TextView) convertView;
            if (textView == null) {
                textView = new TextView(getActivity());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_text_size));
            }
            //textView.setText(" " + String.valueOf(mRange.getBaseFrequencyMhz()) + " ");
            return textView;
        }
    }
}