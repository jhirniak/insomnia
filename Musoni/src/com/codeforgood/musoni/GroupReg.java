package com.codeforgood.musoni;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroupReg extends Activity {

	private Button buttonGroupInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_reg_form1);

		buttonGroupInfo = (Button) findViewById(R.id.buttonGroupInf);
		buttonGroupInfo.setBackgroundColor(getResources().getColor(
				android.R.color.holo_blue_dark));
	}

	public void onClickGroupInfo(View view) {

		
	}

	public void onClickGroupMembers(View view) {

		buttonGroupInfo.setBackgroundResource(android.R.drawable.btn_default);

	}

	public void onClickCertification(View view) {

	}

}