package com.thelosers.android.totality;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private String mDummyPassword = "123456";
  private Button mLoginButton;
  private EditText mUserNameEdittext, mUserPasswordEdittext;

  private String mSharedPreferenceName = "Totality shared preferenced";
  private String mSharedPreferenceKey = "Totality";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mLoginButton = (Button) findViewById(R.id.login_button);
    mUserNameEdittext = (EditText) findViewById(R.id.username_textview);
    mUserPasswordEdittext = (EditText) findViewById(R.id.password_textview);

    mLoginButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String mUserName = mUserNameEdittext.getText().toString();
        String mUserPassword = mUserPasswordEdittext.getText().toString();

        if (mUserName.isEmpty()) {
          mUserNameEdittext.setError("Please Enter User Name !!");
          return;
        }

        if (mUserPassword.isEmpty()) {
          mUserPasswordEdittext.setError("Please Enter Password !!");
          return;
        }

        if (!mUserPassword.equals(mDummyPassword)) {
          mUserPasswordEdittext.setError("Invalid User or Password !!");
          return;
        }

        SharedPreferences prefs = getSharedPreferences(mSharedPreferenceName,
            Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(mSharedPreferenceKey, mUserName);
        editor.commit();

        Toast.makeText(getBaseContext(), "Login Successed !!!", Toast.LENGTH_LONG)
            .show();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();


      }
    });
  }


  @Override
  protected void onStart() {
    super.onStart();
    SharedPreferences pref = getSharedPreferences(
        mSharedPreferenceName, Context.MODE_PRIVATE);
    String username = pref.getString(mSharedPreferenceKey, null);

    if (username != null) {
      startActivity(new Intent(getApplicationContext(), HomeActivity.class));
      finish();
    }
  }
}

