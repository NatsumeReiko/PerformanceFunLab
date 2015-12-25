package lab.fun.performance.Utility.font;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

    private static final String FONT_BOLD   = "srgb_20141216";
    private static final String FONT_NORMAL = "srgm_20141216";

    private static FontManager instance;
    private static Typeface normalFont;
    private static Typeface boldFont;

    private FontManager(Context context) {
        instance = this;
        normalFont =  Typeface.createFromAsset(context.getAssets(), FONT_NORMAL);
        boldFont = Typeface.createFromAsset(context.getAssets(), FONT_BOLD);
    }

    public static FontManager getInstance(Context context) {
        if (instance == null) {
            instance =  new FontManager(context);
        }
        return instance;
    }

    public Typeface getNormalFont() {
        return normalFont;
    }

    public Typeface getBoldFont() {
        return boldFont;
    }
}
