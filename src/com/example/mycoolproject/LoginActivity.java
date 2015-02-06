package com.example.mycoolproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.session.SessionManager;

public class LoginActivity extends ActionBarActivity {

	private SessionManager session;
	private EditText username = null;
	private EditText password = null;
	private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		session = new SessionManager(getApplicationContext());
		if (!session.isLoggedIn()) {
			setContentView(R.layout.fragment_login);
			session = new SessionManager(getApplicationContext());
			username = (EditText) findViewById(R.id.username);
			password = (EditText) findViewById(R.id.password);
			login = (Button) findViewById(R.id.button);
			login.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					login(view);
				}
			});
		} else {
			Intent loggedIntent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(loggedIntent);
		}

	}

	public void login(View view) {
		List<String> authList = getAuthentication();
		if (username.getText().toString().equals(authList.get(0))
				&& password.getText().toString().equals(authList.get(0))) {
			session.createLoginSession(username.getText().toString());
			Intent loginIntent = new Intent(this, MainActivity.class);
			startActivity(loginIntent);
			finish();
		} else {
			Toast.makeText(getApplicationContext(), R.string.wrong_login,
					Toast.LENGTH_SHORT).show();
		}

	}

	private static List<String> getAuthentication() {
		List<String> list = new ArrayList<String>();
		try {
			File sdcard = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			File file = new File(sdcard, "password.txt");

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
