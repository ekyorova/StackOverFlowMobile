package com.example.mycoolproject;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.TextView;

import com.example.dto.Comment;
import com.example.dto.CommentList;
import com.example.session.SessionManager;
import com.example.synctasks.CommentWebSyncTask;

public class CommentActivity extends ActionBarActivity {
	
	private List<String> comments = new ArrayList<String>();
	private SessionManager session;
	private String id;
	private String name;
	private CommentListArrayAdapter adapter;
	Parcelable state = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		session = new SessionManager(getApplicationContext());
		setContentView(R.layout.activity_comment);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    id = extras.getString("user_id");
		    name = extras.getString("user_name");
		}
		
		setTitle(name);
		
		adapter = new CommentListArrayAdapter(this, comments);
		
		CommentWebSyncTask task = new CommentWebSyncTask() {
			@Override
			protected void onPostExecute(CommentList result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				for (Comment comment : result.getComments()) {
					comments.add(comment.getTitle() + " Score: " + comment.getScore());
				}
				adapter.notifyDataSetChanged();

			}
		};
		
		task.execute(new String[] { "https://api.stackexchange.com/2.2/users/" + id + "/answers?order=desc&sort=activity&site=stackoverflow&filter=!9WA((OwZp" });

		AbsListView listView = (AbsListView) findViewById(R.id.list);
		state = listView.onSaveInstanceState();
		listView.setAdapter(adapter);
		listView.onRestoreInstanceState(state);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
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
			View rootView = inflater.inflate(R.layout.fragment_comment,
					container, false);
			return rootView;
		}
	}

}
