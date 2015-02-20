package com.anagaf.freqhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.anagaf.freqhelper.model.Ctcss64;
import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Range;

class Settings {
    private static final String CHANNEL_FREQUENCY_HERTZ_KEY = "FrequencyHertz";
    private static final String CTCSS_FREQUENCY_HERTZ_KEY = "CtcssFrequencyHertz";
    private static final String CTCSS_FREQUENCY_DECI_HERTZ_KEY = "CtcssFrequencyDeciHertz";

    public static void setChannelFrequency(Context context, Frequency frequency) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(CHANNEL_FREQUENCY_HERTZ_KEY, frequency.getHertz()).apply();
    }

    public static Frequency getChannelFrequency(Context context) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final Frequency frequency;
        if (prefs.contains(CHANNEL_FREQUENCY_HERTZ_KEY)) {
            frequency = Frequency.newChannelFrequency(prefs.getInt(CHANNEL_FREQUENCY_HERTZ_KEY, 0));
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
        if (prefs.contains(CTCSS_FREQUENCY_HERTZ_KEY) && prefs.contains(CTCSS_FREQUENCY_DECI_HERTZ_KEY)) {
            final int hertz = prefs.getInt(CTCSS_FREQUENCY_HERTZ_KEY, 0);
            final int hertzFraction = prefs.getInt(CTCSS_FREQUENCY_DECI_HERTZ_KEY, 0);
            frequency = Frequency.newCtcssFrequency(hertz, hertzFraction);
        } else {
            Range ctcss64 = new Ctcss64();
            frequency = ctcss64.getFrequency(1);
            setChannelFrequency(context, frequency);
        }
        return frequency;
    }

    public static void setCtcssFrequency(Context context, Frequency frequency) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(CTCSS_FREQUENCY_HERTZ_KEY, frequency.getHertz())
                .putInt(CTCSS_FREQUENCY_DECI_HERTZ_KEY, frequency.getHertzFractionComponent())
                .apply();

    }
}
