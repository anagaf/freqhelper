package com.anagaf.freqhelper.converters;

import android.content.Context;
import android.content.res.AssetManager;
import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;
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

    private Context getTestContext() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTestContext = AndroidTestCase.class.getMethod("getTestContext");
        return (Context) getTestContext.invoke(this);
    }
}
