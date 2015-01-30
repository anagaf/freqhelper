package com.anagaf.freqhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class FrequencyComponentEdit extends EditText {
    private String mBackupText;
    private Listener mListener;

    public FrequencyComponentEdit(Context context) {
        super(context);
        init();
    }

    public FrequencyComponentEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FrequencyComponentEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void setValue(int value) {
        setText(frequencyComponentIntegerToString(value));
    }

    private void init() {
        setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mBackupText = getText().toString();
                    setText("");
                } else if (mBackupText != null) {
                    setText(mBackupText);
                }
            }
        });
        setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    final String text = getText().toString();
                    setValue(text.isEmpty() ? 0 : Integer.valueOf(text));

                    mBackupText = null;

                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindowToken(), 0);

                    mListener.onChanged();

                    clearFocus();

                    return true;
                }
                return false;
            }
        });
    }

    private static String frequencyComponentIntegerToString(Integer value) {
        return String.format("%03d", value);
    }

    /**
     * ******* Inner Classes *********
     */

    public interface Listener {
        public void onChanged();
    }
}
