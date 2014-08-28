package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;

public interface Range {

    public static final int INVALID_CHANNEL = 0;

    Integer getNameStringId();

    int getChannelCount();

    Frequency getFrequency(int channel);

    int getChannel(Frequency frequency);

    Entry getLowerEntry(Frequency frequency);

    Entry getCeilingEntry(Frequency frequency);

    /********** Inner Classes **********/

    public class Entry {
        private final Frequency mFrequency;
        private final int mChannel;

        public Entry(Frequency frequency, int channel) {
            mFrequency = frequency;
            mChannel = channel;
        }

        public Frequency getFrequency() {
            return mFrequency;
        }

        public int getChannel() {
            return mChannel;
        }
    }
}
