package com.anagaf.freqhelper.model.ranges;

import java.util.List;

public abstract class StaticRange extends AbstractRange {

    protected abstract List<Long> getValues();

    @Override
    public int getCount() {
        return getValues().size();
    }

    @Override
    public long getValue(int index) {
        return getValues().get(index - 1);
    }

}
