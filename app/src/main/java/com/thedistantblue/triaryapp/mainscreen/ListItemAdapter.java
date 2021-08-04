package com.thedistantblue.triaryapp.mainscreen;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.thedistantblue.triaryapp.database.room.dao.base.BaseDao;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ListItemAdapter<E, H extends ListItemHolder<E, ? extends ViewDataBinding>, D extends BaseDao<E>>
        extends RecyclerView.Adapter<H>
        implements ItemTouchHelperAdapter {

    private final D repository;
    private final AutoDisposableFragment fragment;
    private List<E> objectsList;

    @SuppressLint("NotifyDataSetChanged")
    public void setObjectsList(List<E> objectsList) {
        this.objectsList = objectsList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull H holder, int position) {
        E object = objectsList.get(position);
        holder.bind(object);
    }

    @Override
    public void onItemDismiss(int position) {
        fragment.withAutoDispose(
                repository.delete(objectsList.get(position))
                          .subscribe(() -> {
                              objectsList.remove(position);
                              notifyItemRemoved(position);
                          }));
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(objectsList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(objectsList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onRefresh(int position) {
        notifyItemChanged(position);
    }

    @Override
    public Context getContext() {
        return fragment.requireActivity();
    }

    @Override
    public int getItemCount() {
        return objectsList.size();
    }
}