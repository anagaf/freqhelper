package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.R;

public class Ctcss64 extends AbstractRange {
    @Override
    public Integer getNameStringId() {
        return R.string.ctcss64;
    }

    @Override
    public int getChannelCount() {
        return 0;
    }

    @Override
    public Frequency getFrequency(int channel) {
        return null;
    }
}
