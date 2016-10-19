package com.jchat.android.activity;

import android.os.Bundle;

import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.jchat.android.controller.GroupSettingController;
import com.jchat.android.view.GroupSettingView;


public class GroupSettingActivity extends BaseActivity {
	
	private GroupSettingView mGroupSettingView;
	private GroupSettingController mGroupSettingController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_group_setting);
		
		int which = getIntent().getIntExtra(ChatDetailActivity.START_FOR_WHICH, 0);
		mGroupSettingView = (GroupSettingView) findViewById(R.id.group_setting_view);
		mGroupSettingView.initModule();
		mGroupSettingController = new GroupSettingController(mGroupSettingView, this, which);
		mGroupSettingView.setListeners(mGroupSettingController);
	}

}
