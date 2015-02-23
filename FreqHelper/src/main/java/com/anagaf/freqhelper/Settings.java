package com.anagaf.freqhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class Settings {
    public static void write(Context context, String frequency, Long value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putLong(frequency, value).commit();
    }

    public static Long read(Context context, String key) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.contains(key)) {
            return prefs.getLong(key, 0L);
        } else {
            return null;
        }
    }
}
