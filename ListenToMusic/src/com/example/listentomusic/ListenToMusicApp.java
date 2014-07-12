package com.example.listentomusic;

import com.example.listentomusic.module.MusicClientManager;

import android.app.Application;
import android.content.res.Configuration;

public class ListenToMusicApp extends Application {
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	}
	
	private static ListenToMusicApp staticListenToMusicApp=null;
	
	@Override
	public void onCreate() {
	
	  super.onCreate();
	  staticListenToMusicApp=this;
	  loadData();
	  System.out.println("ListenToMusicApp is called");
	}
	@Override
	public void onLowMemory() {
	  super.onLowMemory();
	}
	@Override
	public void onTerminate() {
	  super.onTerminate();
	}
	
	///≥ı ºªØ≥Ã–Ú
	private void loadData()
	{
		MusicClientManager.getInstance();
		
	}
	
	public static ListenToMusicApp getListenToMusicApp()
	{
		
		return staticListenToMusicApp;
	}
}
