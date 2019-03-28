package in.deepanshut041.mmovie.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import in.deepanshut041.mmovie.AcApp;
import in.deepanshut041.mmovie.di.builder.ActivityBuilderModule;
import in.deepanshut041.mmovie.di.module.AppModule;

/**
 * File Description: The main application component which initializes all the dependent modules
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 03/25/2019
 * Modified: 03/25/2019
 */

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(AcApp acApp);
}