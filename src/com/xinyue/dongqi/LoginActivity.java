package com.xinyue.dongqi;

import com.ab.activity.AbActivity;
import com.ab.fragment.AbLoadDialogFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AbActivity {
	private TextView login;
	private TextView username;
	private TextView password;
	private AbHttpUtil mAbHttpUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.login);
		viewInit();
		viewOnclick();
	}

	private void viewInit() {
		mAbHttpUtil = AbHttpUtil.getInstance(LoginActivity.this);
		SharedPreferences sp = getSharedPreferences("dongqi", Activity.MODE_PRIVATE);
		String flag = sp.getString("user", "");
		if (!flag.equals("")) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}

		login = (TextView) findViewById(R.id.login);
		username = (TextView) findViewById(R.id.username);
		password = (TextView) findViewById(R.id.password);
	}

	private void viewOnclick() {
		// TODO Auto-generated method stub
		// µ«¬º
		login.setOnClickListener(new OnClickListener() {

			private AbLoadDialogFragment mDialogFragment;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userName = username.getText().toString().trim();
				String passWord = password.getText().toString().trim();
				if (userName.equals("")) {
					Toast.makeText(LoginActivity.this, "«Î ‰»Î”√ªß√˚", 0).show();
				} else if (passWord.equals("")) {
					Toast.makeText(LoginActivity.this, "«Î ‰»Î√‹¬Î", 0).show();
				} else {

					String loginurl = "http://www.baidu.com";
					AbDialogUtil.removeDialog(LoginActivity.this);
					mDialogFragment = AbDialogUtil.showLoadDialog(LoginActivity.this,
							R.drawable.icon_share_moments, "µ«¬º÷–,«Î…‘∫Ú°£°£°£");
					mAbHttpUtil.post(loginurl, null, new AbStringHttpResponseListener() {

						@Override
						public void onStart() {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
                           Intent intent =  new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);
                           mDialogFragment.dismiss();
						}

						@Override
						public void onFailure(int statusCode, String content, Throwable error) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onSuccess(int statusCode, String content) {
							// TODO Auto-generated method stub

						}
					});
				}
			}
		});

	}
}
