package com.softcaretech.croprecommendersystem;

import android.view.View;
import android.widget.Spinner;


import com.google.android.material.textfield.TextInputLayout;

public class Util {
    public static void setErrorNotification(TextInputLayout textInputLayout){
        textInputLayout.setError("This field is required");
    }
    public static void clearErrorNotification(TextInputLayout textInputLayout){
        textInputLayout.setError(null);
    }
    public static void setErrorNotificationForSpinner(Spinner spinner, TextInputLayout spinnerLayout){
        String selectedItem = spinner.getSelectedItem().toString();
        if (" ".equals(selectedItem)) {
            spinnerLayout.setError("Please select an algorithm");
        } else {
            spinnerLayout.setError(null); // Clear any previous error message
        }
    }
    public static void clearErrorNotificationForSpinner(Spinner spinner, TextInputLayout spinnerLayout){
        spinnerLayout.setError(null); // Clear any previous error message
    }
}
