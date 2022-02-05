package com.forbitbd.lawyersdiary.utils;

import android.util.Patterns;

public class MyUtil {

    private static final String DATE_FORMAT="d MMM yyyy";

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
