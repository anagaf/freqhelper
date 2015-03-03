package com.anagaf.freqhelper.model.ranges;

public class FrsTest extends BaseChannelRangeTest {

    private final Range mRange = new Frs();

    @Override
    protected String getExpectedValuesFileName() {
        return "frs.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
