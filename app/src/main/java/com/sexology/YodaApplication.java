package com.sexology;

import android.app.Application;

import com.github.omadahealth.lollipin.lib.managers.LockManager;
import com.sexology.utils.CustomPinActivity;

/**
 * Created by piyush on 18/2/18.
 */

public class YodaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        enablePasscode();
    }

    public void enablePasscode() {
        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
        lockManager.enableAppLock(this, CustomPinActivity.class);
    }

    public void disablePasscode() {
        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
        lockManager.disableAppLock();
    }
}
