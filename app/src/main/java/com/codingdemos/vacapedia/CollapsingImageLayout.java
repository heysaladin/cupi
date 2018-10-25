package com.codingdemos.vacapedia;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.codingdemos.flowers.R;

public class CollapsingImageLayout extends FrameLayout {

    private static final String TAG = "CollapsingImageLayout";

    private WindowInsetsCompat mLastInsets;

    private int mImageLeftExpanded;

    private int mImageTopExpanded;

    private int mTitleLeftExpanded;

    private int mTitleTopExpanded;

    private int mSubtitleLeftExpanded;

    private int mSubtitleTopExpanded;

    private int mImageLeftCollapsed;

    private int mImageTopCollapsed;

    private int mTitleLeftCollapsed;

    private int mTitleTopCollapsed;

    private int mSubtitleLeftCollapsed;

    private int mSubtitleTopCollapsed;

    private OnOffsetChangedListener mOnOffsetChangedListener;

    private int leftWindow = 0;

    public CollapsingImageLayout(Context context) {
        this(context, null);
        Log.d("TAG", ">>>>>>>>>" + "CollapsingImageLayout(Context context)");
    }

    public CollapsingImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.d("TAG", ">>>>>>>>>" + "CollapsingImageLayout(Context context, AttributeSet attrs)");
    }

    public CollapsingImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mImageLeftCollapsed = getResources().getDimensionPixelOffset(R.dimen.image_left_margin_collapsed);
        mImageTopCollapsed = getResources().getDimensionPixelOffset(R.dimen.image_top_margin_collapsed);
        mTitleLeftCollapsed = getResources().getDimensionPixelOffset(R.dimen.title_left_margin_collapsed);
        mTitleTopCollapsed = getResources().getDimensionPixelOffset(R.dimen.title_top_margin_collapsed);
        mSubtitleLeftCollapsed = getResources().getDimensionPixelOffset(R.dimen.subtitle_left_margin_collapsed);
        mSubtitleTopCollapsed = getResources().getDimensionPixelOffset(R.dimen.subtitle_top_margin_collapsed);

        ViewCompat.setOnApplyWindowInsetsListener(this,
                new android.support.v4.view.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                        return setWindowInsets(insets);
                    }
                });
        Log.d("TAG", ">>>>>>>>>" + "CollapsingImageLayout(Context context, AttributeSet attrs, int defStyleAttr)");
    }

    @TargetApi(21)
    public CollapsingImageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d("TAG", ">>>>>>>>>" + "CollapsingImageLayout");
    }

    @Override
    protected void onAttachedToWindow() {
        Log.d("TAG", ">>>>>>>>>" + "onAttachedToWindow");
        super.onAttachedToWindow();

        // Add an OnOffsetChangedListener if possible
        final ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            if (mOnOffsetChangedListener == null) {
                mOnOffsetChangedListener = new OnOffsetChangedListener();
            }
            ((AppBarLayout) parent).addOnOffsetChangedListener(mOnOffsetChangedListener);
        }

        // We're attached, so lets request an inset dispatch
        ViewCompat.requestApplyInsets(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.d("TAG", ">>>>>>>>>" + "onDetachedFromWindow");
        // Remove our OnOffsetChangedListener if possible and it exists
        final ViewParent parent = getParent();
        if (mOnOffsetChangedListener != null && parent instanceof AppBarLayout) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener(mOnOffsetChangedListener);
        }

        super.onDetachedFromWindow();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // Update our child view offset helpers
        for (int i = 0, z = getChildCount(); i < z; i++) {
            final View child = getChildAt(i);

            if (mLastInsets != null && !ViewCompat.getFitsSystemWindows(child)) {
                final int insetTop = mLastInsets.getSystemWindowInsetTop();
                if (child.getTop() < insetTop) {
                    // If the child isn't set to fit system windows but is drawing within the inset
                    // offset it down
                    ViewCompat.offsetTopAndBottom(child, insetTop);
                }
            }

            getViewOffsetHelper(child).onViewLayout();

            switch (child.getId()) {
//                case R.id.avatar:
//                    mImageLeftExpanded = child.getLeft();
//                    mImageTopExpanded = child.getTop();
//                    break;
                case R.id.title:
                    mTitleLeftExpanded = child.getLeft();
                    mTitleTopExpanded = child.getTop();
                    leftWindow = mTitleLeftExpanded;
                    Log.d("TAG", ">>>>>>>>>" + "Left Condition:" + mTitleLeftExpanded);
                    break;
                case R.id.subtitle:
                    mSubtitleLeftExpanded = child.getLeft();
                    mSubtitleTopExpanded = child.getTop();
                    break;
            }
        }
        Log.d("TAG", ">>>>>>>>>" + "onLayout");
    }

    private WindowInsetsCompat setWindowInsets(WindowInsetsCompat insets) {
        if (mLastInsets != insets) {
            mLastInsets = insets;
            requestLayout();
        }
        Log.d("TAG", ">>>>>>>>>" + "setWindowInsets");
        return insets.consumeSystemWindowInsets();
    }

    class OnOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            final int insetTop = mLastInsets != null ? mLastInsets.getSystemWindowInsetTop() : 0;
            final int scrollRange = appBarLayout.getTotalScrollRange();
            float offsetFactor = (float) (-verticalOffset) / (float) scrollRange;
            final int heightDiff = getHeight() - getMinimumHeight();
            Log.d(TAG, "onOffsetChanged(), offsetFactor = " + offsetFactor);


            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                final ViewOffsetHelper offsetHelper = getViewOffsetHelper(child);

                if (child instanceof Toolbar) {
                    if (getHeight() - insetTop + verticalOffset >= child.getHeight()) {
                        offsetHelper.setTopAndBottomOffset(-verticalOffset); // pin
                    }
                }

                if (child.getId() == R.id.background) {
                    int offset = Math.round(-verticalOffset * .5F);
                    offsetHelper.setTopAndBottomOffset(offset); // parallax
                }

