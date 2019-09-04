package com.ideatech.org.online_noticeboard;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MianGhazanfar on 4/24/2016.
 */
public class SessionManager {
    private static String TAG=SessionManager.class.getSimpleName();

    //SharedPrefrence
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    private static final String PREF_NAME="aocpref";
    private static final String KEY_ISLOGEDIN="isloggedin";
    private static final String USER_NAME="uname";
    private static final String USER_EMAIL="uemail";
    private static final String USER_ID="uid";
    private static final String Created_AT="c_at";
    public SessionManager(Context context){
        this._context=context;
        pref=_context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor=pref.edit();
    }

    public void setLoggedin(boolean login){
        editor.putBoolean(KEY_ISLOGEDIN,login);
        editor.commit();
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_ISLOGEDIN,false);
    }

    public void addUser(String name,String email,String uid,String created_at){
        editor.putString(USER_NAME,name);
        editor.putString(USER_EMAIL,email);
        editor.putString(USER_ID,uid);
        editor.putString(Created_AT,created_at);
        editor.commit();
    }
    public String getUserName(){
        return pref.getString(USER_NAME,null);
    }
    public String getUserEmail(){
        return pref.getString(USER_EMAIL,null);
    }
    public String getUserId(){
        return pref.getString(USER_ID,null);
    }
    public String getCreated_AT(){
        return pref.getString(Created_AT,null);
    }
public void logout(){
    editor.clear();
    editor.commit();
}
}
