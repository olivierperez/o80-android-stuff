package fr.o80.androidstuff.android;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;

import javax.inject.Inject;

public class AccessibilityService {

    private final Context context;

    @Inject
    public AccessibilityService(Context context) {
        this.context = context;
    }

    /**
     * Checks if context is in accessibility mode.
     */
    public boolean isInAccessibilityMode() {
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        return am.isTouchExplorationEnabled();
    }

    /**
     * Put a space between all caracters.<br/>
     * eg: "a5f" => "a 5 f"
     *
     * @param content The caracter to split.
     * @return The input with spaces.
     */
    public String readOneByOne(String content) {
        if (content == null) {
            return null;
        }
        return content.replaceAll("", " ").trim();
    }
}