//                if (child.getId() == R.id.avatar) {
//
//                    float scaleFactor = 1F - offsetFactor * .5F ;
//                    child.setScaleX(scaleFactor);
//                    child.setScaleY(scaleFactor);
//
//                    int topOffset = (int) ((mImageTopCollapsed - mImageTopExpanded) * offsetFactor) - verticalOffset;
//                    int leftOffset = (int) ((mImageLeftCollapsed - mImageLeftExpanded) * offsetFactor);
//                    child.setPivotX(0);
//                    child.setPivotY(0);
//                    offsetHelper.setTopAndBottomOffset(topOffset);
//                    offsetHelper.setLeftAndRightOffset(leftOffset);
//                }

                if (child.getId() == R.id.title) {

                    int topOffset = (int) ((mTitleTopCollapsed - mTitleTopExpanded) * offsetFactor) - verticalOffset;
                    int leftOffset = (int) ((mTitleLeftCollapsed - mTitleLeftExpanded) * offsetFactor);
                    offsetHelper.setTopAndBottomOffset(topOffset);
                    offsetHelper.setLeftAndRightOffset(leftOffset);
                    Log.d(TAG, "onOffsetChanged(), offsetting title top = " + topOffset + ", left = " + leftOffset);
                    Log.d(TAG, "onOffsetChanged(), offsetting title mTitleLeftCollapsed = " + mTitleLeftCollapsed + ", mTitleLeftExpanded = " + mTitleLeftExpanded);

//                    Log.d("TAG", ">>>>>>>>>" + "topOffset" + topOffset);
//                    Log.d("TAG", ">>>>>>>>>" + "mTitleTopCollapsed" + mTitleTopCollapsed);
                    if (topOffset==28&&leftOffset==-(leftWindow-mTitleLeftCollapsed)) {
                        Log.d("TAG", ">>>>>>>>>" + "SAMPAIIIIIIIIII!!!!!");


//                        ScrollView scr = (ScrollView) findViewById(R.id.scr);
//                        scr.setOnTouchListener(new ScrollView.OnTouchListener() {
//                            @Override
//                            public boolean onTouch(View v, MotionEvent event) {
//                                int action = event.getAction();
//                                switch (action) {
//                                    case MotionEvent.ACTION_DOWN:
//                                        // Disallow ScrollView to intercept touch events.
//                                        v.getParent().requestDisallowInterceptTouchEvent(true);
//                                        break;
//
//                                    case MotionEvent.ACTION_UP:
//                                        // Allow ScrollView to intercept touch events.
//                                        v.getParent().requestDisallowInterceptTouchEvent(false);
//                                        break;
//                                }
//
//                                // Handle ScrollView touch events.
//                                v.onTouchEvent(event);
//                                return true;
//                            }
//                        });

                    }
                    if (topOffset==0&&leftOffset==0) {
                        Log.d("TAG", ">>>>>>>>>" + "PULANGG!!!!!");
                    }

                }

                if (child.getId() == R.id.subtitle) {

                    int topOffset = (int) ((mSubtitleTopCollapsed - mSubtitleTopExpanded) * offsetFactor) - verticalOffset;
                    int leftOffset = (int) ((mSubtitleLeftCollapsed - mSubtitleLeftExpanded) * offsetFactor);
                    offsetHelper.setTopAndBottomOffset(topOffset);
                    offsetHelper.setLeftAndRightOffset(leftOffset);
                }
            }
            Log.d("TAG", ">>>>>>>>>" + "OnOffsetChangedListener");
        }
    }

    private static ViewOffsetHelper getViewOffsetHelper(View view) {
        ViewOffsetHelper offsetHelper = (ViewOffsetHelper) view.getTag(R.id.view_offset_helper);
        if (offsetHelper == null) {
            offsetHelper = new ViewOffsetHelper(view);
            view.setTag(R.id.view_offset_helper, offsetHelper);
        }
        Log.d("TAG", ">>>>>>>>>" + "getViewOffsetHelper");
        return offsetHelper;
    }

    static class ViewOffsetHelper {

        private final View mView;

        private int mLayoutTop;
        private int mLayoutLeft;
        private int mOffsetTop;
        private int mOffsetLeft;

        public ViewOffsetHelper(View view) {
            mView = view;
        }

        public void onViewLayout() {
            // Now grab the intended top
            mLayoutTop = mView.getTop();
            mLayoutLeft = mView.getLeft();

            // And offset it as needed
            updateOffsets();
        }

        private void updateOffsets() {
            ViewCompat.offsetTopAndBottom(mView, mOffsetTop - (mView.getTop() - mLayoutTop));
            ViewCompat.offsetLeftAndRight(mView, mOffsetLeft - (mView.getLeft() - mLayoutLeft));

            // Manually invalidate the view and parent to make sure we get drawn pre-M
            if (Build.VERSION.SDK_INT < 23) {
                tickleInvalidationFlag(mView);
                final ViewParent vp = mView.getParent();
                if (vp instanceof View) {
                    tickleInvalidationFlag((View) vp);
                }
            }
        }

        private static void tickleInvalidationFlag(View view) {
            final float y = ViewCompat.getTranslationY(view);
            ViewCompat.setTranslationY(view, y + 1);
            ViewCompat.setTranslationY(view, y);
        }

        /**
         * Set the top and bottom offset for this {@link ViewOffsetHelper}'s view.
         *
         * @param offset the offset in px.
         * @return true if the offset has changed
         */
        public boolean setTopAndBottomOffset(int offset) {
            if (mOffsetTop != offset) {
                mOffsetTop = offset;
                updateOffsets();
                return true;
            }
            return false;
        }

        /**
         * Set the left and right offset for this {@link ViewOffsetHelper}'s view.
         *
         * @param offset the offset in px.
         * @return true if the offset has changed
         */
        public boolean setLeftAndRightOffset(int offset) {
            if (mOffsetLeft != offset) {
                mOffsetLeft = offset;
                updateOffsets();
                return true;
            }
            return false;
        }

        public int getTopAndBottomOffset() {
            return mOffsetTop;
        }

        public int getLeftAndRightOffset() {
            return mOffsetLeft;
        }
    }}
