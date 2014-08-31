package com.anagaf.freqhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    private static final String FREQUENCY_KEY = "Frequency";
    private static final String MODE_INDEX_KEY = "ModeIndex";

    public static void setFrequency(Context context, Frequency frequency) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(FREQUENCY_KEY, frequency.getHertz()).commit();
    }

    public static Frequency getFrequency(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final Integer hertz = prefs.getInt(FREQUENCY_KEY, 0);
        return new Frequency(hertz);
    }

    public static void setModeIndex(Context context, int modeIndex) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(MODE_INDEX_KEY, modeIndex).commit();
    }

    public static int getModeIndex(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(MODE_INDEX_KEY, 0);
    }
}
