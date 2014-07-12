package com.example.listentomusic.common;

import android.content.Context;

import com.example.listentomusic.module.MusicClientManager;

public class MusicControl {
    private static MusicControl uniqueInstance = null;  
    public static MusicControl getInstance() {  
        if (uniqueInstance == null) {  
            uniqueInstance = new MusicControl();  
        }  
        return uniqueInstance;  
     }
	
	public void searchMusic(Context context,String keyString) {
			
		MusicClientManager.getInstance().searchMusic(context,keyString);
	}
}
