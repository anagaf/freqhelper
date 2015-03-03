package com.anagaf.freqhelper.model.ranges;

public class Ctcss64Test extends BaseCtcssRangeTest {

    private final Range mRange = new Ctcss64();

    @Override
    protected String getExpectedValuesFileName() {
        return "ctcss64.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
