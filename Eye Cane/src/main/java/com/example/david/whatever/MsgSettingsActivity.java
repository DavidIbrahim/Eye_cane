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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.david.whatever.data.ContactContract;
import com.example.david.whatever.data.ContactDbHelper;
import com.example.david.whatever.data.MsgContract;
import com.example.david.whatever.data.MsgDbHelper;

import static com.example.david.whatever.R.id.contact;
import static com.example.david.whatever.R.id.editTextContact1;

public class MsgSettingsActivity extends AppCompatActivity {
    private static String contact1;
    private EditText editTextContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        editTextContact = (EditText) findViewById(R.id.editMsgContact);

        Button saveButton1 = (Button) findViewById(R.id.saveMsgContact);
        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact1 = editTextContact.getText().toString();

                hideKeyboard(editTextContact);
                editTextContact.clearFocus();

                insertInDataBase();


            }
        });

        editTextContact.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
     //   loadContacts(this);
        showNumbers();
    }
    public static void loadContacts(Context context) {
        MsgDbHelper contactHelper = new MsgDbHelper(context);
        SQLiteDatabase db = contactHelper.getReadableDatabase();
        String[] projections = {MsgContract.MsgEntry.COLUMN_CONTACT_NUMBER};

        Cursor cursor = db.query(MsgContract.MsgEntry.TABLE_NAME,
                projections,
                null,
                null,
                null,
                null, null
        );
        try {
            if(cursor.moveToLast()){
                contact1 = cursor.getString(0);
            }
        }
        finally {
            cursor.close();
        }

    }

    public static String getContact1() {
        return contact1;
    }

    private void insertInDataBase() {
        MsgDbHelper msgDbHelper = new MsgDbHelper(this);
        SQLiteDatabase db = msgDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(contact1!=null) {
            values.put(MsgContract.MsgEntry.COLUMN_CONTACT_NUMBER, contact1);
            long newRowId = db.insert(MsgContract.MsgEntry.TABLE_NAME, null, values);
            Log.e("insertInDataBase", Long.toString(newRowId));
        }

    }

    private void showNumbers(){
        editTextContact.setText(contact1);

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

