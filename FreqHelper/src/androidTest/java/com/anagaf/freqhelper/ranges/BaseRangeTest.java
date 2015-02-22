package com.anagaf.freqhelper.ranges;

import android.content.Context;
import android.content.res.AssetManager;
import android.test.AndroidTestCase;

import com.anagaf.freqhelper.model.Frequency;
import com.anagaf.freqhelper.model.Range;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRangeTest extends AndroidTestCase {
    private List<Frequency> mExpectedFrequencies = new ArrayList<>();

    protected abstract String getExpectedFrequenciesFileName();

    protected abstract Range getRange();

    protected abstract Frequency createFrequency(String[] frequencyComponents);

    protected List<Frequency> getExpectedFrequencies() {
        return mExpectedFrequencies;
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
                    mExpectedFrequencies.add(createFrequency(line.split("\\.")));
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
        assertEquals(getExpectedFrequencies().size(), getRange().getCount());
        for (int i = 0; i < getRange().getCount(); i++) {
            assertEquals(getExpectedFrequencies().get(i), getRange().getFrequency(i + 1));
        }
    }

    public void testFind() {
        assertEquals(getExpectedFrequencies().size(), getRange().getCount());
        for (int i = 0; i < getRange().getCount(); i++) {
            assertEquals(i + 1, getRange().find(getExpectedFrequencies().get(i)));
        }
    }


    protected void testFindPrev(Frequency frequency, int expectedIndex) {
        assertEquals(expectedIndex, getRange().findPrev(frequency));
    }

    public void testFindPrev() {
        final long STEP = 10L;

        final int firstIndex = 1;
        final Frequency firstFrequency = getRange().getFrequency(firstIndex);
        testFindPrev(Frequency.newFrequency(firstFrequency.getDeciHertz() - STEP), Range.INVALID_INDEX);
        testFindPrev(firstFrequency, Range.INVALID_INDEX);
        testFindPrev(Frequency.newFrequency(firstFrequency.getDeciHertz() + STEP), firstIndex);

        final int lastIndex = getRange().getCount();
        final Frequency lastFrequency = getRange().getFrequency(lastIndex);
        testFindPrev(Frequency.newFrequency(lastFrequency.getDeciHertz() - STEP), lastIndex - 1);
        testFindPrev(lastFrequency, lastIndex - 1);
        testFindPrev(Frequency.newFrequency(lastFrequency.getDeciHertz() + STEP), lastIndex);
    }


    public void testFindNext() {
        final long STEP = 10L;

        final int firstIndex = 1;
        final Frequency firstFrequency = getRange().getFrequency(1);
        testFindNext(Frequency.newFrequency(firstFrequency.getDeciHertz() - STEP), firstIndex);
        testFindNext(firstFrequency, firstIndex + 1);
        testFindNext(Frequency.newFrequency(firstFrequency.getDeciHertz() + STEP), firstIndex + 1);

        final int lastIndex = getRange().getCount();
        final Frequency lastFrequency = getRange().getFrequency(lastIndex);
        testFindNext(Frequency.newFrequency(lastFrequency.getDeciHertz() - STEP), lastIndex);
        testFindNext(lastFrequency, Range.INVALID_INDEX);
        testFindNext(Frequency.newFrequency(lastFrequency.getDeciHertz() + STEP), Range.INVALID_INDEX);
    }

    protected void testFindNext(Frequency frequency, int expectedIndex) {
        assertEquals(expectedIndex, getRange().findNext(frequency));
    }

    private Context getTestContext() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTestContext = AndroidTestCase.class.getMethod("getTestContext");
        return (Context) getTestContext.invoke(this);
    }

}
