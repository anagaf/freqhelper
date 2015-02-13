package com.anagaf.freqhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public abstract class BaseEdit extends EditText {
    private String mBackupText;
    private Listener mListener;

    public BaseEdit(Context context) {
        super(context);
        init();
    }

    public BaseEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected abstract void setValue(int value);

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
                    int value;
                    try {
                        value = Integer.valueOf(text);
                    } catch (NumberFormatException ex) {
                        value = 0;
                    }
                    setValue(value);

                    mBackupText = null;

                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindowToken(), 0);

                    mListener.onValueChanged(value);

                    clearFocus();

                    return true;
                }
                return false;
            }
        });
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    /**
     * ******* Inner Classes *********
     */

    public interface Listener {
        public void onValueChanged(int value);
    }
}
