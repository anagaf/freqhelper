package com.anagaf.freqhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Pmr;
import com.anagaf.freqhelper.model.Range;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Settings {
    private static final String FREQUENCY_KEY = "Frequency";

    public static void setFrequency(Context context, Frequency frequency) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(FREQUENCY_KEY, frequency.getHertz()).commit();
    }

    public static Frequency getFrequency(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Frequency frequency;
        if (prefs.contains(FREQUENCY_KEY)) {
            frequency = new Frequency(prefs.getInt(FREQUENCY_KEY, 0));
        } else {
            Range lpd8 = new Lpd8();
            frequency = lpd8.getFrequency(1);
            setFrequency(context, frequency);
        }
        return frequency;
    }
}
