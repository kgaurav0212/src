package com.Xd.studentsearch;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ouput extends Activity {

	String oname, odept, obatch, oprog, ogender, ohall;
	Cursor c;
	Search es;
	ListView myList;
	private MyCustomAdapter mAdapter;
	private List<MyData> mData = new ArrayList<MyData>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output);
		initialize();
		es = new Search(this);

		mAdapter = new MyCustomAdapter();
		try {
			es.open();

			c = es.getData(oname, odept, obatch, oprog, ohall, ogender);
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				try {
					mAdapter.addItem(
							c.getString(c.getColumnIndex(Search.KEY_NAME)),
							c.getString(c.getColumnIndex(Search.KEY_ROLL)),
							c.getString(c.getColumnIndex(Search.KEY_BATCH)),
							c.getString(c.getColumnIndex(Search.KEY_DEPT)),
							c.getString(c.getColumnIndex(Search.KEY_PROG)),
							c.getString(c.getColumnIndex(Search.KEY_GENDER)),
							c.getString(c.getColumnIndex(Search.KEY_ROOM)),
							c.getString(c.getColumnIndex(Search.KEY_HALL)),
							c.getString(c.getColumnIndex(Search.KEY_EMAIL)),
							c.getString(c.getColumnIndex(Search.KEY_BGROUP)));
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(this, "Couldn't Add", Toast.LENGTH_SHORT)
							.show();
				}
			}
			es.close();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "sorry not found", Toast.LENGTH_SHORT).show();
		}
		try {
			myList.setAdapter(mAdapter);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "problem in List", Toast.LENGTH_SHORT).show();
		}

	}

	private void initialize() {
		// TODO Auto-generated method stub

		myList = (ListView) findViewById(R.id.lvmain);
		Bundle gotpack = getIntent().getExtras();
		oname = gotpack.getString("name");
		if (oname.length() < 1)
			oname = "NOT NULL";
		obatch = gotpack.getString("batch");
		odept = gotpack.getString("dept");
		oprog = gotpack.getString("prog");
		ohall = gotpack.getString("hall");
		ogender = gotpack.getString("gender");

	}

	private class MyCustomAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyCustomAdapter() {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void addItem(String name, String roll, String batch,
				String dept, String prog, String gender, String room,
				String hall, String email, String bgroup) {
			MyData data = new MyData(name, roll, batch, dept, prog, gender,
					room, hall, email, bgroup);
			mData.add(data);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// System.out.println("getView " + position + " " + convertView);
			String source = "drawable/i";
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.view, null);
			}
			TextView tvN = (TextView) convertView.findViewById(R.id.tvName);
			TextView tvR = (TextView) convertView.findViewById(R.id.tvRoll);
			TextView tvP = (TextView) convertView.findViewById(R.id.tvProg);
			TextView tvD = (TextView) convertView.findViewById(R.id.tvDept);
			TextView tvRoom = (TextView) convertView.findViewById(R.id.tvRoom);
			TextView tvH = (TextView) convertView.findViewById(R.id.tvHall);
			TextView tvbG = (TextView) convertView.findViewById(R.id.tvbGroup);
			ImageView ivP = (ImageView) convertView.findViewById(R.id.ivPic);

			// tvN.setText("No WAY");

			try {
				tvN.setText(mData.get(position).getName());
				tvR.setText(mData.get(position).getRoll());
				tvP.setText(mData.get(position).getProg());
				tvD.setText(mData.get(position).getdept());
				tvRoom.setText(mData.get(position).getRoom());
				tvH.setText(mData.get(position).getHall());
				tvbG.setText(mData.get(position).getBgroup());
				String uri = source + mData.get(position).getRoll();
				int imageResource = getResources().getIdentifier(uri, null,
						getPackageName());
				Drawable image = getResources().getDrawable(imageResource);
				ivP.setImageDrawable(image);

			} catch (Exception e) {
				e.printStackTrace();
				//Toast.makeText(Ouput.this, "Problem in Holder",
					//	Toast.LENGTH_SHORT).show();
			}
			return convertView;

		}
	}
}