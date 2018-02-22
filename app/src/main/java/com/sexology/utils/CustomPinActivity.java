package com.sexology.utils;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.omadahealth.lollipin.lib.managers.AppLock;
import com.github.omadahealth.lollipin.lib.managers.AppLockActivity;
import com.sexology.R;

/**
 * Created by oliviergoutay on 1/14/15.
 */
public class CustomPinActivity extends AppLockActivity {

    @Override
    public void showForgotDialog() {
        new MaterialDialog.Builder(this)
                .title("Forgot Passcode")
                .content(R.string.chane_passcode_text)
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(CustomPinActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(CustomPinActivity.this, "No", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onPinFailure(int attempts) {
        Log.d("test", "onPinFailure: " + attempts);
    }

    @Override
    public void onPinSuccess(int attempts) {
        setResult(RESULT_OK);
    }

    @Override
    public int getPinLength() {
        return super.getPinLength();//you can override this method to change the pin length from the default 4
    }

    @Override
    public void onBackPressed() {
        if (mType == AppLock.ENABLE_PINLOCK) {
            setResult(RESULT_CANCELED);
            finish();
        } else super.onBackPressed();
    }
}