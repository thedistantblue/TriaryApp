package com.thedistantblue.triaryapp.mainscreen.utils.recycler;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ListItemHolder<E, T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public ListItemHolder(@NonNull T view) {
        super(view.getRoot());
    }

    public abstract void bind(E object);
}