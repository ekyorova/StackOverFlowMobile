package com.example.mycoolproject;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.example.dto.User;
import com.example.dto.UserList;
import com.example.session.SessionManager;
import com.example.synctasks.WebSyncTask;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	protected static final String LOG_TAG = "TAG";
	private List<User> userInfo = new ArrayList<User>();
	private SessionManager session;
	private ListArrayAdapter adapter;
	Parcelable state = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		session = new SessionManager(getApplicationContext());
		setContentView(R.layout.fragment_main);
		
		adapter = new ListArrayAdapter(this, userInfo);

		WebSyncTask task = new WebSyncTask() {
			@Override
			protected void onPostExecute(UserList result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				for (User user : result.getUsers()) {
					userInfo.add(user);
				}
				adapter.notifyDataSetChanged();

			}
		};
		task.execute(new String[] { "https://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow" });

		AbsListView listView = (AbsListView) findViewById(R.id.list);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				User selectedObject = (User) parent.getItemAtPosition(position);
				Intent commentIntent = new Intent(parent.getContext(),
						CommentActivity.class);
				commentIntent.putExtra("user_id", selectedObject.getUserId());
				commentIntent.putExtra("user_name", selectedObject.getDisplayName());
				parent.getContext().startActivity(commentIntent);
				
			}
		});
		state = listView.onSaveInstanceState();
		listView.setAdapter(adapter);
		listView.onRestoreInstanceState(state);
	}


	@Override
	/**
	 * 
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_logout: {
			session.logoutUser();
		}
		}
		return super.onOptionsItemSelected(item);

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public List<User> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<User> userInfo) {
		this.userInfo = userInfo;
	}

}
