package com.example.steph.beginerwourkoutapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ProfileActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get preference xml from xml directory
        addPreferencesFromResource(R.xml.profile_pref);
    }
}
