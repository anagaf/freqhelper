package com.anagaf.freqhelper.ui.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.anagaf.freqhelper.ui.views.AbstractEdit;
import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.ui.views.RangeView;
import com.anagaf.freqhelper.model.ranges.Dcs;
import com.anagaf.freqhelper.ui.views.ValueEdit;

public class DcsPage extends Page {

    private TableLayout mRangesLayout;
    private ValueEdit mDirectCodeEdit;
    private ValueEdit mInverseCodeEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dcs, null);

        mRangesLayout = (TableLayout) view.findViewById(R.id.ranges_layout);

        mDirectCodeEdit = (ValueEdit) view.findViewById(R.id.dcs_direct_code_edit);
        mDirectCodeEdit.setListener(new AbstractEdit.Listener() {

            @Override
            public void onValueChanged() {
                pushCurrentStateToBackStack();
                final long value = mDirectCodeEdit.getValue();
                writeValueToSettings(getActivity(), value);
                updateValue();
            }
        });

        mInverseCodeEdit = (ValueEdit) view.findViewById(R.id.dcs_inverse_code_edit);
        mInverseCodeEdit.setListener(new AbstractEdit.Listener() {

            @Override
            public void onValueChanged() {
                pushCurrentStateToBackStack();
                final long value = mInverseCodeEdit.getValue();
                writeValueToSettings(getActivity(), -1 * value);
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
        final long code = readValueFromSettings(getActivity());
        final Long directCode;
        final Long inverseCode;
        if (code > 0) {
            directCode = code;
            inverseCode = Dcs.getInverseCode(directCode);
        } else {
            inverseCode = code;
            directCode = Dcs.getDirectCode(inverseCode);
        }
        mDirectCodeEdit.setValue(directCode.intValue());
        mInverseCodeEdit.setValue(inverseCode.intValue());

        updateRanges(directCode);
    }

    @Override
    protected long getDefaultValue() {
        return new Dcs().getValue(1);
    }

    @Override
    protected String getSettingsKey() {
        return "DcsCode";
    }

    private void updateRanges(long directCode) {
        for (int i = 0; i < getRangesLayout().getChildCount(); i++) {
            final RangeView row = (RangeView) getRangesLayout().getChildAt(i);
            row.setValue(directCode);
        }
    }
}
