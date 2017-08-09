@file:JvmName("FingerprintHelper")

package fr.o80.androidstuff.android

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.support.annotation.RequiresPermission
import android.support.v4.content.ContextCompat

import timber.log.Timber

fun isCurrentSDKCompatibleForFingerprint(): Boolean =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

@TargetApi(value = Build.VERSION_CODES.M)
fun Context.hasPermissionForFingerprint(): Boolean =
        isCurrentSDKCompatibleForFingerprint()
                && ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED

@TargetApi(value = Build.VERSION_CODES.M)
@RequiresPermission(value = Manifest.permission.USE_FINGERPRINT)
fun FingerprintManager.hasHardwareForFingerprint(): Boolean =
        if (isCurrentSDKCompatibleForFingerprint()) {
            try {
                isHardwareDetected
            } catch (e: SecurityException) {
                false
            }
        } else {
            false
        }

@TargetApi(value = Build.VERSION_CODES.M)
@RequiresPermission(value = Manifest.permission.USE_FINGERPRINT)
fun FingerprintManager.hasFingerprintsConfiguredForFingerprint(): Boolean =
        if (isCurrentSDKCompatibleForFingerprint()) {
            try {
                hasEnrolledFingerprints()
            } catch (e: SecurityException) {
                false
            }
        } else {
            false
        }

@TargetApi(value = Build.VERSION_CODES.M)
@RequiresPermission(value = Manifest.permission.USE_FINGERPRINT)
fun Context.canUseFingerprintCompleteCheck(): Boolean =
        if (isCurrentSDKCompatibleForFingerprint()) {
            if (hasPermissionForFingerprint()) {
                val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
                if (fingerprintManager.hasHardwareForFingerprint()) {
                    if (fingerprintManager.hasFingerprintsConfiguredForFingerprint()) {
                        true
                    } else {
                        Timber.d("No fingerprins enrolled")
                        false
                    }
                } else {
                    Timber.d("No hardware detected for fingerprint authentication")
                    false
                }
            } else {
                Timber.d("Missing permissions for fingerprint authentication")
                false
            }
        } else {
            Timber.d("SDK not compatible")
            false
        }
