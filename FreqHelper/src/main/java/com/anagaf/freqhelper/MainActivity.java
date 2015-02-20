package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(1); // channels
    }

    /**
     * ******* Inner Classes *********
     */

    private class PageInfo {
        private Fragment mFragment;
        private int mTitleResId;

        private PageInfo(Fragment fragment, int titleResId) {
            mFragment = fragment;
            mTitleResId = titleResId;
        }

        public Fragment getFragment() {
            return mFragment;
        }

        public int getTitleResId() {
            return mTitleResId;
        }
    }

    private class Adapter extends FragmentPagerAdapter {

        final List<PageInfo> mPages;

        public Adapter(FragmentManager fm) {
            super(fm);

            mPages = new ArrayList<>();
            mPages.add(new PageInfo(new ChannelsFragment(), R.string.dcs));
            mPages.add(new PageInfo(new ChannelsFragment(), R.string.channels));
            mPages.add(new PageInfo(new CtcssFragment(), R.string.ctcss));
        }

        @Override
        public Fragment getItem(int position) {
            return mPages.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(mPages.get(position).getTitleResId());
        }
    }
}
