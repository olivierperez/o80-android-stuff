package fr.o80.androidstuff.android;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.support.v4.content.ContextCompat;

import timber.log.Timber;

@TargetApi(FingerprintHelper.MIN_VERSION_FINGERPRINT)
public final class FingerprintHelper {

    public static final int MIN_VERSION_FINGERPRINT = Build.VERSION_CODES.M;
    private static final String TAG = "FingerprintHelper";

    private FingerprintHelper() {
        //never called
    }

    public static int getMinTargetAPIForFingerprint() {
        return MIN_VERSION_FINGERPRINT;
    }

    public static boolean isCurrentSDKCompatibleForFingerprint() {
        return Build.VERSION.SDK_INT >= getMinTargetAPIForFingerprint();
    }

    public static boolean hasPermissionForFingerprint(Context context) {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED);
    }

    @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
    public static boolean hasHardwareForFingerprint(FingerprintManager fingerprintManager) {
        try {
            return fingerprintManager.isHardwareDetected();
        } catch (SecurityException e) {
            return false;
        }
    }

    @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
    public static boolean hasFingerprintsConfiguredForFingerprint(FingerprintManager fingerprintManager) {
        try {
            return fingerprintManager.hasEnrolledFingerprints();
        } catch (SecurityException e) {
            return false;
        }
    }

    @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
    public static boolean canUseFingerprintCompleteCheck(Activity activity) {
        boolean canUseFingerprint = false;
        if (isCurrentSDKCompatibleForFingerprint()) {
            Context context = activity.getBaseContext();
            if (hasPermissionForFingerprint(context)) {
                FingerprintManager fingerprintManager = (FingerprintManager) activity.getSystemService(Context.FINGERPRINT_SERVICE);
                if (hasHardwareForFingerprint(fingerprintManager)) {
                    if (hasFingerprintsConfiguredForFingerprint(fingerprintManager)) {
                        canUseFingerprint = true;
                    } else {
                        Timber.tag(TAG).d("No fingerprins enrolled");
                    }
                } else {
                    Timber.tag(TAG).d("No hardware detected for fingerprint authentication");
                }
            } else {
                Timber.tag(TAG).d("Missing permissions for fingerprint authentication");
            }
        } else {
            Timber.tag(TAG).d("SDK not compatible");
        }
        return canUseFingerprint;
    }
}
