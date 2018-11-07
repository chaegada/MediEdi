package com.happy.trans;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends ImageView {

    public SquareImageView(Context context) {
        super(context);
    }
    public SquareImageView(Context context, AttributeSet attrset) {
        super(context, attrset);
    }
    public SquareImageView(Context context, AttributeSet attrset, int defStyle) {
        super(context, attrset, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = width;
        setMeasuredDimension(width, height);
    }
}