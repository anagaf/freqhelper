package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;

public interface Range {

    public static final int INVALID_CHANNEL = 0;

    Integer getNameStringId();

    int getChannelCount();

    Frequency getFrequency(int channel);

    int getChannel(Frequency frequency);

    Entry getLowerEntry(Frequency frequency);

    Entry getHigherEntry(Frequency frequency);

    int getPrevChannel(int channel);

    int getNextChannel(int channel);

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry entry = (Entry) o;

            if (mChannel != entry.mChannel) return false;
            if (mFrequency != null ? !mFrequency.equals(entry.mFrequency) : entry.mFrequency != null)
                return false;

            return true;
        }
    }
}
