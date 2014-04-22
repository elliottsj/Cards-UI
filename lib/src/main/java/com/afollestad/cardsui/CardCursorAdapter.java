package com.afollestad.cardsui;

import android.content.Context;
import android.database.Cursor;

/**
 * @author Spencer Elliott (elliottsj)
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class CardCursorAdapter<ItemType extends CardBase> extends CardAdapter<ItemType> {

    private Cursor mCursor;

    /**
     * Initializes a new CardCursorAdapter instance.
     *
     * @param context The context used to inflate layouts and retrieve resources.
     */
    public CardCursorAdapter(Context context) {
        super(context);
    }

    /**
     * Initializes a new CardCursorAdapter instance.
     *
     * @param context       The context used to inflate layouts and retrieve resources.
     * @param cardLayoutRes Sets a custom layout to be used for all cards (not including headers) in the adapter.
     *                      This <b>does not</b> override layouts set to individual cards.
     */
    public CardCursorAdapter(Context context, int cardLayoutRes) {
        super(context, cardLayoutRes);
    }

    /**
     * Initializes a new CardCursorAdapter instance.
     *
     * @param context                The context used to inflate layouts and retrieve resources.
     * @param cardLayoutRes          Sets a custom layout to be used for all cards (not including headers) in the adapter.
     *                               This <b>does not</b> override layouts set to individual cards.
     * @param cardLayoutNoContentRes Sets a custom layout to be used for all cards (not including headers) in the
     *                               adapter with null content. This <b>does not</b> override layouts set to individual cards.
     */
    public CardCursorAdapter(Context context, int cardLayoutRes, int cardLayoutNoContentRes) {
        super(context, cardLayoutRes, cardLayoutNoContentRes);
    }

    /**
     * Change the underlying cursor to a new cursor. If there is an existing cursor it will be
     * closed.
     *
     * @param cursor The new cursor to be used
     */
    public void changeCursor(Cursor cursor) {
        Cursor old = swapCursor(cursor);
        if (old != null) {
            old.close();
        }
    }

    /**
     * Swap in a new Cursor, returning the old Cursor.  Unlike
     * {@link #changeCursor(Cursor)}, the returned old Cursor is <em>not</em>
     * closed.
     *
     * @param newCursor The new cursor to be used.
     * @return Returns the previously set Cursor, or null if there was not one.
     * If the given new Cursor is the same instance is the previously set
     * Cursor, null is also returned.
     */
    @SuppressWarnings("ConstantConditions")
    public Cursor swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return null;
        }
        clear();
        Cursor oldCursor = mCursor;
        mCursor = newCursor;
        if (newCursor != null) {
            newCursor.moveToFirst();
            populateArray(newCursor);
            // notify the observers about the new cursor
            notifyDataSetChanged();
        } else {
            // notify the observers about the lack of a data set
            notifyDataSetInvalidated();
        }
        return oldCursor;
    }

    /**
     * Populate the adapter by calling {@code add()} for each item in the cursor
     *
     * @param cursor cursor to add items from
     */
    public abstract void populateArray(Cursor cursor);

}