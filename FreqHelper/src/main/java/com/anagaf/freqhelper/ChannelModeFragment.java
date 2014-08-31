package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
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
    private ClickableSpan mFrequencyClickableSpan;

    public ChannelModeFragment(Range range) {
        this.mRange = range;
        mFrequencyClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Log.d("FreqHelper", "Clicked");
            }
        };
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
        mFreqTextView.setMovementMethod(LinkMovementMethod.getInstance());

        return rootView;
    }

    private class OnChannelSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            SpannableString text = new SpannableString(mRange.getFrequency(position + 1).toString());
            text.setSpan(mFrequencyClickableSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mFreqTextView.setText(text);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // do nothing
        }
    }
}
