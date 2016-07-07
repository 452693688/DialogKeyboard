package app.gm.com.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by Administrator on 2016/5/25.
 */
public class KeyboardEvent {
    private static KeyboardEvent keyboardVisibilityEvent;
    private KeyboardListener listener;
    private Activity activity;
    private boolean useAnimation;

    public KeyboardEvent(Activity activity) {
        this.activity = activity;
        activityListener();
    }

    public void setEventListener(KeyboardListener keyListener) {
        listener = keyListener;
    }

    public void setAnimation(boolean useAnimation) {
        this.useAnimation = useAnimation;
    }

    //获取根view
    private static View getActivityRoot(Activity activity) {
        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    private void activityListener() {
        View activityRootView = getActivityRoot(activity);
        activityRootView.getViewTreeObserver().
                addOnGlobalLayoutListener(new LayoutListener(activityRootView));
    }


    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private View activityRootView;
        private Rect measureContentView = new Rect();
        //状态栏高度
        private int status;
        //键盘高度
        private int keyboardHeight;
        private int tempkeyboardHeight;
        //屏幕高度
        private int screenHeight;
        //测量时的最大高度
        private int maxContentHeight;
        //键盘状态
        private boolean keyboardState;

        public LayoutListener(View activityRootView) {
            this.activityRootView = activityRootView;
            DisplayMetrics dm = activity.getResources().getDisplayMetrics();
            status = getStatusBarHeight(activityRootView);
            screenHeight = dm.heightPixels;
        }

        @Override
        public void onGlobalLayout() {
            //这里得到的是除了系统自带显示区域之外的所有区域
            activityRootView.getWindowVisibleDisplayFrame(measureContentView);
            //测量高度
            int measureContentHeight = measureContentView.height();
            if (measureContentHeight > maxContentHeight) {
                maxContentHeight = measureContentHeight;
            }
            //系统view高度
            int height = activityRootView.getHeight() - measureContentHeight;
            //键盘高度
            keyboardHeight = height;
            if (screenHeight == maxContentHeight) {
                keyboardHeight = height - status;
            }
            String msg = "状态栏高度:" + status + " 屏幕高度:" + screenHeight +
                    " measureContentView高度:" + measureContentHeight
                    + " 系统view高度:" + height + " 键盘高度:" + keyboardHeight;
            Log.e("onGlobalLayout", msg);
            if (!keyboardState && keyboardHeight == 0) {
                return;
            }
            keyboardState = keyboardHeight > 0 ? true : false;
            if (listener == null) {
                return;
            }
            if (useAnimation) {
               int temp=tempkeyboardHeight>0?tempkeyboardHeight:keyboardHeight;
                animator(tempkeyboardHeight,keyboardHeight,temp);
            } else {
                listener.onVisibilityChanged(keyboardState, keyboardHeight, status, msg);
            }
            tempkeyboardHeight=keyboardHeight;
        }

        //获取状态栏高度
        private int getStatusBarHeight(View activityRoot) {
            int result = 0;
            int resourceId = activityRoot.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = activityRoot.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        }
    }


    private ValueAnimator valueAnimatorSmall;

    private void animator(float start, float end, final int moveLength) {
        if (valueAnimatorSmall == null) {
            valueAnimatorSmall = ValueAnimator.ofInt();
        }
        valueAnimatorSmall.cancel();

        valueAnimatorSmall.setFloatValues(start, end);
        valueAnimatorSmall.setDuration(200);
        valueAnimatorSmall.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float move = (Float) animation.getAnimatedValue();
                listener.onMove(move, moveLength);
            }
        });
        valueAnimatorSmall.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimatorSmall.start();
    }


}
