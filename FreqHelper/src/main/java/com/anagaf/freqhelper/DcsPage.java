package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.DcsCode;
import com.anagaf.freqhelper.model.DirectDcs;
import com.anagaf.freqhelper.model.Key;

public class DcsPage extends Page {
    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mCodeEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dcs, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

        mCodeEdit = (FrequencyComponentEdit) view.findViewById(R.id.dcs_code_edit);
        mCodeEdit.setListener(getFrequencyComponentEditListener());

        addRangeRow(inflater, new DirectDcs());

        updateKey();

        return view;
    }

    @Override
    protected TableLayout getRangesLayout() {
        return mRangesLayout;
    }

    @Override
    protected void updateKey() {
        final DcsCode code = (DcsCode) readKeyFromSettings(getActivity());
        mCodeEdit.setValue(code.getValue().intValue());
        updateRanges();
    }

    @Override
    protected Key getKey() {
        final Integer dcsCodeValue = frequencyComponentStringToInteger(mCodeEdit.getText().toString());
        return new DcsCode(dcsCodeValue);
    }

    @Override
    protected Key getDefaultKey() {
        return new DirectDcs().getKey(1);
    }

    @Override
    protected String getSettingsKey() {
        return "DcsCode";
    }

    @Override
    protected Key createKey(Long value) {
        return new DcsCode(value);
    }
}
