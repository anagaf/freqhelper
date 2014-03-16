package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.anagaf.freqhelper.ranges.Lpd69;
import com.anagaf.freqhelper.ranges.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrequencyModeFragment extends Fragment {

    private final List<Range> mRanges;

    public FrequencyModeFragment() {
        List<Range> ranges = new ArrayList<Range>();
        ranges.add(new Lpd69());
        mRanges = Collections.unmodifiableList(ranges);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frequency_to_channel, container, false);
        assert rootView != null;

        return rootView;
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