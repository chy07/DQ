package com.xinyue.dongqi;

import com.xinyue.zxing.activity.MipcaActivityCapture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 扫描拣货单
 * 
 * @author chenhongyu
 *
 */
public class RuInputCodeActivity extends PActivity {

	private View rightViewMore;
	private EditText edit_code;
	protected final static int SCANNIN_GREQUEST_CODE = 4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.inputscan);
		mAbTitleBar.setTitleText("扫描单据");
		mAbTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color.blue2));
		rightViewMore = mInflater.inflate(R.layout.scan, null);
		mAbTitleBar.addRightView(rightViewMore);
		ImageView scan_code =(ImageView) rightViewMore.findViewById(R.id.scan_code);
		edit_code = (EditText) findViewById(R.id.edit_code);
		TextView codeOk =(TextView) findViewById(R.id.codeOk);
		codeOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String flag= edit_code.getText().toString();
				if(flag.equals("")){
					Toast.makeText(RuInputCodeActivity.this, "请先扫描单据", 0).show();
					return;
				}
				Intent intent = new Intent(RuInputCodeActivity.this,RuScanActivity.class);
				intent.putExtra("code", flag);
				startActivity(intent);
			}
		});
		//扫描scan_code
		scan_code.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(RuInputCodeActivity.this, MipcaActivityCapture.class);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				//bundle.getString("result")
				edit_code.setText(bundle.getString("result"));
			}
			break;
		}
	}
}
