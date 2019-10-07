package com.entry.asqliteteaching;

import android.app.Application;

/**
 * 整个程序定制的Application对象。
 */
public class OrderApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // 创建全局可访问的单例
        if (!OrderContext.isInitialized()) {
            OrderContext.init(getApplicationContext());
        }
    }

}
