package com.lottery.library.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lottery.library.utils.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：czg
 * @class ：BaseAdapter.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    private final List<T> data = new ArrayList<>();
    protected Context context;
    private Map<Integer, Class<? extends BaseViewHolder<T>>> viewHolders = new HashMap<>();
    private Map<Integer, Integer> viewHoldersLayout = new HashMap<>();
    private static final int DEFAULT_TYPE = 0;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    public BaseAdapter(Context context, Class<? extends BaseViewHolder<T>> defultViewHolder, int layoutId) {
        this.context = context;
        viewHolders.put(DEFAULT_TYPE, defultViewHolder);
        viewHoldersLayout.put(DEFAULT_TYPE, layoutId);
    }

    public void registerViewHolders(int type, Class<? extends BaseViewHolder<T>> clazz, int layoutId) {
        if (type == DEFAULT_TYPE) {
            throw new RuntimeException("could not register type =" + DEFAULT_TYPE);
        }
        if (viewHolders.get(type) == null) {
            viewHolders.put(type, clazz);
        } else {
            LogUtils.e(clazz.getSimpleName() + " multiple registration");
        }
        if (viewHoldersLayout.get(type) == null) {
            viewHoldersLayout.put(type, layoutId);
        } else {
            LogUtils.e(clazz.getSimpleName() + " multiple registration");
        }
    }


    public void setData(List<T> data) {
        this.data.clear();
        this.addData(data);
    }

    public void addData(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return data.get(position);
    }

    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {

        try {

            Class<? extends BaseViewHolder<T>> clazz = viewHolders.get(viewType);
            int layoutId = viewHoldersLayout.get(viewType);

            Constructor<? extends BaseViewHolder<T>> constructor = clazz.getConstructor(ViewGroup.class, int.class);

            final BaseViewHolder<T> baseViewHolder = constructor.newInstance(parent, layoutId);


            baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, baseViewHolder.getAdapterPosition());
                    }

                }
            });
            baseViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemLongClickListener != null) {
                        return mOnItemLongClickListener.onItemLongClick(v, baseViewHolder.getAdapterPosition());
                    }
                    return false;
                }
            });

            return baseViewHolder;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    ;

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        holder.setData(getItem(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
