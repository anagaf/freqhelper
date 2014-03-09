package com.anagaf.freqhelper;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.anagaf.freqhelper.converters.Lpd69ChannelToFrequencyConverter;
import java.util.ArrayList;
import java.util.List;

public enum Mode {

    Lpd69channels(R.string.lpd_69_channels_title, new ChannelModeFragment(new Lpd69ChannelToFrequencyConverter())),
//    Lpd8channels(R.string.lpd_8channels_title, new ChannelModeFragment(new Lpd8ChannelToFrequencyConverter())),
//    Pmr(R.string.pmr_title, new ChannelModeFragment(new PmrToFrequenctConverter())),
    Frequency(R.string.frequency_title, new FrequencyModeFragment());

    private final int mTitleStringId;
    private final Fragment mFragment;

    private Mode(int titleStringId, Fragment fragment) {
        mTitleStringId = titleStringId;
        mFragment = fragment;
    }

    public static List<String> getTitles(Context context) {
        List<String> titles = new ArrayList<String>(values().length);
        for (Mode mode : values()) {
            titles.add(context.getString(mode.mTitleStringId));
        }
        return titles;
    }

    public Fragment getFragment() {
        return mFragment;
    }
}
