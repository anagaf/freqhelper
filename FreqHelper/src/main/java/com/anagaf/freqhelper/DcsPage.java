package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.keys.DcsCode;
import com.anagaf.freqhelper.model.ranges.DirectDcs;
import com.anagaf.freqhelper.model.keys.Key;
import com.anagaf.freqhelper.model.ranges.InverseDcs;

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
        mDirectCodeEdit.setValue(code.getValue().intValue());

        final DcsCode inverseCode = DirectDcs.getInverseCode(code);
        if (inverseCode == null) {
            mInverseCodeEdit.setText("--");
        } else {
            mInverseCodeEdit.setValue(inverseCode.getValue().intValue());
        }
        updateRanges();
    }


    @Override
    protected Key getKey() {
        final Integer dcsCodeValue = frequencyComponentStringToInteger(mDirectCodeEdit.getText().toString());
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
