package com.anagaf.freqhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Range;

class Settings {
    private static final String FREQUENCY_KEY = "Frequency";

    public static void setFrequency(Context context, Frequency frequency) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(FREQUENCY_KEY, frequency.getHertz()).apply();
    }

    public static Frequency getFrequency(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Frequency frequency;
        if (prefs.contains(FREQUENCY_KEY)) {
            frequency = Frequency.newChannelFrequency(prefs.getInt(FREQUENCY_KEY, 0));
        } else {
            Range lpd8 = new Lpd8();
            frequency = lpd8.getFrequency(1);
            setFrequency(context, frequency);
        }
        return frequency;
    }
}
