package com.anagaf.freqhelper.model.ranges;

public class Ctcss39Test extends BaseCtcssRangeTest {

    private final Range mRange = new Ctcss39();

    @Override
    protected String getExpectedValuesFileName() {
        return "ctcss39.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
