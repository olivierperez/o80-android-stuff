package fr.o80.androidstuff.android;

import android.support.v7.util.DiffUtil;

import java.util.List;

public abstract class GenericDiffCallback<T> extends DiffUtil.Callback {

    protected final List<T> oldList;
    protected final List<T> newList;

    public GenericDiffCallback(List<T> oldContributions, List<T> newContributions) {
        this.oldList = oldContributions;
        this.newList = newContributions;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);

        return isSameItem(oldItem, newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    protected abstract boolean isSameItem(T oldItem, T newItem);
}
