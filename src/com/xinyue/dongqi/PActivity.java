package com.xinyue.dongqi;


import android.os.Bundle;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;

public class PActivity extends AbActivity{
	
	public AbTitleBar mAbTitleBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setLogo(R.drawable.back_n);
		mAbTitleBar.setTitleBarBackground(R.color.black);
		mAbTitleBar.setLogoLine(R.drawable.line);
	}
}
