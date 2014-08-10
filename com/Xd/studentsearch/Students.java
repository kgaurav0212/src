package com.Xd.studentsearch;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Students extends Activity implements OnClickListener,
		OnItemSelectedListener {
	EditText etname;
	Button bsearch;
	Spinner batchspinner, deptspinner, progspinner, hallspinner, genderspinner;
	String[] batch = { "Any", "11", "12", "13", "14" };
	String[] dept = { "Any", "CSE", "EE", "ME", "CHE", "CE" };
	String[] prog = { "Any", "BTech", "MTech" };
	String[] hall = { "Any", "HALL1", "HALL2", "HALL3", "HALL4" };
	String[] gender = { "Any", "M", "F" };
	ArrayAdapter<String> batchadapter, progadapter, deptadapter, halladapter,
			genderadapter;
	String rBatch, rdept, rprog, rhall, rgender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		etname = (EditText) findViewById(R.id.etName);

		bsearch = (Button) findViewById(R.id.bSearch);
		bsearch.setOnClickListener(this);

		deptadapter = new ArrayAdapter<String>(Students.this,
				android.R.layout.simple_spinner_dropdown_item, dept);
		deptspinner = (Spinner) findViewById(R.id.deptSpinner);
		deptspinner.setAdapter(deptadapter);
		deptspinner.setOnItemSelectedListener(this);

		progadapter = new ArrayAdapter<String>(Students.this,
				android.R.layout.simple_spinner_dropdown_item, prog);
		progspinner = (Spinner) findViewById(R.id.progSpinner);
		progspinner.setAdapter(progadapter);
		progspinner.setOnItemSelectedListener(this);

		batchadapter = new ArrayAdapter<String>(Students.this,
				android.R.layout.simple_spinner_dropdown_item, batch);
		batchspinner = (Spinner) findViewById(R.id.batchSpinner);
		batchspinner.setAdapter(batchadapter);
		batchspinner.setOnItemSelectedListener(this);

		halladapter = new ArrayAdapter<String>(Students.this,
				android.R.layout.simple_spinner_dropdown_item, hall);
		hallspinner = (Spinner) findViewById(R.id.hallSpinner);
		hallspinner.setAdapter(halladapter);
		hallspinner.setOnItemSelectedListener(this);

		genderadapter = new ArrayAdapter<String>(Students.this,
				android.R.layout.simple_spinner_dropdown_item, gender);
		genderspinner = (Spinner) findViewById(R.id.genderSpinner);
		genderspinner.setAdapter(genderadapter);
		genderspinner.setOnItemSelectedListener(this);

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

		// TODO Auto-generated method stub
		int position;
		switch (arg0.getId()) {
		case R.id.batchSpinner:
			position = batchspinner.getSelectedItemPosition();
			if (position == 0) {
				rBatch = new String("NOT NULL");
				break;
			} else {
				rBatch = batch[position];

			}
			break;
		case R.id.hallSpinner:
			position = hallspinner.getSelectedItemPosition();
			if (position == 0) {
				rhall = new String("NOT NULL");
				break;
			} else {
				rhall = hall[position];

			}
			break;
		case R.id.genderSpinner:
			position = genderspinner.getSelectedItemPosition();
			if (position == 0) {
				rgender = new String("NOT NULL");
				break;
			} else {
				rgender = gender[position];

			}
			break;
		case R.id.deptSpinner:
			position = deptspinner.getSelectedItemPosition();
			if (position == 0) {
				rdept = new String("NOT NULL");
			} else {
				rdept = dept[position];
			}
			break;
		case R.id.progSpinner:
			position = progspinner.getSelectedItemPosition();
			if (position == 0) {
				rprog = new String("NOT NULL");
			} else {
				rprog = prog[position];
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.batchSpinner:
			rBatch = "NOT NULL";
			break;
		case R.id.deptSpinner:
			rdept = "NOT NULL";
			break;
		case R.id.progSpinner:
			rprog = "NOT NULL";
			break;
		case R.id.hallSpinner:
			rhall = "NOT NULL";
			break;
		case R.id.genderSpinner:
			rgender = "NOT NULL";
			break;

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bSearch:
			String rname = new String(etname.getText().toString());
			try {

				Intent ourIntent = new Intent(Students.this, Ouput.class);
				Bundle pack = new Bundle();
				pack.putString("name", rname);
				pack.putString("batch", rBatch);
				pack.putString("prog", rprog);
				pack.putString("dept", rdept);
				pack.putString("hall", rhall);
				pack.putString("gender", rgender);
				ourIntent.putExtras(pack);
				startActivity(ourIntent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "sorry can not open", Toast.LENGTH_SHORT)
						.show();
			}
			break;
		}
	}
}
