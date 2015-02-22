package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.model.keys.Key;

public interface Range {

    public static final int INVALID_INDEX = 0;

    Integer getNameStringId();

    int getCount();

    Key getKey(int index);

    int find(Key key);

    int findPrev(Key key);

    int findNext(Key key);
}
