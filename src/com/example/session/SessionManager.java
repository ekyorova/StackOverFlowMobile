package com.example.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.mycoolproject.LoginActivity;

public class SessionManager {
	SharedPreferences pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	
	private static final String PREF_NAME = "AndroidHivePref";
	private static final String IS_LOGIN = "IsLoggedIn";
	public static final String KEY_NAME = "username";

	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void createLoginSession(String name) {
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_NAME, name);
		editor.commit();
	}

	public String getUserDetails() {
		String user = pref.getString(KEY_NAME, null);
		return user;
	}

	public void logoutUser() {
		editor.clear();
		editor.commit();
		Intent i = new Intent(_context, LoginActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		_context.startActivity(i);
	}

	public boolean isLoggedIn() {
		return pref.getBoolean(IS_LOGIN, false);
	}
}