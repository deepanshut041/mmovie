package in.deepanshut041.mmovie;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import in.deepanshut041.mmovie.di.component.DaggerAppComponent;

/**
 * File Description
 * Entry point into app
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 03/25/2019
 * Modified: 03/25/2019
 */
public class AcApp extends Application implements HasActivityInjector {

    // App Instance to context
    private static AcApp appInstance;

    // Getter and setter for AppInstance
    public static AcApp getAppContext() {
        return appInstance;
    }

    private static synchronized void setInstance(AcApp app) {
        appInstance = app;
    }


    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        Fresco.initialize(this);
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
