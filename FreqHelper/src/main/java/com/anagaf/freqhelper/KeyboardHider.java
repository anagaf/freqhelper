package com.anagaf.freqhelper;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class KeyboardHider implements TextView.OnEditorActionListener {
    private Context mContext;
    private EditText mEditText;

    public KeyboardHider(Context context, EditText editText) {
        mContext = context;
        mEditText = editText;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            return true;
        }
        return false;
    }
}
