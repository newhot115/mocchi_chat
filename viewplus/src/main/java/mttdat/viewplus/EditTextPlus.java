package mttdat.viewplus;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatEditText;

public class EditTextPlus extends AppCompatEditText {

    private float DEFAULT_GRAPHIC_WIDTH = 750f;
    private float DEFAULT_GRAPHIC_HEIGHT = 1335f;

    private float DEFAULT_CUSTOM_SIZE_PT = 20f;//16

    private float DEFAULT_CUSTOM_SIZE_PX = 39f;//32

    private static final String TAG = "EditText";
    private float graphicHeight = 0, graphicWidth = 0, customSizePx = 0, customSizePt = 0;

    public EditTextPlus(Context context) {
        super(context);
    }

    public EditTextPlus(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        graphicHeight = a.getFloat(R.styleable.EditTextPlus_customGraphicHeight, DEFAULT_GRAPHIC_HEIGHT);
        graphicWidth = a.getFloat(R.styleable.EditTextPlus_customGraphicWidth, DEFAULT_GRAPHIC_WIDTH);
        a.recycle();

        setCustomFont(context, attrs);
        setCustomSize(context, attrs);
        setCustomPadding(context, attrs);
    }

    public EditTextPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        graphicHeight = a.getFloat(R.styleable.EditTextPlus_customGraphicHeight, DEFAULT_GRAPHIC_HEIGHT);
        graphicWidth = a.getFloat(R.styleable.EditTextPlus_customGraphicWidth, DEFAULT_GRAPHIC_WIDTH);
        a.recycle();

        setCustomFont(context, attrs);
        setCustomSize(context, attrs);
        setCustomPadding(context, attrs);
    }

    private void setCustomPadding(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        float cusPadding = a.getFloat(R.styleable.EditTextPlus_customPadding, 0f);
        float cusPaddingLeft = a.getFloat(R.styleable.EditTextPlus_customPaddingLeft, 0f);
        float cusPaddingRight = a.getFloat(R.styleable.EditTextPlus_customPaddingRight, 0f);
        float cusPaddingTop = a.getFloat(R.styleable.EditTextPlus_customPaddingTop, 0f);
        float cusPaddingBottom = a.getFloat(R.styleable.EditTextPlus_customPaddingBottom, 0f);

        int paddingLeft = 0;
        int paddingRight = 0;
        int paddingTop = 0;
        int paddingBottom = 0;

        float ratioW = (float) getContext().getResources().getDisplayMetrics().widthPixels / graphicWidth;
        float ratioH = (float) getContext().getResources().getDisplayMetrics().heightPixels / graphicHeight;

        if(cusPadding != 0f) {
            paddingLeft = (int) (cusPadding * ratioW);
            paddingRight = (int) (cusPadding * ratioW);
            paddingTop = (int) (cusPadding * ratioH);
            paddingBottom = (int) (cusPadding * ratioH);
        }

        if(cusPaddingLeft != 0f) {
            paddingLeft = (int) (cusPaddingLeft * ratioW);
        }

        if(cusPaddingRight != 0f) {
            paddingRight = (int) (cusPaddingRight * ratioW);
        }

        if(cusPaddingTop != 0f) {
            paddingTop = (int) (cusPaddingTop * ratioH);
        }

        if(cusPaddingBottom != 0f) {
            paddingBottom = (int) (cusPaddingBottom * ratioH);
        }

        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        a.recycle();
    }

    private void setCustomSize(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        float customSize = a.getFloat(R.styleable.EditTextPlus_customSize, 0f);
        customSizePx = a.getFloat(R.styleable.EditTextPlus_customSizePx, DEFAULT_CUSTOM_SIZE_PX);
        customSizePt = a.getFloat(R.styleable.EditTextPlus_customSizePt, DEFAULT_CUSTOM_SIZE_PT);

        if(customSize != 0f) {

            float sizePixel = (float) getContext().getResources().getDisplayMetrics().widthPixels * (customSizePx / graphicWidth) / customSizePt;

            setTextSize(TypedValue.COMPLEX_UNIT_PX, customSize * sizePixel);
        }

        a.recycle();
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.EditTextPlus);
        String customFont = a.getString(R.styleable.EditTextPlus_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: " + e.getMessage());
            return false;
        }

        setTypeface(tf, getTypeface().getStyle());

        setIncludeFontPadding(false);

        return true;
    }

}