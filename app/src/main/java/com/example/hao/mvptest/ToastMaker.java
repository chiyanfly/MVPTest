package com.example.hao.mvptest;


import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

/**
 * <p>
 * Created by ZENG Yuhao on 16/7/31. <br>
 * Contact: enzo.zyh@gmail.com
 * </p>
 */
public class ToastMaker {
    public static final  int BOTTOM        = 0x00;
    public static final  int CENTER        = 0x01;
    public static final  int SHORT         = 0x00;
    public static final  int LONG          = 0x10;
    private static final int MASK_GRAVITY  = 0x0F;
    private static final int MASK_DURATION = 0xF0;

    private Context mContext;

    public ToastMaker(Context context) {
        mContext = context;
    }

    public static ToastMaker from(Context context) {
        return new ToastMaker(context);
    }

    public Toast show(String content) {
        return showToast(content, BOTTOM | SHORT);
    }

    public Toast show(@StringRes int resId) {
        String content = mContext.getString(resId);
        return showToast(content, BOTTOM | SHORT);
    }

    /**
     * Easy way to show a toast with specification you want.
     *
     * @param content content of toast
     * @param config  0 if it's default. other config settings can be found at
     *                {@link #BOTTOM}, {@link #CENTER}, {@link #SHORT}, {@link #LONG}
     * @return Toast created.
     */
    public Toast show(String content, int config) {
        return showToast(content, config);
    }

    /**
     * @param resId  string resource id
     * @param config 0 if it's default. other config settings can be found at
     *               {@link #BOTTOM}, {@link #CENTER}, {@link #SHORT}, {@link #LONG}
     * @return Toast created.
     */
    public Toast show(@StringRes int resId, int config) {
        String content = mContext.getString(resId);
        return showToast(content, config);
    }

    private Toast showToast(String content, int config) {
        int gravityConfig = config & MASK_GRAVITY;
        int durationConfig = config & MASK_DURATION;
        boolean isBottom = (gravityConfig == 0);
        boolean isShort = (durationConfig == 0);

        Toast toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        if (!isBottom) {
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }
}
