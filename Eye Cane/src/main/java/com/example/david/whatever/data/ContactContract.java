package com.example.david.whatever.data;

import android.provider.BaseColumns;

/**
 * Created by David on 8/31/2017.
 */

public class ContactContract {
    private ContactContract(){}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class ContactEntry implements BaseColumns{
        /** Name of database table for contacts */
        public final static String TABLE_NAME = "contacts";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CONTACT_NUMBER ="number";
    }

}
