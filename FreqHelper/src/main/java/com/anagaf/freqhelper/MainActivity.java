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
    private int mCurrentPageIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        addPage(0, new ChannelsPage(), R.string.dcs);
        addPage(1, new ChannelsPage(), R.string.channels);
        addPage(2, new CtcssPage(), R.string.ctcss);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int index) {
                if (mCurrentPageIndex > 0 && index != mCurrentPageIndex) {
                    mPages.get(mCurrentPageIndex).getPage().pushCurrentStateToBackStack();
                }
                mCurrentPageIndex = index;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(1); // channels
    }

    @Override
    public void onBackPressed() {
        final BackStack.Item item = BackStack.getsInstance().pop();
        if (item == null) {
            super.onBackPressed();
        } else {
            final boolean shouldPopToPreventLoop = mCurrentPageIndex > 0
                    && item.getPageIndex() != mCurrentPageIndex;
            mViewPager.setCurrentItem(item.getPageIndex());
            if (shouldPopToPreventLoop) {
                BackStack.getsInstance().pop();
            }
            final Page page = mPages.get(item.getPageIndex()).getPage();
            page.restoreFrequency(item.getFrequency());
        }
    }

    private void addPage(int index, Page page, int stringResId) {
        final Bundle args = new Bundle();
        args.putInt(Page.PAGE_INDEX_KEY, index);
        page.setArguments(args);
        mPages.add(new PageInfo(page, stringResId));
    }

    /**
     * ******* Inner Classes *********
     */

    private class PageInfo {
        private Page mPage;
        private int mTitleResId;

        private PageInfo(Page page, int titleResId) {
            mPage = page;
            mTitleResId = titleResId;
        }

        public Page getPage() {
            return mPage;
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
            return mPages.get(position).getPage();
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
