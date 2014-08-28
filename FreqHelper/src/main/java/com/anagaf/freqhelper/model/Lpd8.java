package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lpd8 extends AbstractRange {

    static final List<Frequency> sFrequencies;
    static {
        List<Frequency> frequencies = new ArrayList<Frequency>();
        frequencies.add(new Frequency(433, 75));
        frequencies.add(new Frequency(433, 100));
        frequencies.add(new Frequency(433, 200));
        frequencies.add(new Frequency(433, 300));
        frequencies.add(new Frequency(433, 350));
        frequencies.add(new Frequency(433, 475));
        frequencies.add(new Frequency(433, 625));
        frequencies.add(new Frequency(433, 800));
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    @Override
    public Integer getNameStringId() {
        return R.string.lpd_8channels_title;
    }

    @Override
    public int getChannelCount() {
        return sFrequencies.size();
    }

    @Override
    public Frequency getFrequency(int channel) {
        assert (channel > 0 && channel <= getChannelCount());
        return sFrequencies.get(channel - 1);
    }
}
