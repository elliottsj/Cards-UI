package com.afollestad.cardsui;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;

/**
 * @author Aidan Follestad (afollestad)
 */
public interface CardBase {

    public abstract String getTitle();

    public abstract String getContent();

    public abstract boolean isHeader();

    public abstract boolean isClickable();

    public abstract int getPopupMenu();

    public CardHeader.ActionListener getActionCallback();

    public String getActionTitle();

    public abstract CardMenuListener getPopupListener();

    public abstract Drawable getThumbnail();

    public abstract int getLayout();

    public interface CardMenuListener<ItemType extends CardBase> {
        public void onMenuItemClick(ItemType card, MenuItem item);
    }

}
