package com.anagaf.freqhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    private static final String FREQUENCY_KEY = "Frequency";

    public static void setFrequency(Context context, Frequency frequency) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(FREQUENCY_KEY, frequency.getHertz()).commit();
    }

    public static Frequency getFrequency(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final Integer hertz = prefs.getInt(FREQUENCY_KEY, 0);
        return new Frequency(hertz);
    }
}
