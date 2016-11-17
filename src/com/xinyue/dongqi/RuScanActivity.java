package com.xinyue.dongqi;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import com.ab.fragment.AbLoadDialogFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbDialogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinyue.dongqi.adapter.DetailAdapter;
import com.xinyue.dongqi.model.ManifestDetail;
import com.xinyue.dongqi.util.NetWorkUtils;
import com.xinyue.zxing.activity.MipcaActivityCapture;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RuScanActivity extends PActivity {

	private TextView scan;
	protected final static int SCANNIN_GREQUEST_CODE = 1;
	protected final static int SCANNIN_GREQUEST_CODE2 = 2;
	protected final static int SCANNIN_GREQUEST_CODE3 = 3;
	private TextView scan2;
	private TextView scan3;
	private AbHttpUtil mAbHttpUtil;
	private View rightViewMore;
	private Button upload;
	private LinearLayout whole;
	private AbLoadDialogFragment mDialogFragment;
	private TextView dhrq;
	private TextView bh;
	private TextView mddz;
	private TextView mddh;
	private TextView khddh;
	private TextView mdmc;
	private DetailAdapter detailAdapter;
	private ListView listview;
	private List<ManifestDetail> detaillist;
	private String bianhao;
	int flag = 0;
	private AbLoadDialogFragment mDialogFragment2;
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			if (flag < detaillist.size()) {
				Message message = new Message();
				message.what = 1;
				myHandler.sendMessage(message);
			} else {
				Message message = new Message();
				message.what = 2;
				myHandler.sendMessage(message);
			}

		}
	};
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				flag++;
				Log.e("进入", "handler");

				uploaddata();
				break;
			case 2:
				changedata();
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.chu_scan);
		mAbTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color.blue2));
		rightViewMore = mInflater.inflate(R.layout.upload_save, null);
		mAbTitleBar.addRightView(rightViewMore);
		viewInit();
		viewOnclick();
	}

	private void viewInit() {
		String jhcode =getIntent().getStringExtra("code");
		mAbHttpUtil = AbHttpUtil.getInstance(RuScanActivity.this);
		whole = (LinearLayout) findViewById(R.id.whole);
		whole.setVisibility(View.GONE);
		scan2 = (TextView) findViewById(R.id.scan2);
		scan3 = (TextView) findViewById(R.id.scan3);
		upload = (Button) rightViewMore.findViewById(R.id.upload);
		mdmc = (TextView) findViewById(R.id.mdmc);
		dhrq = (TextView) findViewById(R.id.dhrq);
		mddz = (TextView) findViewById(R.id.mddz);
		khddh = (TextView) findViewById(R.id.khddh);
		bh = (TextView) findViewById(R.id.bh);
		listview = (ListView) findViewById(R.id.listview);
		detailAdapter = new DetailAdapter(RuScanActivity.this);

		getdataJH(jhcode);
	}

	private void viewOnclick() {
	    //仓位
		scan2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RuScanActivity.this, MipcaActivityCapture.class);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE2);
			}
		});
		//物料
		scan3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RuScanActivity.this, MipcaActivityCapture.class);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE3);
			}
		});
		// 上传数据
		upload.setOnClickListener(new OnClickListener() {

			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(detaillist==null||detaillist.size()<1){
					Toast.makeText(RuScanActivity.this, "请先获取拣货信息", 0).show();
					return;
				}
				mDialogFragment2 = AbDialogUtil.showLoadDialog(RuScanActivity.this,
						R.drawable.icon_share_moments, "正在提交,请稍候。。。");
				uploaddata();
		/*		Log.e("111", "---");
				for(int i=0;i<detaillist.size();i++){
					Log.e(""+detaillist.get(i).getCol_0001(), detaillist.get(i).getCol_0020());
				}
				Log.e("222", "---");*/
			}
		});
	}
	private void getdataJH(String mjhcode){
		String lastUrl = Contants.getInfoUrl + mjhcode + "]&templeId=111935";
		mDialogFragment = AbDialogUtil.showLoadDialog(RuScanActivity.this, R.drawable.icon_share_moments,
				"正在获取拣货信息,请稍候。。。");
		Log.e("chuscan", lastUrl);
		if (NetWorkUtils.isNetworkConnected(RuScanActivity.this)) {
			mAbHttpUtil.post(lastUrl, new AbStringHttpResponseListener() {

				@Override
				public void onSuccess(int statusCode, String content) {
					// TODO Auto-generated method stub
					Gson geson = new Gson();
					Map jc = geson.fromJson(content, Map.class);
					String code = (String) jc.get("code");
					// Log.e("进入","111");
					if (code.equals("1")) {
						whole.setVisibility(View.VISIBLE);
						Map jc2 = (Map) jc.get("desc");
						bianhao = (String) jc2.get("col_0015");
						if (jc2.get("col_0001").equals("null")) {
							mdmc.setText("客户名称：");
						} else {
							mdmc.setText("客户名称：" + jc2.get("col_0001"));
						}
						if (jc2.get("col_0004").equals("null")) {
							dhrq.setText("订货日期：");
						} else {
							dhrq.setText("订货日期：" + jc2.get("col_0004"));
						}
						if (jc2.get("col_0015").equals("null")) {
							bh.setText("编号：");
						} else {
							bh.setText("编号：" + jc2.get("col_0015"));
						}
						if (jc2.get("col_0024").equals("null")) {
							mddz.setText("门店地址：");
						} else {
							mddz.setText("门店地址：" + jc2.get("col_0024"));
						}
						if (jc2.get("col_0031").equals("null")) {
							khddh.setText("客户单号：");
						} else {
							khddh.setText("客户单号：" + jc2.get("col_0031"));
						}

						List<Map> detail = (List<Map>) jc2.get("detail");
						String descstring1 = geson.toJson(detail);
						detaillist = geson.fromJson(descstring1, new TypeToken<List<ManifestDetail>>() {
						}.getType());
						Log.e("size", detaillist.size() + "");
						detailAdapter.setList(detaillist);
						listview.setAdapter(detailAdapter);
					} else {
						Toast.makeText(RuScanActivity.this, jc.get("desc") + "", 0).show();
					}
				}

				@Override
				public void onStart() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					mDialogFragment.dismiss();
				}

				@Override
				public void onFailure(int statusCode, String content, Throwable error) {
					// TODO Auto-generated method stub

				}

			});
		} else {
			Toast.makeText(RuScanActivity.this, "网络状态异常", 0).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SCANNIN_GREQUEST_CODE2:
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				for (int i = 0; i < detaillist.size(); i++) {
					if (bundle.getString("result").equals(detaillist.get(i).getCol_0014())) {
						detaillist.get(i).setCwcolor("1");
					}
				}
				detailAdapter.setList(detaillist);
				detailAdapter.notifyDataSetChanged();
				Intent intent = new Intent(RuScanActivity.this, MipcaActivityCapture.class);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE2);

			}
			break;

		case SCANNIN_GREQUEST_CODE3:
			if (resultCode == RESULT_OK)

			{
				Bundle bundle = data.getExtras();

				for (int i = 0; i < detaillist.size(); i++) {
					if (bundle.getString("result").equals(detaillist.get(i).getCol_0002())) {
						detaillist.get(i).setCpcolor("1");
						detaillist.get(i).setCol_0020("1");
					}
				}

				detailAdapter.setList(detaillist);
				detailAdapter.notifyDataSetChanged();
				Intent intent = new Intent(RuScanActivity.this, MipcaActivityCapture.class);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE3);
			}
			break;

		}
	}

	// 上传 数据
	private void uploaddata() {
		if (NetWorkUtils.isNetworkConnected(RuScanActivity.this)) {
			String uUrl = Contants.uploadUrl + bianhao + ",col_0010_0001=" + detaillist.get(flag).getCol_0001()
					+ ",col_0010_0020=" + detaillist.get(flag).getCol_0020() + ",col_0010_0022="
					+ detaillist.get(flag).getCol_0022() + "]&templeId=111935";
			mAbHttpUtil.post(uUrl, new AbStringHttpResponseListener() {

				@Override
				public void onStart() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFinish() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFailure(int statusCode, String content, Throwable error) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(int statusCode, String content) {
					// TODO Auto-generated method stub
					Gson geson = new Gson();
					Map jc = geson.fromJson(content, Map.class);
					String code = (String) jc.get("code");
					Log.e("upload", "code=" + code);
					if (code.equals("1")) {
						if (flag < detaillist.size() - 1) {
							Message message = new Message();
							message.what = 1;
							myHandler.sendMessage(message);
						} else {
							Message message = new Message();
							message.what = 2;
							myHandler.sendMessage(message);
						}
					}
				}
			});
		} else {
			Toast.makeText(RuScanActivity.this, "网络状态异常", 0).show();
		}
	}

	// }
	// 上传修改状态
	private void changedata() {

		if (NetWorkUtils.isNetworkConnected(RuScanActivity.this)) {
			String statusUrl = Contants.changeStatusUrl + bianhao + ",col_0037=2]&templeId=111935";
			Log.e("url", statusUrl);
			mAbHttpUtil.post(statusUrl, new AbStringHttpResponseListener() {

				@Override
				public void onStart() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					mDialogFragment2.dismiss();
				}

				@Override
				public void onFailure(int statusCode, String content, Throwable error) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(int statusCode, String content) {
					// TODO Auto-generated method stub
					Gson geson = new Gson();
					Log.e("content", content);
					Map jc3 = geson.fromJson(content, Map.class);
					String code = (String) jc3.get("code");

					if (code.equals("1")) {
						Toast.makeText(RuScanActivity.this, "提交完成", 0).show();
					}
				}
			});
		} else {
			Toast.makeText(RuScanActivity.this, "网络状态异常", 0).show();
		}
	}
}
