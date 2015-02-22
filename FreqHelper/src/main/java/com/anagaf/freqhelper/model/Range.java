package com.anagaf.freqhelper.model;

public interface Range {

    public static final int INVALID_INDEX = 0;

    Integer getNameStringId();

    int getCount();

    Key getKey(int index);

    int find(Key key);

    int findPrev(Key key);

    int findNext(Key key);
}
