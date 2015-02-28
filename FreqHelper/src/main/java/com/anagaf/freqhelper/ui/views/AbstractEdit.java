package com.anagaf.freqhelper.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public abstract class AbstractEdit extends EditText {
    private static final int DEFAULT_MAX_LENGTH = 1;

    protected int mMaxLength;
    private String mInvalidValue;
    private String mBackupText;
    private Listener mListener;

    public AbstractEdit(Context context) {
        super(context);
        init(null);
    }

    public AbstractEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AbstractEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setInvalidValue() {
        setText(mInvalidValue);
    }

    public boolean isInvalidValue() {
        return getText().toString().equals(mInvalidValue);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedAttrs = getContext().getTheme().obtainStyledAttributes(attrs, new int[]{android.R.attr.maxLength}, 0, 0);
            try {
                mMaxLength = typedAttrs.getInt(0, DEFAULT_MAX_LENGTH);
            } finally {
                typedAttrs.recycle();
            }
        }

        mInvalidValue = String.format("%0" + mMaxLength + "d", 0).replace('0', '-');

        setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(mMaxLength)
        });

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
                if (actionId == EditorInfo.IME_ACTION_DONE && !isInvalidValue()) {
                    mBackupText = null;

                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindowToken(), 0);

                    mListener.onValueChanged();

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
        public void onValueChanged();
    }
}
