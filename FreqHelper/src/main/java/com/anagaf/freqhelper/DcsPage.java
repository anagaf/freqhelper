package com.anagaf.freqhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

public class DcsPage extends Page {
    private static final String DCS_CODE_KEY = "DcsCode";

    private TableLayout mRangesLayout;
    private FrequencyComponentEdit mDirectCodeEdit;
    private FrequencyComponentEdit mInverseCodeEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dcs, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

        mDirectCodeEdit = (FrequencyComponentEdit) view.findViewById(R.id.dcs_direct_code_edit);
        //mDirectCodeEdit.setListener(getFrequencyComponentEditListener());

        mInverseCodeEdit = (FrequencyComponentEdit) view.findViewById(R.id.dcs_inverse_code_edit);

        //addRangeRow(inflater, new DirectDcs());

//        updateFrequency();

        return view;
    }

    @Override
    public void pushCurrentStateToBackStack() {
        final long code = Settings.read(getActivity(), DCS_CODE_KEY);
        BackStack.getsInstance().push(new BackStack.Item(getIndex(), code));
    }

    @Override
    public void restoreState(long value) {
        Settings.write(getActivity(), DCS_CODE_KEY, value);
        //updateFrequency();
    }

    @Override
    protected TableLayout getRangesLayout() {
        return null;
    }

//    @Override
//    protected TableLayout getRangesLayout() {
//        return mRangesLayout;
//    }
//
//    @Override
//    protected void updateFrequency() {
//        final DcsCode code = (DcsCode) readFrequencyFromSettings(getActivity());
//        mDirectCodeEdit.setValue(code.getValue().intValue());
//
//        final DcsCode inverseCode = DirectDcs.getInverseCode(code);
//        if (inverseCode == null) {
//            mInverseCodeEdit.setText("--");
//        } else {
//            mInverseCodeEdit.setValue(inverseCode.getValue().intValue());
//        }
//        updateRanges();
//    }
//
//
//    @Override
//    protected Frequency getFrequency() {
//        final Integer dcsCodeValue = frequencyComponentStringToInteger(mDirectCodeEdit.getText().toString());
//        return new DcsCode(dcsCodeValue);
//    }
//
//    @Override
//    protected Frequency getDefaultKey() {
//        return new DirectDcs().getFrequency(1);
//    }
//
//    @Override
//    protected String getSettingsKey() {
//        return "DcsCode";
//    }
//
//    @Override
//    protected Frequency createKey(Long value) {
//        return new DcsCode(value);
//    }
}
