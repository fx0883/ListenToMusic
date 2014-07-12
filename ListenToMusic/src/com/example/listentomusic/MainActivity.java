package com.example.listentomusic;



import android.support.v4.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


//import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends FragmentActivity implements SelectedChangeDelegate{
	
	private FragmentTransaction mFt;
	
    private SearchMusicFragment mSearchMusicFragment;
    private TransferFragment mTransferFragment;
    private MineFragment mMineFragment;
    private SettingFragment mSettingFragment;

    private MainTabbarFragment mMainTabbarFragment=new MainTabbarFragment();
    
    private FragmentManager mFm = getSupportFragmentManager();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mMainTabbarFragment.delegate=this;
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.mainTop, mMainTabbarFragment).commit();
			
			getSupportFragmentManager().beginTransaction()
			.add(R.id.mainBottom, new MiniPlayBottom()).commit();
			

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	 /**
     * 以下一系列方法都是判断fragment是否已经创建，防止重复add进FragmentTransaction 
     * FragmentTransaction中提供了show,add,replace,hide等一系列方法，可以根据不同的需求 
     * 使用,这里demo使用了show方法，是因为避免使用replace之后会重新创建fragment,重新加载数据 
     */
    private void attachTabSearchMusic() {
            if (mSearchMusicFragment == null) {
            	mSearchMusicFragment = new SearchMusicFragment();
                    mFt.add(R.id.mainContent, mSearchMusicFragment, "searchMusic");

            } else {
                    mFt.show(mSearchMusicFragment);
                    
            }
    }
    private void attachTabTransfer() {
        if (mTransferFragment == null) {
        	mTransferFragment = new TransferFragment();
                mFt.add(R.id.mainContent, mTransferFragment, "transfer");
        } else {
                mFt.show(mTransferFragment);
                
        }
    }
    private void attachTabMine() {
        if (mMineFragment == null) {
        	mMineFragment = new MineFragment();
                mFt.add(R.id.mainContent, mMineFragment, "mine");
        } else {
                mFt.show(mMineFragment);
                
        }
    }
    
    private void attachTabSetting() {
        if (mSettingFragment == null) {
        	mSettingFragment = new SettingFragment();
                mFt.add(R.id.mainContent, mSettingFragment, "mine");
        } else {
                mFt.show(mSettingFragment);
                
        }
    }

	@Override
	public void selectdChange(int checkedId) 
	{
		mFt = mFm.beginTransaction();
		// TODO Auto-generated method stub
		Log.i("MainActivitycheckedChanged", String.valueOf(checkedId));
		
		if (mSearchMusicFragment!=null) {
			mFt.hide(mSearchMusicFragment);
		}
		if (mTransferFragment!=null) {
			mFt.hide(mTransferFragment);
		}
		if (mMineFragment!=null) {
			mFt.hide(mMineFragment);
		}
		if (mSettingFragment!=null) {
			mFt.hide(mSettingFragment);
		}
		
		switch (checkedId) 
		{
		case R.id.radio_search:
			attachTabSearchMusic();
			break;
		case R.id.radio_Transfer:
			attachTabTransfer();
			break;
		case R.id.radio_Mine:
			attachTabMine();
			break;
		case R.id.radio_Setting:
			attachTabSetting();
			break;

		default:
			break;
		}
		
		mFt.commit();
	}
  
    
//    /**
//     * 初始化选项卡 
//     */
//    private void initTab() {
//            TabHost.TabSpec tSpecHome = mTabHost.newTabSpec(“home”);
//            tSpecHome.setIndicator(mTabIndicator_home);
//            tSpecHome.setContent(new DummyTabContent(getBaseContext()));
//            mTabHost.addTab(tSpecHome);
//            TabHost.TabSpec tSpecApp = mTabHost.newTabSpec(“app”);
//            tSpecApp.setIndicator(mTabIndicator_app);
//            tSpecApp.setContent(new DummyTabContent(getBaseContext()));
//            mTabHost.addTab(tSpecApp);
//            TabHost.TabSpec tSpecGame = mTabHost.newTabSpec(“game”);
//            tSpecGame.setIndicator(mTabIndicator_game);
//            tSpecGame.setContent(new DummyTabContent(getBaseContext()));
//            mTabHost.addTab(tSpecGame);
//            TabHost.TabSpec tSpecTop = mTabHost.newTabSpec(“top”);
//            tSpecTop.setIndicator(mTabIndicator_top);
//            tSpecTop.setContent(new DummyTabContent(getBaseContext()));
//            mTabHost.addTab(tSpecTop);
//            TabHost.TabSpec tSpecSetting = mTabHost.newTabSpec(“setting”);
//            tSpecSetting.setIndicator(mTabIndicator_setting);
//            tSpecSetting.setContent(new DummyTabContent(getBaseContext()));
//            mTabHost.addTab(tSpecSetting);
//    }
//}


}
