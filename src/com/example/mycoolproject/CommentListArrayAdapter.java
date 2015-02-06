package com.example.mycoolproject;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentListArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final List<String> values;

	public CommentListArrayAdapter(Context context, List<String> mystringarray) {
		super(context, R.layout.list_comment, mystringarray);
		this.context = context;
		this.values = mystringarray;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_comment, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values.get(position));
		imageView.setImageResource(R.drawable.ic_launcher);

		return rowView;
	}
}