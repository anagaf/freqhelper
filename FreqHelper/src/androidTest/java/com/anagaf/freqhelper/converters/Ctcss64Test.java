package com.anagaf.freqhelper.converters;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.model.Ctcss64;
import com.anagaf.freqhelper.model.Range;

public class Ctcss64Test extends BaseCtcssRangeTest {

    private final Range mRange = new Ctcss64();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "ctcss64.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }

    public void testFindPrev() {
        testFindPrev(Frequency.newCtcssFrequency(32, 5), Range.INVALID_INDEX);
        testFindPrev(Frequency.newCtcssFrequency(33, 0), Range.INVALID_INDEX);
        testFindPrev(Frequency.newCtcssFrequency(33, 1), 1);
        testFindPrev(Frequency.newCtcssFrequency(254, 0), 63);
        testFindPrev(Frequency.newCtcssFrequency(254, 1), 63);
        testFindPrev(Frequency.newCtcssFrequency(254, 5), 64);
    }

    public void testFindNext() {
        testFindNext(Frequency.newCtcssFrequency(32, 5), 1);
        testFindNext(Frequency.newCtcssFrequency(33, 0), 2);
        testFindNext(Frequency.newCtcssFrequency(33, 5), 2);
        testFindNext(Frequency.newCtcssFrequency(254, 1), Range.INVALID_INDEX);
        testFindNext(Frequency.newCtcssFrequency(254, 5), Range.INVALID_INDEX);
    }

    private void testFindPrev(Frequency frequency, int expectedIndex) {
        assertEquals(expectedIndex, mRange.findPrev(frequency));
    }

    private void testFindNext(Frequency frequency, int expectedIndex) {
        assertEquals(expectedIndex, mRange.findNext(frequency));
    }


}
