package com.anagaf.freqhelper;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.anagaf.freqhelper.ranges.Lpd69;

import java.util.ArrayList;
import java.util.List;

public enum Mode {

    ChannelToFrequencyLpd69(R.string.lpd_69_channels_title, new ChannelModeFragment(new Lpd69())),
    FrequencyToChannelLpd69(R.string.frequency, new FrequencyModeFragment());

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
