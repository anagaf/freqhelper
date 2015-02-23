package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.model.ranges.Dcs;
import com.anagaf.freqhelper.model.ranges.Range;

public class DcsPage extends Page {

    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mDirectCodeEdit;
    private FrequencyComponentEdit mInverseCodeEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dcs, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

        mDirectCodeEdit = (FrequencyComponentEdit) view.findViewById(R.id.dcs_direct_code_edit);
        mDirectCodeEdit.setListener(new FrequencyComponentEdit.Listener() {

            @Override
            public void onValueChanged(int value) {
                pushCurrentStateToBackStack();
                writeValueToSettings(getActivity(), value);
                updateRanges();
                updateValue();
            }
        });

        mInverseCodeEdit = (FrequencyComponentEdit) view.findViewById(R.id.dcs_inverse_code_edit);
        mInverseCodeEdit.setListener(new FrequencyComponentEdit.Listener() {

            @Override
            public void onValueChanged(int value) {
                pushCurrentStateToBackStack();
                writeValueToSettings(getActivity(), -1 * value);
                updateRanges();
                updateValue();
            }
        });

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
        final Long inverseCode = Dcs.getInverseCode(code);
        mDirectCodeEdit.setValue(code.intValue());
        if (inverseCode != Range.INVALID_VALUE) {
            mInverseCodeEdit.setValue(inverseCode.intValue());
        } else {
            mInverseCodeEdit.setText("--");
        }
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
        return getDirectCode();
    }

    private long getDirectCode() {
        return valueComponentStringToInteger(mDirectCodeEdit.getText().toString());
    }

}
