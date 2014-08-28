package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anagaf.freqhelper.model.Range;

public class ChannelModeFragment extends Fragment {

    private final Range mRange;
    private TextView mFreqTextView;

    public ChannelModeFragment(Range range) {
        this.mRange = range;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_channel_to_frequency, container, false);
        assert rootView != null;
        Spinner spinner = (Spinner) rootView.findViewById(R.id.channel_spinner);
        spinner.setAdapter(new NumberSpinnerAdapter(getActivity(), 1, mRange.getChannelCount()));
        spinner.setOnItemSelectedListener(new OnChannelSelectedListener());

        mFreqTextView = (TextView) rootView.findViewById(R.id.freq_textview);

        return rootView;
    }

    private class OnChannelSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            mFreqTextView.setText(mRange.getFrequency(position + 1).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // do nothing
        }
    }
}
