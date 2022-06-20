package com.obelieve.zlog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 *
 * 需要添加授权 <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
 * 1.#register(Activity)
 * 2.#unregister(Activity)
 * 3.#switchLogShowOrHide() //点击显示或隐藏
 */
public class SimpleLogWindowManager {

    @SuppressLint("StaticFieldLeak")
    private static final SimpleLogWindowManager mManager = new SimpleLogWindowManager();

    private Context mContext;

    private final LogUtil.LogBroadcastReceiver mReceiver = new LogUtil.LogBroadcastReceiver(new LogUtil.LogBroadcastReceiver.LogListener() {
        @Override
        public void getLogMessage(int priority, String msg) {
            LogUtil.LogWindow.getInstance(mContext).printLogMessage(priority,msg);
        }
    });

    private LogUtil.LogWindow mLogWindow;
    private boolean mShowLog = false;

    private SimpleLogWindowManager(){
    }

    public static SimpleLogWindowManager getInstance(){
        return mManager;
    }

    private static void checkContext() {
        if(mManager.mContext == null){
            throw new IllegalStateException("init(Context)未初始化");
        }
    }

    public void register(Activity activity){
        if(mContext==null){
            mContext = activity.getApplicationContext();
            LogUtil.builder().setBroadcast(mContext,true).setDebug(true);
            mLogWindow = LogUtil.LogWindow.getInstance(mContext);
        }
        activity.registerReceiver(mReceiver, mReceiver.getIntentFilter());
    }

    public void unregister(Activity activity){
        activity.unregisterReceiver(mReceiver);
    }

    /**
     * Log显示或隐藏
     */
    public void switchLogShowOrHide(){
        checkContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(mContext)) {
                //若未授权则请求权限
                getOverlayPermission();
            }else{
                showLog();
            }
        }else{
            showLog();
        }

    }

    private void getOverlayPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + mContext.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }

    private void showLog() {
        checkContext();
        if(!mShowLog){
            mLogWindow.showLogWindow();
        }else{
            mLogWindow.removeLogWindow();
        }
        mShowLog = !mShowLog;
    }
}
