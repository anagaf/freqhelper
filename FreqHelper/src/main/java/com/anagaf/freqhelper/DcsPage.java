package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.ranges.Dcs;

public class DcsPage extends Page {

    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mDirectCodeEdit;
    private FrequencyComponentEdit mInverseCodeEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dcs, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

        mDirectCodeEdit = (FrequencyComponentEdit) view.findViewById(R.id.dcs_direct_code_edit);
        mDirectCodeEdit.setListener(getFrequencyComponentEditListener());

        mInverseCodeEdit = (FrequencyComponentEdit) view.findViewById(R.id.dcs_inverse_code_edit);

        addRangeRow(inflater, new Dcs());

        updateValue();

        return view;
    }

    @Override
    protected TableLayout getRangesLayout() {
        return mRangesLayout;
    }

    @Override
    protected void updateValue() {
        final Long code = readValueFromSettings(getActivity());
        mDirectCodeEdit.setValue(code.intValue());
        updateRanges();
    }

    @Override
    protected long getDefaultValue() {
        return new Dcs().getValue(1);
    }

    @Override
    protected String getSettingsKey() {
        return "DcsCode";
    }

    @Override
    protected long getValue() {
        return valueComponentStringToInteger(mDirectCodeEdit.getText().toString());
    }
}
