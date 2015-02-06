package com.example.mycoolproject;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dto.User;
import com.example.imageLoad.ImageLoader;

public class ListArrayAdapter extends ArrayAdapter<User> {
	private final Context context;
	private final List<User> values;
	public ImageLoader imageLoader; 

	public ListArrayAdapter(Context context, List<User> myarray) {
		super(context, R.layout.list_footballer, myarray);
		this.context = context;
		this.values = myarray;
		imageLoader=new ImageLoader(context);
	}

	@Override
	public View getView(int position, View convertView,final ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater
				.inflate(R.layout.list_footballer, parent, false);
		User user = getItem(position);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		
		textView.setText(user.getDisplayName());
		imageLoader.displayImage(user.getProfileImage(), imageView);
		return rowView;
	}
	
}