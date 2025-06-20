package com.example.zt_navigation2;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zeting
 * @date: 2025/6/20
 */
public class UUUUU {
    public static List<Activity> getActivitiesByReflection() {
        List<Activity> list = new ArrayList<>();
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = activityThreadClass.getMethod("currentActivityThread");
            Object activityThread = currentActivityThread.invoke(null);

            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);

            // 获取mActivities（类型为ArrayMap<IBinder, ActivityClientRecord>）
            Object activities = activitiesField.get(activityThread);
            if (activities instanceof Map) {
                for (Object record : ((Map<?, ?>) activities).values()) {
                    Class<?> recordClass = record.getClass();
                    Field activityField = recordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(record);
                    list.add(activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
