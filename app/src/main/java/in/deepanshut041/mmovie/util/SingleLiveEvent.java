package in.deepanshut041.mmovie.util;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * File Description: A lifecycle-aware observable that sends only new updates after subscription, used for events like
 *  *                   navigation and Snackbar messages.This avoids a common problem with events: on configuration change
 *  *                   (like rotation) an update can be emitted if the observer is active.
 *  *                   This LiveData only calls the observable if there's an explicit call to setValue() or call().
 *  *                   Note that only one observer is going to be notified of changes.
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 25/03/2019
 * Modified: 25/03/2019
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private static final String TAG = "SingleLiveEvent";

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    @Override
    public void observe(LifecycleOwner owner, final Observer<T> observer) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        // Observe the internal MutableLiveData
        super.observe(owner, t -> {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    @Override
    public void setValue(@Nullable T t) {
        mPending.set(true);
        super.setValue(t);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() {
        setValue(null);
    }
}
