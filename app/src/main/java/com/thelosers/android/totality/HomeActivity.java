package com.thelosers.android.totality;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

  private TextView mIntroTextview;
  private String mSharedPreferenceName = "Totality shared preferenced";
  private String mSharedPreferenceKey = "Totality";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    mIntroTextview = (TextView) findViewById(R.id.intro_textview);

    SharedPreferences pref = getSharedPreferences(
        mSharedPreferenceName, Context.MODE_PRIVATE);
    String username = pref.getString(mSharedPreferenceKey, null);

    if (username == null) {
      startActivity(new Intent(getApplicationContext(), MainActivity.class));
      finish();
    } else {
      mIntroTextview.setText("Hey !!" + username );
    }
  }


  @Override
  public void onBackPressed() {
    Intent a = new Intent(Intent.ACTION_MAIN);
    a.addCategory(Intent.CATEGORY_HOME);
    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(a);
  }
}
