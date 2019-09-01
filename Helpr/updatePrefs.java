package com.paulfoleyblogs.Helpr.Helpr;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.regex.Pattern;

public class updatePrefs extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseHelper myDb;
    private EditText childName, guardianPhone, guardianEmail;
    private String emailNotification = "N", phoneNotification = "N";
    private boolean checker;
    public static final String DATABASE_NAME = "User.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prefs);
        auth = FirebaseAuth.getInstance();
        childName = findViewById(R.id.childNameField);
        guardianPhone = findViewById(R.id.phoneNoField);
        guardianEmail = findViewById(R.id.guardianEmailField);
        myDb = new DatabaseHelper(this);
        guardianPhone.setText(myDb.getContactNumber());
        childName.setText(myDb.getChildName());
        guardianEmail.setText(myDb.getContactEmail());

        Button gatherInfoSave = findViewById(R.id.gatherInfoSave);
        final CheckBox emailNot = findViewById(R.id.emailNot);
        final CheckBox phoneNot = findViewById(R.id.phoneNot);

        if (myDb.getEmailPref().equalsIgnoreCase("Y")) {
            emailNot.setChecked(true);
        }
        if (myDb.getMsgPref().equalsIgnoreCase("Y")) {
            phoneNot.setChecked(true);
        }
        gatherInfoSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            checker = true;

            if (TextUtils.isEmpty(childName.getText().toString())) {
                childName.setError(getString(R.string.notEmprtyNoti));
                checker = false;
            }

            if (TextUtils.isEmpty(guardianPhone.getText().toString())) {
                guardianPhone.setError(getString(R.string.notEmprtyNoti));
                checker = false;
            }

            if (TextUtils.isEmpty(guardianEmail.getText().toString())) {
                guardianEmail.setError(getString(R.string.notEmprtyNoti));
                checker = false;
            }

            if (!isValidEmail(guardianEmail.getText().toString())) {
                guardianEmail.setError(getString(R.string.validEmailNoti));
                checker = false;
            }

            if (checker) {
                myDb = new DatabaseHelper(getApplicationContext());
                if (emailNot.isChecked()) {
                    emailNotification = "Y";
                }
                if (phoneNot.isChecked()) {
                    phoneNotification = "Y";
                }

                boolean test = myDb.updateDataToUserContact(childName.getText().toString(), guardianPhone.getText().toString(), guardianEmail.getText().toString(), emailNotification, phoneNotification);
                if (test) {
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    startActivity(i);
                }
            }
            }
        });

    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pref_menu, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.logOutTitle)
                        .setMessage(R.string.logoutPrompt)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                getApplicationContext().deleteDatabase(DATABASE_NAME);
                                auth.getCurrentUser().delete();
                                auth.signOut();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;


            case R.id.homeMenu:
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                return true;

            case R.id.request:
                final CharSequence[] action = new CharSequence[]{"Food", "Drink", "Sleep", "Play", "Sick", "Toilet"};

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.requestTitle);
                builder.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), RequestGraph.class);
                        intent.putExtra("action", action[which]);
                        startActivity(intent);
                    }
                });
                AlertDialog alert =builder.create();
                //set dividers
                ListView listView =alert.getListView();
                ColorDrawable dividerColor = new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark));
                listView.setDivider(dividerColor);
                listView.setDividerHeight(1);
                //show
                alert.show();
                // builder.show();
                return true;

            case R.id.faq:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paulfoleyblogs.com/helpr/faq"));
                startActivity(browserIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
