package com.anagaf.freqhelper.model.ranges;

public class Lpd69Test extends BaseChannelRangeTest {

    private final Range mRange = new Lpd69();

    @Override
    protected String getExpectedValuesFileName() {
        return "lpd69.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
