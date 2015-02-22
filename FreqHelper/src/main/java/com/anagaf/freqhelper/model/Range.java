package com.anagaf.freqhelper.model;

public interface Range {

    public static final int INVALID_INDEX = 0;

    Integer getNameStringId();

    int getCount();

    Frequency getFrequency(int index);

    int find(Frequency frequency);

    int findPrev(Frequency frequency);

    int findNext(Frequency frequency);
}
