package in.deepanshut041.mmovie.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.deepanshut041.mmovie.data.remote.Resource;
import in.deepanshut041.mmovie.view.base.BaseAdapter;

/**
 * File Description: xx
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: xx/xx/20xx
 * Modified: xx/xx/20xx
 */
final class ListBindingAdapter {

    private ListBindingAdapter(){
        // Private Constructor to hide the implicit one
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }

}