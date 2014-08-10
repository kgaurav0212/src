package com.Xd.studentsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Main extends Activity {

	Search es1;
	InputStream input;
	BufferedReader br;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (!prefs.getBoolean("firstTime", false)) {
			new loadSomeStuff().execute();
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("firstTime", true);
			editor.commit();
		}
		else
		{
			Intent i = new Intent(Main.this, Students.class);
			startActivity(i);
			finish();
		}
			

	}

	public class loadSomeStuff extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			// example of setting up something
			AssetManager assetManager = getAssets();
			try {
				es1 = new Search(Main.this);
				input = assetManager.open("data.txt");

				br = new BufferedReader(new InputStreamReader(input));

				es1.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dialog = new ProgressDialog(Main.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			for (int i = 0; i < 20; i++) {
				publishProgress(5);
				// run your one time code
				// SharedPreferences.Editor editor = prefs.edit();
				// editor.putBoolean("firstTime", true);
				// editor.commit();
				// int tempcount = 0;

				try {
					while (true) {
						String text = null;
						text = br.readLine();
						if (text == null)
							break;
						String[] ar = text.split("%");

						es1.createEntry(ar[0], ar[1], ar[2], ar[3], ar[4],
								ar[5], ar[6], ar[7], ar[9],
								ar[0].substring(0, 2));

					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			dialog.dismiss();
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			// TODO Auto-generated method stub
			dialog.incrementProgressBy(progress[0]);
			super.onProgressUpdate(progress);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Main.this, Students.class);
			startActivity(i);
			super.onPostExecute(result);
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		try {
			input.close();
			es1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finish();
		super.onPause();
	}

}
