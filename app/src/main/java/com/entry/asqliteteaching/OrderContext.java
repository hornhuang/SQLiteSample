package com.entry.asqliteteaching;

import android.content.Context;

/**
 * 全局可用的context对象  单例模式:http://www.jianshu.com/p/8b59089a12f6
 */
public class OrderContext {

    private static OrderContext instance;// 例子 用于记录本身

    private Context applicationContext;// 存储 application

    public static OrderContext getInstance() {
        if (instance == null){
            throw new RuntimeException(OrderContext.class.getSimpleName() + "has not been initialized!");
        }

        return instance;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public OrderContext(Context applicationContext) {// 构造方法赋值
        this.applicationContext = applicationContext;
    }

    /**
     * 全局信息 只能调用一次
     */
    public static void init(Context applicationContext) {// 初始化，在 application 中调用
        if (instance != null) {
            throw new RuntimeException(OrderContext.class.getSimpleName() + " can not be initialized multiple times!");
        }
        instance = new OrderContext(applicationContext);
    }

    public static boolean isInitialized() {// 判断是否成功初始化
        return (instance != null);
    }
}
