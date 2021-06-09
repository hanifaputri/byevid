package com.example.byevid.utils;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtil {
    private Activity from;
    private Class<?> to;
    boolean isFinishMode;

    public void showActivity(Activity from, Class<?> to, boolean finish) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);

        if (finish) {
            from.finish();
        }
    }

    public void showActivity(Activity from, Class<?> to) {

    }
}
