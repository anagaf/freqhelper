package com.anagaf.freqhelper.model.ranges;

import android.content.Context;
import android.content.res.AssetManager;
import android.test.AndroidTestCase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRangeTest extends AndroidTestCase {
    private List<Long> mExpectedValues = new ArrayList<>();

    protected abstract String getExpectedFrequenciesFileName();

    protected abstract Range getRange();

    protected abstract long parseValue(String[] components);

    protected List<Long> getExpectedValues() {
        return mExpectedValues;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        BufferedReader reader = null;
        try {
            AssetManager manager = getTestContext().getAssets();
            reader = new BufferedReader(new InputStreamReader(manager.open(getExpectedFrequenciesFileName()), "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    mExpectedValues.add(parseValue(line.split("\\.")));
                }
                line = reader.readLine();
            }
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public void testGetFrequency() {
        assertEquals(getExpectedValues().size(), getRange().getCount());
        for (int i = 0; i < getRange().getCount(); i++) {
            assertTrue(getExpectedValues().get(i) == getRange().getValue(i + 1));
        }
    }

    public void testFind() {
        assertEquals(getExpectedValues().size(), getRange().getCount());
        for (int i = 0; i < getRange().getCount(); i++) {
            final long expectedValue = getExpectedValues().get(i);
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

    private Context getTestContext() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTestContext = AndroidTestCase.class.getMethod("getTestContext");
        return (Context) getTestContext.invoke(this);
    }

}
