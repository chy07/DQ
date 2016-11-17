package com.xinyue.dongqi;

import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.UICheckUpdateCallback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends PActivity {

	private TextView btn_exit;
	private LinearLayout ru_btn;
	private LinearLayout chu_btn;
	private ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_main);
		mAbTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color.blue2));
		
		viewInit();
		viewOnclick();
		dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		BDAutoUpdateSDK.uiUpdateAction(this, new MyUICheckUpdateCallback());
	}
	private class MyUICheckUpdateCallback implements UICheckUpdateCallback {

		@Override
		public void onCheckComplete() {
			// 点击忽略此版本
			dialog.dismiss();
		}

	}
	private void viewInit() {
		View rightViewMore = mInflater.inflate(R.layout.exit, null);
		mAbTitleBar.addRightView(rightViewMore);
		btn_exit = (TextView) rightViewMore.findViewById(R.id.btn_exit);
		ru_btn = (LinearLayout) findViewById(R.id.ru_btn);
		chu_btn = (LinearLayout) findViewById(R.id.chu_btn);
	}

	private void viewOnclick() {
		// TODO Auto-generated method stub
		// 退出登录
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences sp = getSharedPreferences("dongqi", Activity.MODE_PRIVATE);
				Editor editor = sp.edit();
				editor.putString("user", "");
				editor.commit();
				Intent intent = new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// 入库上架
		ru_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,RuInputCodeActivity.class);
				startActivity(intent);
			}
		});
		// 出库拣货
		chu_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,InputCodeActivity.class);
				startActivity(intent);
			}
		});
	}

}
