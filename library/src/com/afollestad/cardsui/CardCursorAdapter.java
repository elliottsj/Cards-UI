package com.afollestad.cardsui;

import android.content.Context;
import android.database.Cursor;
import com.afollestad.silk.adapters.SilkCursorAdapter;
import com.afollestad.silk.caching.SilkCursorItem;

/**
 * @author Aidan Follestad (afollestad)
 */
public class CardCursorAdapter<ItemType extends CardBase<ItemType> & SilkCursorItem<ItemType>> extends CardAdapter<ItemType> {

    private final Class<? extends SilkCursorItem> mClass;

    public CardCursorAdapter(Context context, Class<? extends SilkCursorItem> cls) {
        super(context);
        mClass = cls;
    }

    public CardCursorAdapter(Context context, Class<? extends SilkCursorItem> cls, int cardLayoutRes) {
        super(context, cardLayoutRes);
        mClass = cls;
    }

    public CardCursorAdapter(Context context, Class<? extends SilkCursorItem> cls, int cardLayoutRes, int cardLayoutNoContentRes) {
        super(context, cardLayoutRes, cardLayoutNoContentRes);
        mClass = cls;
    }

    public final void changeCursor(Cursor cursor) {
        clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ItemType item = (ItemType) SilkCursorAdapter.performConvert(cursor, mClass);
                if (item != null) add(item);
            }
        }
    }
}