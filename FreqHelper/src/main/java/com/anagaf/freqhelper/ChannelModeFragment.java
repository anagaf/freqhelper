package com.anagaf.freqhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
    private Spinner mSpinner;
    private ClickableSpan mFrequencyClickableSpan;
    private String mModeSettingsKey;

    public ChannelModeFragment(Range range, String modeSettingsKey) {
        mRange = range;
        mFrequencyClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                switchToFrequencyMode();
            }
        };
        mModeSettingsKey = modeSettingsKey;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_channel_to_frequency, container, false);
        assert rootView != null;
        mSpinner = (Spinner) rootView.findViewById(R.id.channel_spinner);
        mSpinner.setAdapter(new NumberSpinnerAdapter(getActivity(), 1, mRange.getChannelCount()));
        mSpinner.setOnItemSelectedListener(new OnChannelSelectedListener());

        mFreqTextView = (TextView) rootView.findViewById(R.id.freq_textview);
        mFreqTextView.setMovementMethod(LinkMovementMethod.getInstance());

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        final int channel = Settings.getModeChannel(getActivity(), mModeSettingsKey);
        if (channel != Range.INVALID_CHANNEL) {
            setChannel(channel);
        }
    }

    private void switchToFrequencyMode() {
        Settings.setFrequency(getActivity(), getCurrentFrequency());
        Intent intent = new Intent(MainActivity.SWITCH_MODE_ACTION);
        intent.putExtra(MainActivity.MODE_INDEX, Mode.Frequency.ordinal());
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    private Frequency getCurrentFrequency() {
        return mRange.getFrequency(mSpinner.getSelectedItemPosition() + 1);
    }

    private void setChannel(int channel) {
        SpannableString text = new SpannableString(mRange.getFrequency(channel + 1).toString());
        text.setSpan(mFrequencyClickableSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mFreqTextView.setText(text);
    }

    /********** Inner Classes **********/

    private class OnChannelSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            setChannel(position);
            Settings.setModeChannel(getActivity(), mModeSettingsKey, position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // do nothing
        }
    }
}
