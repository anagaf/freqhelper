package com.anagaf.freqhelper;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;

import java.util.ArrayList;
import java.util.List;

public enum Mode {

    Lpd69(R.string.lpd_69_channels_title, new ChannelModeFragment(new Lpd69())),
    Lpd8(R.string.lpd_8channels_title, new ChannelModeFragment(new Lpd8())),
    Pmr(R.string.pmr_title, new ChannelModeFragment(new Pmr())),
    Frequency(R.string.frequency, new FrequencyModeFragment());

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
