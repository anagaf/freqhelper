package com.anagaf.freqhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.anagaf.freqhelper.model.Ctcss64;
import com.anagaf.freqhelper.model.Frequency;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Range;

class Settings {
    private static final String CHANNEL_FREQUENCY_KEY = "ChannelFrequency";
    private static final String CTCSS_FREQUENCY_KEY = "CtcssFrequency";

    public static void setChannelFrequency(Context context, Frequency frequency) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putLong(CHANNEL_FREQUENCY_KEY, frequency.getDeciHertz()).apply();
    }

    public static Frequency getChannelFrequency(Context context) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final Frequency frequency;
        if (prefs.contains(CHANNEL_FREQUENCY_KEY)) {
            frequency = Frequency.newFrequency(prefs.getLong(CHANNEL_FREQUENCY_KEY, 0));
        } else {
            Range lpd8 = new Lpd8();
            frequency = lpd8.getFrequency(1);
            setChannelFrequency(context, frequency);
        }
        return frequency;
    }

    public static Frequency getCtcssFrequency(Context context) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final Frequency frequency;
        if (prefs.contains(CTCSS_FREQUENCY_KEY)) {
            frequency = Frequency.newFrequency(prefs.getLong(CTCSS_FREQUENCY_KEY, 0));
        } else {
            Range ctcss64 = new Ctcss64();
            frequency = ctcss64.getFrequency(1);
            setChannelFrequency(context, frequency);
        }
        return frequency;
    }

    public static void setCtcssFrequency(Context context, Frequency frequency) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putLong(CTCSS_FREQUENCY_KEY, frequency.getDeciHertz()).apply();
    }
}
