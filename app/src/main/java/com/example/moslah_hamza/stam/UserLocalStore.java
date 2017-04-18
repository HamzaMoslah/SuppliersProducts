package com.example.moslah_hamza.stam;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Moslah_Hamza on 18/04/2017.
 */

//user shared preferences class
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    //store user's informations in the shared preferences
    public void storeUserDate(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("Name", user.get_name());
        spEditor.putString("Password", user.get_pwd());
        spEditor.putString("Email", user.get_email());
        spEditor.putInt("id", user.get_id());

        spEditor.commit();
    }

    //get the current user's information
    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String Name = userLocalDatabase.getString("Name", "");
        String Password = userLocalDatabase.getString("Password", "");
        String Email = userLocalDatabase.getString("Email", "");
        int id = userLocalDatabase.getInt("id", 0);

        User user = new User(Name, Email, Password);
        user.set_id(id);

        return user;
    }

    //call after log in
    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        return userLocalDatabase.getBoolean("loggedIn", false);
    }

    //clear user data after log out
    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}

