package in.deepanshut041.mmovie.view.base;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * File Description: Base Recyclerview Adapter
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 25/03/2019
 * Modified: 25/03/2019
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T>{

    public abstract void setData(List<D> data);
}

