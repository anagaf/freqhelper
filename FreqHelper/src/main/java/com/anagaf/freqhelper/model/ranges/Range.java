package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.model.keys.Frequency;

public interface Range {

    public static final int INVALID_INDEX = 0;

    Integer getNameStringId();

    int getCount();

    Frequency getFrequency(int index);

    int find(Frequency frequency);

    int findPrev(Frequency frequency);

    int findNext(Frequency frequency);
}
