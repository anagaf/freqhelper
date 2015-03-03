package com.anagaf.freqhelper.model.ranges;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFrequencyRangeTest extends BaseRangeTest {
    private final List<Long> mExpectedFrequencies = new ArrayList<>();

    protected abstract Range getRange();

    protected abstract long parseExpectedFrequency(String[] components);

    protected List<Long> getExpectedFrequencies() {
        return mExpectedFrequencies;
    }

    @Override
    protected void parseExpectedValue(String[] components) {
        mExpectedFrequencies.add(parseExpectedFrequency(components));
    }

    public void testGetFrequency() {
        assertEquals(getExpectedFrequencies().size(), getRange().getCount());
        for (int i = 0; i < getRange().getCount(); i++) {
            assertTrue(getExpectedFrequencies().get(i) == getRange().getValue(i + 1));
        }
    }

    public void testFind() {
        assertEquals(getExpectedFrequencies().size(), getRange().getCount());
        for (int i = 0; i < getRange().getCount(); i++) {
            final long expectedValue = getExpectedFrequencies().get(i);
            assertEquals(i + 1, getRange().find(expectedValue));
        }
    }

    public void testFindPrev() {
        final long STEP = 10L;

        final int firstIndex = 1;
        final long firstValue = getRange().getValue(firstIndex);
        testFindPrev(firstValue - STEP, Range.INVALID_INDEX);
        testFindPrev(firstValue, Range.INVALID_INDEX);
        testFindPrev(firstValue + STEP, firstIndex);

        final int lastIndex = getRange().getCount();
        final long lastValue = getRange().getValue(lastIndex);
        testFindPrev(lastValue - STEP, lastIndex - 1);
        testFindPrev(lastValue, lastIndex - 1);
        testFindPrev(lastValue + STEP, lastIndex);
    }


    public void testFindNext() {
        final long STEP = 10L;

        final int firstIndex = 1;
        final long firstValue = getRange().getValue(1);
        testFindNext(firstValue - STEP, firstIndex);
        testFindNext(firstValue, firstIndex + 1);
        testFindNext(firstValue + STEP, firstIndex + 1);

        final int lastIndex = getRange().getCount();
        final long lastValue = getRange().getValue(lastIndex);
        testFindNext(lastValue - STEP, lastIndex);
        testFindNext(lastValue, Range.INVALID_INDEX);
        testFindNext(lastValue + STEP, Range.INVALID_INDEX);
    }

    protected void testFindNext(long value, int expectedIndex) {
        assertEquals(expectedIndex, getRange().findNext(value));
    }

    protected void testFindPrev(long value, int expectedIndex) {
        assertEquals(expectedIndex, getRange().findPrev(value));
    }
}
