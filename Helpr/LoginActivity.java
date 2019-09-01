package com.paulfoleyblogs.Helpr.Helpr;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 0;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        myDb = new DatabaseHelper(this);

        if (auth.getCurrentUser() != null) {
            int check = myDb.checkData();
            if (check > 0) {
                //has token can auto log in
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            } else {
                //not created tables yet so direct
                Intent intent = new Intent(this, GatheringInfo.class);
                startActivity(intent);
            }
        } else {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setTheme(R.style.GreenTheme)
                    .setLogo(R.drawable.transparent_logo)
                    .setIsSmartLockEnabled(false, true)
                    .setAvailableProviders(Arrays.asList(
                            new AuthUI.IdpConfig.FacebookBuilder().build(),
                            new AuthUI.IdpConfig.TwitterBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                            new AuthUI.IdpConfig.EmailBuilder().build()))
                    .build(), RC_SIGN_IN);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(this, GatheringInfo.class);
                startActivity(intent);
            }
        }
    }
}
