package com.livos.companionplants.util;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class KeyboardUtil {

    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
