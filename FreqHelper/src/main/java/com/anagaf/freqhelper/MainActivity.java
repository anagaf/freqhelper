package com.anagaf.freqhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;

public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener {

    public static final String MODE_INDEX = "mode_index";
    public static final String SWITCH_MODE_ACTION = "switch_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        Mode.getTitles(this)),
                this);

        BroadcastReceiver switchModeBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final int modeIndex = intent.getIntExtra(MODE_INDEX, 0);
                actionBar.setSelectedNavigationItem(modeIndex);
            }
        };
        IntentFilter switchModeBroadcastIntentFilter = new IntentFilter(SWITCH_MODE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(switchModeBroadcastReceiver,
                switchModeBroadcastIntentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final int modeIndex = Settings.getModeIndex(this);
        if (modeIndex >= 0 && modeIndex < getSupportActionBar().getNavigationItemCount()) {
            getSupportActionBar().setSelectedNavigationItem(modeIndex);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(MODE_INDEX)) {
            getSupportActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(MODE_INDEX));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(MODE_INDEX,
                getSupportActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        switchMode(position);
        return true;
    }

    private void switchMode(int index) {
        Settings.setModeIndex(this, index);
        Fragment fragment = Mode.values()[index].getFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
