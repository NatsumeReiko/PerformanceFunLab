package lab.fun.performance.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import lab.fun.performance.R;
import lab.fun.performance.Utility.font.FontManager;


public class TargetTextView extends TextView {

    private boolean isUserInputText;

    private final int initialTextStyle;

    public TargetTextView(Context context) {
        this(context, null);
    }

    public TargetTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TargetTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode()) {
            initialTextStyle = Typeface.NORMAL;
            return;
        }

        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.TargetTextView);

        isUserInputText = typedArray.getBoolean(R.styleable.TargetTextView_is_user_input_text, true);

        Typeface typeface = getTypeface();
        if (typeface != null) {
            initialTextStyle = getTypeface().getStyle();
        } else {
            initialTextStyle = Typeface.NORMAL;
        }
    }

    public boolean isUserInputText() {
        return isUserInputText;
    }

    public void setUserInputText(boolean isUserInputText) {
        this.isUserInputText = isUserInputText;

        updateFont();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        updateFont();
    }

    /**
     * フォントを更新する
     */
    private void updateFont() {
        Typeface typeface;

        //ユーザー入力の文字列を含む場合はデバイスフォント、含まない場合は新丸ゴのフォントで表示する
        if (isUserInputText) {
            typeface = Typeface.defaultFromStyle(initialTextStyle);
        } else {
            FontManager fontManager = FontManager.getInstance(getContext());
            boolean isBold = (initialTextStyle == Typeface.BOLD);

            if (isBold) {
                typeface = fontManager.getBoldFont();
            } else {
                typeface = fontManager.getNormalFont();
            }
        }
        setTypeface(typeface);
    }
}
