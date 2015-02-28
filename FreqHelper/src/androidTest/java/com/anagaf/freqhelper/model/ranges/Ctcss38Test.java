package com.anagaf.freqhelper.model.ranges;

public class Ctcss38Test extends BaseCtcssRangeTest {

    private Range mRange = new Ctcss38();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "ctcss38.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
