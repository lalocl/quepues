package com.wordpress.appsandroidsite.quepues.multiselector;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ViewHolder with a callback for when it is rebound.
 *
 * This lives in {@link android.support.v7.widget} so that it can override
 * {@link #setFlags(int, int)}, {@link #offsetPosition(int, boolean)}, and
 * {@link #addFlags(int)}, all of which are package private. This is currently
 * the only way to automatically detect when a ViewHolder has been rebound
 * to a new item.
 *
 * If you intend to subclass for the purpose of interfacing with
 * a {@link com.bignerdranch.android.multiselector.MultiSelector},
 * use {@link com.bignerdranch.android.multiselector.MultiSelectorBindingHolder}
 * instead.
 */
public abstract class RebindReportingHolder extends RecyclerView.ViewHolder {

    public RebindReportingHolder(View itemView) {
        super(itemView);
    }

    /**
     * Called when this instance is rebound to another item in the RecyclerView.
     */
    protected abstract void onRebind();



}
