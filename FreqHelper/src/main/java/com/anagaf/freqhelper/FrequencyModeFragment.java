package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anagaf.freqhelper.model.Range;
import com.anagaf.freqhelper.model.Ranges;

import java.util.HashMap;
import java.util.Map;

public class FrequencyModeFragment extends Fragment {

    private final Map<Range, View> mRangeCells = new HashMap<Range, View>();

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
        for (Range range : Ranges.availableRanges()) {
            final String rangeName = getActivity().getString(range.getNameStringId());
            View rangeCell = createRangeCell(rangeName, rangesLayout);
            mRangeCells.put(range, rangeCell);
        }

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        Frequency frequency = Settings.getFrequency(getActivity());
        if (frequency.getHertz() == 0) {
            frequency = Ranges.availableRanges().get(0).getFrequency(1);
        }
        mFrequencyView.setFrequency(frequency);
    }

    private View createRangeCell(String name, LinearLayout layout) {
        View cell = View.inflate(getActivity(), R.layout.frequency_range_cell, null);

        TextView titleTextView = (TextView) cell.findViewById(R.id.frequency_range_title_text_view);
        titleTextView.setText(name);

        layout.addView(cell);

        return cell;

//        TextView titleTextView = new TextView(getActivity(), null, R.attr.FrequencyActivityItemTitleStyleAttr);
//        titleTextView.setText(name);
//        layout.addView(titleTextView);
//
//        RangeCell cell = new RangeCell();
//
//        cell.lowerChannelTextView = new TextView(getActivity(), null, R.attr.FrequencyActivityRangeItemChannelStyleAttr);
//        layout.addView(cell.lowerChannelTextView);
//
//        cell.channelTextView = new TextView(getActivity(), null, R.attr.FrequencyActivityRangeItemChannelStyleAttr);
//        layout.addView(cell.channelTextView);
//
//        cell.ceilingChannelTextView = new TextView(getActivity(), null, R.attr.FrequencyActivityRangeItemChannelStyleAttr);
//        layout.addView(cell.ceilingChannelTextView);
//
//        return cell;
    }

    private void onFrequencyChanged(Frequency frequency) {
        Settings.setFrequency(getActivity(), frequency);

        for (Range range : mRangeCells.keySet()) {
            View cell = mRangeCells.get(range);

            final Range.Entry lowerEntry = range.getLowerEntry(frequency);
            TextView lowerChannelTextView = (TextView) cell.findViewById(R.id.frequency_range_lower_channel_text_view);
            if (lowerEntry != null) {
                lowerChannelTextView.setVisibility(View.VISIBLE);
                lowerChannelTextView.setText(getEnclosingChannelString(R.string.lower_channel_format, lowerEntry));
            } else {
                lowerChannelTextView.setVisibility(View.GONE);
            }

            final int channel = range.getChannel(frequency);
            TextView channelTextView = (TextView) cell.findViewById(R.id.frequency_range_channel_text_view);
            if (channel != Range.INVALID_CHANNEL) {
                channelTextView.setVisibility(View.VISIBLE);
                channelTextView.setText(getString(R.string.channel) + " " + channel);
            } else {
                channelTextView.setVisibility(View.GONE);
            }

            final Range.Entry ceilingEntry = range.getCeilingEntry(frequency);
            TextView ceilingChannelTextView = (TextView) cell.findViewById(R.id.frequency_range_ceiling_channel_text_view);
            if (ceilingEntry != null) {
                ceilingChannelTextView.setVisibility(View.VISIBLE);
                ceilingChannelTextView.setText(getEnclosingChannelString(R.string.ceiling_channel_format, ceilingEntry));
            } else {
                ceilingChannelTextView.setVisibility(View.GONE);
            }
        }
    }

    private String getEnclosingChannelString(int formatStringId, Range.Entry rangeEntry) {
        return String.format(getString(formatStringId), rangeEntry.getChannel(), rangeEntry.getFrequency().toString());

    }
}