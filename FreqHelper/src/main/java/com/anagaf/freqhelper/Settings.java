package com.anagaf.freqhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.anagaf.freqhelper.model.Range;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Settings {
    private static final String FREQUENCY_KEY = "Frequency";
    private static final String MODE_INDEX_KEY = "ModeIndex";
    private static final String CHANNELS_KEY = "Channels";

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

    public static int getModeChannel(Context context, String modeKey) {
        int channel = Range.INVALID_CHANNEL;
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final String channelsString = prefs.getString(CHANNELS_KEY, "");
        if (!channelsString.isEmpty()) {
            try {
                final JSONObject channelsJson = new JSONObject(channelsString);
                channel = channelsJson.getInt(modeKey);
            } catch (JSONException e) {
                channel = Range.INVALID_CHANNEL;
            }
        }
        return channel;
    }

    public static void setModeChannel(Context context, String modeKey, int channel) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String channelsString = prefs.getString(CHANNELS_KEY, "");
        try {
            JSONObject channelsJson;
            if (channelsString.isEmpty()) {
                channelsJson = new JSONObject();
            } else {
                channelsJson = new JSONObject(channelsString);
            }
            if (channelsJson.has(modeKey) && channelsJson.getInt(modeKey) != channel) {
                channelsJson.put(modeKey, channel);
                channelsString = channelsJson.toString();
                prefs.edit().putString(CHANNELS_KEY, channelsString).commit();
            }
        } catch (JSONException e) {
            Log.e("FreqHelper", "JSON error: " + e.getLocalizedMessage());
        }
    }
}
