package com.example.listentomusic;





import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class MainTabbarFragment extends Fragment implements OnCheckedChangeListener
{
	
	private RadioGroup group;
	private TabHost tabHost;
	public SelectedChangeDelegate delegate=null;
	
	public static final String TAB_ITEM_Search = "tabItem1";
	public static final String TAB_ITEM_Transfer = "tabItem2";
	public static final String TAB_ITEM_Mine = "tabItem3";
	public static final String TAB_ITEM_Setting = "tabItem4";
	public MainTabbarFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		
		
		
		
		initView(rootView);
		
		return rootView;
	}
	
	protected void initView(View v) {
		
		group = (RadioGroup) v.findViewById(R.id.radio_tabbar);
		group.setOnCheckedChangeListener(this);
//		group.check(R.id.radio_search);
		onCheckedChanged(group, R.id.radio_search);
		
		
		tabHost=(TabHost)v.findViewById(R.id.tabhost);
//		tabHost = v.getTabHost();
//		
//		TabSpec tab1 = tabHost.newTabSpec(TAB_ITEM_Search);
//		TabSpec tab2 = tabHost.newTabSpec(TAB_ITEM_Transfer);
//		TabSpec tab3 = tabHost.newTabSpec(TAB_ITEM_Mine);
//		TabSpec tab4 = tabHost.newTabSpec(TAB_ITEM_Setting);
//
//		
//		tab1.setIndicator(TAB_ITEM_Search).setContent(new Intent(this, null));
//		tab2.setIndicator(TAB_ITEM_Transfer).setContent(new Intent(this, SecondActivity.class));
//		tab3.setIndicator(TAB_ITEM_Mine).setContent(new Intent(this, FirstActivity.class));
//		tab4.setIndicator(TAB_ITEM_Setting).setContent(new Intent(this, SecondActivity.class));
//
//		
//		tabHost.addTab(tab1);
//		tabHost.addTab(tab2);
//		tabHost.addTab(tab3);
//		tabHost.addTab(tab4);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// 
//		switch (checkedId) 
//		{
//		case R.id.radio_search:
////			tabHost.setCurrentTabByTag(TAB_ITEM_1);
//			break;
//		case R.id.radio_Transfer:
////			tabHost.setCurrentTabByTag(TAB_ITEM_2);
//			break;
//		case R.id.radio_Mine:
////			tabHost.setCurrentTabByTag(TAB_ITEM_3);
//			break;
//		case R.id.radio_Setting:
////			tabHost.setCurrentTabByTag(TAB_ITEM_4);
//			break;
//
//		default:
//			break;
//		}
		if (delegate!=null) {
			delegate.selectdChange(checkedId);
		}
		
		
//		Log.i("checkedChanged", String.valueOf(checkedId));
	}
}
