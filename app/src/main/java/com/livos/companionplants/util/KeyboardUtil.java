package com.livos.companionplants.util;


import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtil {

    public static void hideSoftKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
