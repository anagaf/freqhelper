package com.anagaf.freqhelper.model.ranges;

public interface Range {

    public static final int INVALID_INDEX = 0;
    public static final long INVALID_VALUE = 0L;

    Integer getNameStringId();

    int getCount();

    long getValue(int index);

    int find(long value);

    int findPrev(long value);

    int findNext(long value);
}
