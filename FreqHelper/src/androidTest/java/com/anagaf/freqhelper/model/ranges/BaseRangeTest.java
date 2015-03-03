package com.anagaf.freqhelper.model.ranges;

import android.content.Context;
import android.content.res.AssetManager;
import android.test.AndroidTestCase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseRangeTest extends AndroidTestCase {

    protected abstract void parseExpectedValue(String[] components);

    protected abstract String getExpectedValuesFileName();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        BufferedReader reader = null;
        try {
            AssetManager manager = getTestContext().getAssets();
            reader = new BufferedReader(new InputStreamReader(manager.open(getExpectedValuesFileName()), "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    parseExpectedValue(line.split("\\."));
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

    private Context getTestContext() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTestContext = AndroidTestCase.class.getMethod("getTestContext");
        return (Context) getTestContext.invoke(this);
    }

}
