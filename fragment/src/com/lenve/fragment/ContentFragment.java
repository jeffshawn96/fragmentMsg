package com.lenve.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContentFragment extends Fragment {

	private Map<String, List<String>> map;
	private List<String> list;
	private ListView lv;
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
		initBroadcast();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.content_fg, null);
		initLv(v);
		return v;
	}

	private void initLv(View v) {
		lv = (ListView) v.findViewById(R.id.content_lv);
		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, list);
		lv.setAdapter(adapter);
	}

	private void initData() {
		map = new HashMap<String, List<String>>();
		list = new ArrayList<String>();
		list.add("师说");
		list.add("马说");
		list.add("早春呈水部张十八员外");
		map.put("韩愈", list);
		list = new ArrayList<String>();
		list.add("小石潭记");
		list.add("江雪");
		list.add("捕蛇者说");
		list.add("小石城山记");
		map.put("柳宗元", list);
		list = new ArrayList<String>();
		list.add("水调歌头");
		list.add("念奴娇·赤壁怀古");
		list.add("江城子·密州出猎");
		list.add("赤壁赋");
		list.add("题西林壁");
		map.put("苏轼", list);
		list = new ArrayList<String>();
		list.add("黄州快哉亭记");
		list.add("上枢密韩太尉书");
		map.put("苏辙", list);
		list = new ArrayList<String>();
		list.add("六国论");
		list.add("九日和韩魏公");
		list.add("心术");
		list.add("管仲论");
		map.put("苏洵", list);
	}

	public void showPro(String key) {
		list = map.get(key);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, list);
		lv.setAdapter(adapter);
	}

	private void initBroadcast() {
		LocalBroadcastManager localBroadcastManager = LocalBroadcastManager
				.getInstance(getActivity());
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("showPro");
		BroadcastReceiver br = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				String key = intent.getStringExtra("name");
				list = map.get(key);
				adapter = new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_list_item_1, list);
				lv.setAdapter(adapter);
			}

		};
		localBroadcastManager.registerReceiver(br, intentFilter);
	}
}
