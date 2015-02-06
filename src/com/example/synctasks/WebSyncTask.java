package com.example.synctasks;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dto.UserList;
import com.example.mycoolproject.MainActivity;
import com.google.gson.Gson;

public abstract class WebSyncTask extends AsyncTask<String, Void, UserList> {

	private UserList response = new UserList();

	@Override
	protected UserList doInBackground(String... urls) {
		return getJSONObject(readStackOverFlowData(urls[0]));
	}

	private String readStackOverFlowData(String url) {
		String answer = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				answer = encodingParse(response);
			} else {
				Log.e(MainActivity.class.toString(), "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}

	private UserList getJSONObject(String string) {
		String jsonString = string;
		Gson jsonObject = new Gson();
		UserList response = jsonObject.fromJson(jsonString, UserList.class);
		return response;
	}

	public String encodingParse(HttpResponse response) {
		String responseBody = null;
		if (response != null && response.getEntity() != null) {
			if (response.getEntity() != null) {
				GZIPInputStream gzipInputStream;
				try {
					gzipInputStream = new GZIPInputStream(response.getEntity()
							.getContent());
					byte[] buffer = new byte[1024];
					int read;
					StringBuffer stringBuffer = new StringBuffer();
					while ((read = gzipInputStream.read(buffer)) >= 0) {
						stringBuffer.append(new String(buffer, 0, read));
					}
					responseBody = stringBuffer.toString();
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return responseBody;
	}

	@Override
	protected void onPostExecute(UserList result) {
		super.onPostExecute(result);
	}

	public UserList getResponse() {
		return response;
	}

	public void setResponse(UserList response) {
		this.response = response;
	}
}
