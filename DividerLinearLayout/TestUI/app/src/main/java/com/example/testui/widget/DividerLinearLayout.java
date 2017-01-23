package com.example.testui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.testui.R;

/**
 * Created by chenjiawei on 16/12/8.
 */

public class DividerLinearLayout extends LinearLayout {

    private int mDividerWidth = 0;

    private boolean mHeaderDividerEnable = false;

    private boolean mFooterDividerEnable = false;

    private int mDividerColor;

    private int mHeaderDividerPaddingEnd;

    private int mHeaderDividerPaddingStart;

    private int mFooterDividerPaddingEnd;

    private int mFooterDividerPaddingStart;

    private GradientDrawable mDividerDrawable;


    public DividerLinearLayout(Context context) {
        super(context);
        initDividerDrawable();
    }

    public DividerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        resolveAttributeSet(attrs);
        adjustPadding();
        initDividerDrawable();
    }

    public DividerLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        resolveAttributeSet(attrs);
        adjustPadding();
        initDividerDrawable();
    }


    private void resolveAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.borderLinearLayout);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.borderLinearLayout_dividerColor:
                    mDividerColor = typedArray.getColor(index, getResources().getColor(R.color.defaultDividerColor));
                    break;
                case R.styleable.borderLinearLayout_dividerWidth:
                    mDividerWidth = (int) typedArray.getDimension(index, 0);
                    break;
                case R.styleable.borderLinearLayout_footerDividerEnable:
                    mHeaderDividerEnable = typedArray.getBoolean(index, false);
                    break;
                case R.styleable.borderLinearLayout_headerDividerEnable:
                    mFooterDividerEnable = typedArray.getBoolean(index, false);
                    break;
                case R.styleable.borderLinearLayout_headerDividerPaddingStart:
                    mHeaderDividerPaddingStart = (int) typedArray.getDimension(index, 0);
                    break;
                case R.styleable.borderLinearLayout_headerDividerPaddingEnd:
                    mHeaderDividerPaddingEnd = (int) typedArray.getDimension(index, 0);
                    break;
                case R.styleable.borderLinearLayout_footerDividerPaddingStart:
                    mFooterDividerPaddingStart = (int) typedArray.getDimension(index, 0);
                    break;
                case R.styleable.borderLinearLayout_footerDividerPaddingEnd:
                    mFooterDividerPaddingEnd = (int) typedArray.getDimension(index, 0);
                    break;
                default:
                    break;

            }
        }
        typedArray.recycle();
    }

    void initDividerDrawable() {
        setShowDividers(SHOW_DIVIDER_MIDDLE);
        setDividerDrawableAttributeSet();
    }

    void setDividerDrawableAttributeSet() {
        mDividerDrawable = new GradientDrawable();
        mDividerDrawable.setColor(mDividerColor);
        mDividerDrawable.setShape(GradientDrawable.RECTANGLE);
        if (getOrientation() == VERTICAL) {
            mDividerDrawable.setSize(getMeasuredWidth(), mDividerWidth);
        } else {
            mDividerDrawable.setSize(mDividerWidth, getMeasuredHeight());
        }
        setDividerDrawable(mDividerDrawable);
    }

    void adjustPadding() {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mHeaderDividerEnable) {
            drawHeaderDivider(canvas);
        }
        if (mFooterDividerEnable) {
            drawFooterDivider(canvas);
        }
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        if (mHeaderDividerEnable) {
            if (getOrientation() == VERTICAL) {
                top = mDividerWidth + top;
            } else {
                left = mDividerWidth + left;
            }
        }
        if (mFooterDividerEnable) {
            if (getOrientation() == VERTICAL) {
                bottom = mDividerWidth + bottom;
            } else {
                right = mDividerWidth + right;
            }
        }
        super.setPadding(left, top, right, bottom);
    }

    /**
     * Set the visible state of header divider
     *
     * @param visible True if the header divider is Shown, false otherwise
     */
    public void showHeaderDivider(boolean visible) {
        if (mHeaderDividerEnable != visible) {
            resetPadding();
            this.mHeaderDividerEnable = visible;
            adjustPadding();
            postInvalidate();
        }
    }

    /**
     * Set the visible state of footer divider
     *
     * @param visible True if the footer divider is Shown, false otherwise
     */
    public void showFooterDivider(boolean visible) {
        if (mFooterDividerEnable != visible) {
            resetPadding();
            this.mFooterDividerEnable = visible;
            adjustPadding();
            postInvalidate();
        }
    }

    void resetPadding() {
        if (mFooterDividerEnable && mDividerWidth > 0) {
            if (getOrientation() == VERTICAL) {
                super.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() - mDividerWidth);
            } else {
                super.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight() - mDividerWidth, getPaddingBottom());
            }
        }
        if (mHeaderDividerEnable && mDividerWidth > 0) {
            if (getOrientation() == VERTICAL) {
                super.setPadding(getPaddingLeft(), getPaddingTop() - mDividerWidth, getPaddingRight(), getPaddingBottom());
            } else {
                super.setPadding(getPaddingLeft() - mDividerWidth, getPaddingTop(), getPaddingRight(), getPaddingBottom());
            }
        }
    }


    /**
     * Set the width value of divider
     *
     * @param width the divider width in px
     */
    public void setDividerWidth(int width) {
        if (mDividerWidth != width) {
            resetPadding();
            this.mDividerWidth = width;
            adjustPadding();
            setDividerDrawableAttributeSet();
        }
    }

    /**
     * Set the color value of divider
     *
     * @param color the color of divider
     */
    public void setDividerColor(int color) {
        if (mDividerColor != color) {
            this.mDividerColor = color;
            setDividerDrawableAttributeSet();
            postInvalidate();
        }
    }

    /**
     * Set the padding of header divider
     *
     * @param start the start padding in pixels
     * @param end   the end padding in pixels
     */
    public void setHeaderDividerPadding(int start, int end) {
        if (mHeaderDividerPaddingStart != start || mHeaderDividerPaddingEnd != end) {
            this.mHeaderDividerPaddingStart = start;
            this.mHeaderDividerPaddingEnd = end;
            postInvalidate();
        }
    }

    /**
     * Set the padding of footer divider
     *
     * @param start the start padding in pixels
     * @param end   the end padding in pixels
     */
    public void setFooterDividerPadding(int start, int end) {
        if (mFooterDividerPaddingStart != start || mFooterDividerPaddingEnd != end) {
            this.mFooterDividerPaddingStart = start;
            this.mFooterDividerPaddingEnd = end;
            postInvalidate();
        }
    }

    private void drawHeaderDivider(Canvas canvas) {
        View child = findFirstVisibleView();
        if (child != null) {
            if (getOrientation() == VERTICAL) {
                drawHeaderDividerVertical(child, canvas);
            } else {
                drawHeaderDividerHorizontal(child, canvas);
            }
        }
    }

    private void drawHeaderDividerVertical(View child, Canvas canvas) {
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int top = child.getTop() - lp.topMargin - mDividerWidth;
        mDividerDrawable.setBounds(mHeaderDividerPaddingStart, top,
                getMeasuredWidth() - mHeaderDividerPaddingEnd, top + mDividerWidth);
        mDividerDrawable.draw(canvas);
    }

    private void drawHeaderDividerHorizontal(View child, Canvas canvas) {
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int left = child.getLeft() - lp.leftMargin - mDividerWidth;
        mDividerDrawable.setBounds(left, mHeaderDividerPaddingStart, left + mDividerWidth, getMeasuredHeight() - mHeaderDividerPaddingEnd);
        mDividerDrawable.draw(canvas);
    }

    private void drawFooterDivider(Canvas canvas) {
        View child = findLastVisibleView();
        if (child != null) {
            if (getOrientation() == VERTICAL) {
                drawFooterDividerVertical(child, canvas);
            } else {
                drawFooterDividerHorizontal(child, canvas);
            }
        }
    }

    private void drawFooterDividerVertical(View child, Canvas canvas) {
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int bottom = child.getBottom() + lp.bottomMargin + mDividerWidth;
        mDividerDrawable.setBounds(mFooterDividerPaddingStart, bottom - mDividerWidth,
                getMeasuredWidth() - mFooterDividerPaddingEnd, bottom);
        mDividerDrawable.draw(canvas);
    }

    private void drawFooterDividerHorizontal(View child, Canvas canvas) {
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int right = child.getRight() + lp.rightMargin + mDividerWidth;
        mDividerDrawable.setBounds(right - mDividerWidth, mFooterDividerPaddingStart, right, getMeasuredHeight() - mFooterDividerPaddingEnd);
        mDividerDrawable.draw(canvas);
    }

    View findFirstVisibleView() {
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i) != null && getChildAt(i).getVisibility() != GONE) {
                    return getChildAt(i);
                }
            }
        }
        return null;
    }

    View findLastVisibleView() {
        if (getChildCount() > 0) {
            for (int i = getChildCount() - 1; i >= 0; i--) {
                if (getChildAt(i) != null && getChildAt(i).getVisibility() != GONE) {
                    return getChildAt(i);
                }
            }
        }
        return null;
    }
}
