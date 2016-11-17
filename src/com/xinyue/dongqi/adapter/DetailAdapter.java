package com.xinyue.dongqi.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xinyue.dongqi.R;
import com.xinyue.dongqi.model.ManifestDetail;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class DetailAdapter extends BaseAdapter {
	List<ManifestDetail> mlist;
	Context mContext;
	String cwStr;
	String cpStr;
	private String text[];// 记录输入的值
	private TextWatcher textWatcher;
	private String mCWstr;
	private String mCpdmstr;
	private int index = -1;// 记录选中的位置
	Map<Integer, String> mMapContent;

	public void setList(List<ManifestDetail> list) {
		mlist = list;
		text = new String[mlist.size()];
	}

	public void setCWColor(String cwstr) {
		mCWstr = cwstr;
	}

	public void setCpdmColor(String cpdmstr) {
		mCpdmstr = cpdmstr;
	}

	public DetailAdapter(Context context) {
		mMapContent = new HashMap<Integer, String>();
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}

	@Override
	public Object getItem(int postion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int postion) {
		// TODO Auto-generated method stub
		return postion;
	}

	@Override
	public View getView(final int postion, View v, ViewGroup root) {
	
		v = LayoutInflater.from(mContext).inflate(R.layout.detail_adapter, root, false);
	
		TextView xlh = (TextView) v.findViewById(R.id.xlh);
		TextView cpdm = (TextView) v.findViewById(R.id.cpdm);
		TextView cpmc = (TextView) v.findViewById(R.id.cpmc);
		TextView ggxh = (TextView) v.findViewById(R.id.ggxh);
		TextView dw = (TextView) v.findViewById(R.id.dw);
		TextView cw = (TextView) v.findViewById(R.id.cw);
		TextView fpsl = (TextView) v.findViewById(R.id.fpsl);
		EditText jhsl = (EditText) v.findViewById(R.id.jhsl);
		TextView scrq = (TextView) v.findViewById(R.id.scrq);
		TextView pch = (TextView) v.findViewById(R.id.pch);
		TextView wltm = (TextView) v.findViewById(R.id.wltm);

		xlh.setText(mlist.get(postion).getCol_0001());
		cpdm.setText(mlist.get(postion).getCol_0002());
		cpmc.setText(mlist.get(postion).getCol_0003());
		ggxh.setText(mlist.get(postion).getCol_0004());
		dw.setText(mlist.get(postion).getCol_0005());
		cw.setText(mlist.get(postion).getCol_0014());
		fpsl.setText(mlist.get(postion).getCol_0009());
		jhsl.setText(mlist.get(postion).getCol_0020());
		scrq.setText(mlist.get(postion).getCol_0019());
		pch.setText(mlist.get(postion).getCol_0030());
		wltm.setText(mlist.get(postion).getCol_0022());

		jhsl.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				mlist.get(postion).setCol_0020(s.toString());
			}
		});
		if (mlist.get(postion).getCwcolor() == null) {

		} else if (mlist.get(postion).getCwcolor().equals("1")) {
			cw.setTextColor(Color.GREEN);
		}
		if (mlist.get(postion).getCpcolor() == null) {

		} else if (mlist.get(postion).getCpcolor().equals("1")) {
			cpdm.setTextColor(Color.GREEN);
			jhsl.setEnabled(true);
			jhsl.setFocusable(true);
			jhsl.setBackgroundResource(android.R.drawable.edit_text);
		}
		return v;

	}
}
