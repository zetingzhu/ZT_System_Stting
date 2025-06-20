package com.example.zt_navigation2

import android.app.Activity
import android.app.Application
import android.os.Bundle


/**
 * @author: zeting
 * @date: 2025/6/19
 *
 */
class MyApplication : Application() {
    // 伴生对象，用于存放静态成员（属性和方法）
    companion object {
        // 静态属性，通常用于存储Application实例，方便全局访问
        private var instance: MyApplication? = null

        // 静态方法，用于获取Application实例
        fun getInstance(): MyApplication {
            return instance as MyApplication
        }
    }

    val sActivityList: MutableList<Activity> = mutableListOf()

    override fun onCreate() {
        super.onCreate()
        instance = this // 在onCreate中初始化静态实例

        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                sActivityList.add(activity)
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                sActivityList.remove(activity)
            }
        })
    }



}