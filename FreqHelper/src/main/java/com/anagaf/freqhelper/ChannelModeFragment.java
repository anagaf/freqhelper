package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.anagaf.freqhelper.converters.ChannelToFrequencyConverter;

public class ChannelModeFragment extends Fragment {

    private final ChannelToFrequencyConverter mConverter;
    private TextView mFreqTextView;

    public ChannelModeFragment(ChannelToFrequencyConverter mConverter) {
        this.mConverter = mConverter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_channel, container, false);
        assert rootView != null;
        Spinner spinner = (Spinner) rootView.findViewById(R.id.channel_spinner);
        spinner.setAdapter(new ChannelSpinnerAdapter());
        spinner.setOnItemSelectedListener(new OnChannelSelectedListener());

        mFreqTextView = (TextView) rootView.findViewById(R.id.freq_textview);

        return rootView;
    }

    /**
     * ******* Inner Classes *********
     */

    private class ChannelSpinnerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mConverter.getChannelCount();
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
            }
            textView.setText(String.valueOf(postion + 1));
            return textView;
        }
    }

    private class OnChannelSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            mFreqTextView.setText(mConverter.getFrequency(position + 1).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // do nothing
        }
    }
}
