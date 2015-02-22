package com.anagaf.freqhelper.model;

import java.util.NavigableMap;
import java.util.TreeMap;

public abstract class AbstractRange implements Range {

    private final NavigableMap<Key, Integer> mItems;

    AbstractRange() {
        mItems = new TreeMap<>();
        for (int i = 1; i <= getCount(); i++) {
            mItems.put(getKey(i), i);
        }
    }

    @Override
    public int find(Key key) {
        if (mItems.containsKey(key)) {
            return mItems.get(key);
        } else {
            return INVALID_INDEX;
        }
    }

    @Override
    public int findPrev(Key key) {
        final TreeMap.Entry<Key, Integer> entry = mItems.lowerEntry(key);
        return (entry != null ? entry.getValue() : INVALID_INDEX);
    }

    @Override
    public int findNext(Key key) {
        final TreeMap.Entry<Key, Integer> entry = mItems.higherEntry(key);
        return (entry != null ? entry.getValue() : INVALID_INDEX);
    }

}
