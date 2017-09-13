package com.example.david.whatever;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.david.whatever.data.ContactContract;
import com.example.david.whatever.data.ContactContract.ContactEntry;
import com.example.david.whatever.data.ContactDbHelper;
public class CallsSettingsActivity extends AppCompatActivity {
    private static String contact1;
    private static String contact2;
    private static String contact3;
    private EditText editTextContact1;
    private EditText editTextContact2;
    private EditText editTextContact3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls_settings);
        editTextContact1 = (EditText) findViewById(R.id.editTextContact1);
        editTextContact2 = (EditText) findViewById(R.id.editTextContact2);
        editTextContact3 = (EditText) findViewById(R.id.editTextContact3);
        //   editTextContact1.setFocusable(false);


        Button saveButton1 = (Button) findViewById(R.id.saveButton1);
        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact1 = editTextContact1.getText().toString();

                hideKeyboard(editTextContact1);
                editTextContact1.clearFocus();

                insertInDataBase(0);


            }
        });
        Button saveButton2 = (Button) findViewById(R.id.saveButton2);
        saveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact2 = editTextContact2.getText().toString();

                hideKeyboard(editTextContact2);
                editTextContact2.clearFocus();

                insertInDataBase(1);


            }
        });


        Button saveButton3 = (Button) findViewById(R.id.saveButton3);
        saveButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact3 = editTextContact3.getText().toString();

                hideKeyboard(editTextContact3);
                editTextContact3.clearFocus();

                insertInDataBase(2);


            }
        });



        editTextContact1.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        editTextContact2.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        editTextContact3.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

            loadContacts(this);
            displayNumbers();

        loadContacts(this);


    }

    private void displayNumbers() {
        editTextContact3.setText(contact3);
        editTextContact2.setText(contact2);
        editTextContact1.setText(contact1);
        /*ContactDbHelper contactHelper = new ContactDbHelper(this);
        SQLiteDatabase db = contactHelper.getReadableDatabase();
        String[] projections = {ContactContract.ContactEntry.COLUMN_CONTACT_NUMBER};


        Cursor cursor = db.query(ContactContract.ContactEntry.TABLE_NAME,
                projections,
                null,
                null,
                null,
                null, null
        );

        try {


            if (cursor.moveToLast()) {
                contact3 = cursor.getString(0);
                editTextContact3.setText(contact3);
                Log.e("display numbers : ", contact3);
            }
            if (cursor.moveToPrevious()) {
                contact2 = cursor.getString(0);
                editTextContact2.setText(contact2);
                Log.e("display numbers : ", contact2);
            }
            if (cursor.moveToPrevious()) {
                contact1 = cursor.getString(0);
                editTextContact1.setText(contact1);
                Log.e("display numbers : ", contact1);
            }
        } finally {
            cursor.close();
        }
*/
    }

    private void insertInDataBase(int id) {
        ContactDbHelper contactHelper = new ContactDbHelper(this);
        SQLiteDatabase db = contactHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(contact1!=null) {
            values.put(ContactEntry.COLUMN_CONTACT_NUMBER, contact1);
            long newRowId = db.insert(ContactEntry.TABLE_NAME, null, values);
            Log.e("insertInDataBase", Long.toString(newRowId));
        }
        if(contact2!=null) {
            values = new ContentValues();
            values.put(ContactEntry.COLUMN_CONTACT_NUMBER, contact2);
            long   newRowId = db.insert(ContactEntry.TABLE_NAME, null, values);
            Log.e("insertInDataBase", Long.toString(newRowId));
        }
        if(contact3!=null) {
            values = new ContentValues();
            values.put(ContactEntry.COLUMN_CONTACT_NUMBER, contact3);
            long    newRowId = db.insert(ContactEntry.TABLE_NAME, null, values);
            Log.e("insertInDataBase", Long.toString(newRowId));
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String getContact1() {

        return contact1;
    }

    public static String getContact2() {
        return contact2;
    }

    public static String getContact3() {
        return contact3;
    }

    public static void loadContacts(Context context){
        ContactDbHelper contactHelper = new ContactDbHelper(context);
        SQLiteDatabase db = contactHelper.getReadableDatabase();
        String[] projections = {ContactContract.ContactEntry.COLUMN_CONTACT_NUMBER};


        Cursor cursor = db.query(ContactContract.ContactEntry.TABLE_NAME,
                projections,
                null,
                null,
                null,
                null, null
        );

        try {


            if (cursor.moveToLast()) {
                contact3 = cursor.getString(0);

            }
            if (cursor.moveToPrevious()) {
                contact2 = cursor.getString(0);

            }
            if (cursor.moveToPrevious()) {
                contact1 = cursor.getString(0);

            }
        } finally {
            cursor.close();
        }
    }
}
