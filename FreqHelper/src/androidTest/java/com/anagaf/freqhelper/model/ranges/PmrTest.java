package com.anagaf.freqhelper.model.ranges;

public class PmrTest extends BaseChannelRangeTest {

    private final Range mRange = new Pmr();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "pmr.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
