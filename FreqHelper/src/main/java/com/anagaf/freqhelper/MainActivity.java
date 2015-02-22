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
    final List<PageInfo> mPages = new ArrayList<>();
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        addPage(0, new ChannelsFragment(), R.string.dcs);
        addPage(1, new ChannelsFragment(), R.string.channels);
        addPage(2, new CtcssFragment(), R.string.ctcss);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(1); // channels
    }

    @Override
    public void onBackPressed() {
        final BackStack.Item item = BackStack.getsInstance().pop();
        if (item == null) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(item.getPageIndex());
            final BaseMainActivityFragment page = mPages.get(item.getPageIndex()).getFragment();
            page.restoreFrequency(item.getFrequency());
        }
    }

    private void addPage(int index, BaseMainActivityFragment page, int stringResId) {
        final Bundle args = new Bundle();
        args.putInt(BaseMainActivityFragment.PAGE_INDEX_KEY, index);
        page.setArguments(args);
        mPages.add(new PageInfo(page, stringResId));
    }

    /**
     * ******* Inner Classes *********
     */

    private class PageInfo {
        private BaseMainActivityFragment mFragment;
        private int mTitleResId;

        private PageInfo(BaseMainActivityFragment fragment, int titleResId) {
            mFragment = fragment;
            mTitleResId = titleResId;
        }

        public BaseMainActivityFragment getFragment() {
            return mFragment;
        }

        public int getTitleResId() {
            return mTitleResId;
        }
    }

    private class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
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
