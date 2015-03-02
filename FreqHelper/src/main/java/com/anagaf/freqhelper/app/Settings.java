package com.anagaf.freqhelper.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.anagaf.freqhelper.model.ranges.Range;

public class Settings {
    public static void write(Context context, String frequency, long value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putLong(frequency, value).apply();
    }

    public static long read(Context context, String key) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.contains(key)) {
            return prefs.getLong(key, Range.INVALID_VALUE);
        } else {
            return Range.INVALID_VALUE;
        }
    }
}
