package in.deepanshut041.mmovie.managers;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

import in.deepanshut041.mmovie.common.Constants;
import in.deepanshut041.mmovie.util.SharedPrefUtil;

import static in.deepanshut041.mmovie.common.Constants.LANGUAGE_ENGLISH;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
public class LocaleManager {
    public static Context setLocale(Context context) {
        return updateResources(context, getLanguage(context));
    }

    /**
     * This method is used to update the device resources incase of language change
     * @param context - Context
     * @param language - User selected language
     * @return updated context
     */
    public static Context setNewLocale(Context context, String language) {
        persistLanguage(context, language);
        return updateResources(context, language);
    }

    /**
     * This method return the current configured language
     * @param context - context
     * @return the current language code
     */
    public static String getLanguage(Context context) {
        return SharedPrefUtil.getStringPreference(context, Constants.LANGUAGE_KEY, LANGUAGE_ENGLISH);
    }

    /**
     * Method sets the current language in SharedPreference
     * @param context - Context
     * @param language - User selected language
     */
    private static void persistLanguage(Context context, String language) {
        SharedPrefUtil.setStringPreference(context, Constants.LANGUAGE_KEY, language);
    }

    /**
     * This method will update the android resources on localization change
     * @param context - Application context
     * @param language  - Selected Language
     * @return the updated context
     */
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.setLocale(locale);
        context = context.createConfigurationContext(config);
        return context;
    }


    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }
}